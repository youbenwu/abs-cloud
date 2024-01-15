package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.exception.IdempotentException;
import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.dto.HotelDeviceIncomeDTO;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.QyDeviceLeaseOrderVO;
import com.outmao.ebs.mall.order.vo.StatsOrderStatusVO;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.mall.product.dto.ProductSkuStockOutDTO;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.store.common.constant.StoreSkuStockOutStatus;
import com.outmao.ebs.mall.store.dto.SetStoreSkuStockOutStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutItemDTO;
import com.outmao.ebs.mall.store.service.StoreSkuService;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.common.constant.*;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.dto.TradeProfitSharingDTO;
import com.outmao.ebs.wallet.dto.TradeProfitSharingReceiverDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.TradeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class OrderServiceImpl extends BaseService implements OrderService, TradeStatusListener, CommandLineRunner {

    @Autowired
    private OrderDomain orderDomain;

    @Autowired
    private OrderStatsDomain orderStatsDomain;


    @Autowired
    private StoreSkuService storeSkuService;

    @Autowired
    private ProductService productService;


    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;


    @Autowired
    private PayService payService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private HotelDeviceIncomeService hotelDeviceIncomeService;

    @Autowired
    private MerchantService merchantService;


    @Override
    public void run(String... args) throws Exception {
        payService.addStatusListener(this);
    }


    @Override
    public void statusChanged(Trade trade) {
        if(trade.getBusinessType()== WalletConstant.business_type_pay) {
            /**
             *
             *
             *      * TRADE_WAIT_PAY--等待支付
             *      * TRADE_SUCCEED--交易成功、可退款
             *      * TRADE_FINISHED--交易完成、不可退款
             *      * TRADE_CLOSED--交易关闭、超时关闭或全额退款
             *
             * **/
            SetOrderStatusDTO setOrderStatusDTO=new SetOrderStatusDTO();
            setOrderStatusDTO.setOrderNo(trade.getTradeNo());
            if(trade.getStatus()== TradeStatus.TRADE_WAIT_PAY.getStatus()){

            }else if(trade.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
                //支付成功修改订单状态
                setOrderStatusDTO.setStatus(OrderStatus.SUCCESSED.getStatus());
                setOrderStatusDTO.setStatusRemark(OrderStatus.SUCCESSED.getStatusRemark());
            }else if(trade.getStatus()== TradeStatus.TRADE_FINISHED.getStatus()){
                setOrderStatusDTO.setStatus(OrderStatus.FINISHED.getStatus());
                setOrderStatusDTO.setStatusRemark(OrderStatus.FINISHED.getStatusRemark());
            }else if(trade.getStatus()== TradeStatus.TRADE_CLOSED.getStatus()){
                //交易关闭修改订单状态
                setOrderStatusDTO.setStatus(OrderStatus.CLOSED.getStatus());
                setOrderStatusDTO.setStatusRemark(OrderStatus.CLOSED.getStatusRemark());
            }

            try{
                setOrderStatus(setOrderStatusDTO);
            }catch (IdempotentException e){
                log.info("/**{}**/",e.getMessage());
            }

        }
    }


    @Transactional
    @Override
    public Order saveOrder(OrderDTO request) {

        Order order= orderDomain.saveOrder(request);

        updateStore(order);

        return order;
    }

    private void updateStore(Order order){
        if(order.isOut())
            return;

        if(!order.isUseStoreStock()){
            List<ProductSkuStockOutDTO> list=new ArrayList<>(order.getProducts().size());
            order.getProducts().forEach(t->{
                ProductSkuStockOutDTO outDTO=new ProductSkuStockOutDTO();
                outDTO.setSkuId(t.getSkuId());
                outDTO.setQuantity(t.getQuantity());
                list.add(outDTO);
            });
            productService.skuStockOut(list);
            return;
        }


        StoreSkuStockOutDTO dto=new StoreSkuStockOutDTO();
        dto.setOrderId(order.getId());
        dto.setStoreId(order.getFromStoreId());

        List<StoreSkuStockOutItemDTO> items=new ArrayList<>(order.getProducts().size());

        order.getProducts().forEach(t->{
            StoreSkuStockOutItemDTO item=new StoreSkuStockOutItemDTO();
            item.setSkuId(t.getSkuId());
            item.setStock(t.getQuantity());
        });

        dto.setItems(items);

        storeSkuService.saveStoreSkuStockOut(dto);

    }

    @Transactional
    @Override
    public Order setOrderStatus(SetOrderStatusDTO request) {

        Order order= orderDomain.setOrderStatus(request);

        if(order.getStatus()== OrderStatus.DELIVERED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.WAIT_OUT.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.WAIT_OUT.getStatusRemark());
            storeSkuService.setStoreSkuStockOutStatus(dto);
        }else if(order.getStatus()==OrderStatus.CLOSED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.CLOSED.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.CLOSED.getStatusRemark());
            storeSkuService.setStoreSkuStockOutStatus(dto);
        }

        if(order.getStatus()==OrderStatus.CLOSED.getStatus()){
            payService.tradeFinish(order.getOrderNo());
            //分账
            if(order.getHotelId()!=null){
                profitSharing(order);
            }
        }

        return order;
    }


    private void profitSharing(Order order){
        //平台
        double totalFee=order.getTotalAmount()*0.05;
        if(totalFee<0.01)
            return;


        HotelDevice device=hotelDeviceService.getHotelDeviceByHotelIdAndRoomNo(order.getHotelId(),order.getRoomNo());

        double fee=totalFee;

        //机主
        double renterFee=0;

        if(device!=null&&device.getLease().getStatus()==1){
            renterFee=totalFee*0.35;
            fee=totalFee-renterFee;
        }

        TradeProfitSharingDTO sharingDTO=new TradeProfitSharingDTO();
        sharingDTO.setTradeNo(order.getOrderNo());
        sharingDTO.setSharingNo(order.getOrderNo());
        sharingDTO.setUnfreeze(true);
        sharingDTO.setReceivers(new ArrayList<>());

        if(fee>0.01){
            User user=userService.getUserByUsername("admin");
            TradeProfitSharingReceiverDTO receiverDTO=new TradeProfitSharingReceiverDTO();
            receiverDTO.setAccount(user.getWalletId());
            receiverDTO.setAmount((long)(fee*100));
            receiverDTO.setBusinessType(WalletConstant.business_type_fee);
            receiverDTO.setBusiness("平台收取手续费");
            receiverDTO.setRemark("平台收取手续费");
            sharingDTO.getReceivers().add(receiverDTO);
        }

        if(renterFee>0.01){
            User user=userService.getUserById(device.getLease().getRenterId());
            TradeProfitSharingReceiverDTO receiverDTO=new TradeProfitSharingReceiverDTO();
            receiverDTO.setAccount(user.getWalletId());
            receiverDTO.setAmount((long)(renterFee*100));
            receiverDTO.setBusinessType(WalletConstant.business_type_fee);
            receiverDTO.setBusiness("酒店服务机主收益");
            receiverDTO.setRemark("酒店服务机主收益");
            sharingDTO.getReceivers().add(receiverDTO);

            HotelDeviceIncomeType type;
            if(order.getType()==ProductType.MOVIE.getType()){
                type=HotelDeviceIncomeType.Vod;
            }else{
                type=HotelDeviceIncomeType.HotelService;
            }
            HotelDeviceIncomeDTO dto=new HotelDeviceIncomeDTO();
            dto.setDeviceId(device.getId());
            dto.setType(HotelDeviceIncomeType.HotelService.getType());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setTotalFee(totalFee);
            dto.setRenterId(device.getLease().getRenterId());
            dto.setRenterFee(renterFee);
            dto.setFee(fee);

            dto.setTime(new Date());
            dto.setRemark(type.getDescription()+"收益");

            dto.setStatus(1);
            hotelDeviceIncomeService.saveHotelDeviceIncome(new HotelDeviceIncomeDTO());
        }

        if(sharingDTO.getReceivers().size()>0){
            tradeService.saveTradeProfitSharing(sharingDTO);
        }

    }


    @Override
    public Order closeOrder(CloseOrderDTO request) {
        return orderDomain.closeOrder(request);
    }

    @Override
    public Order finishOrder(FinishOrderDTO request) {
        SetOrderStatusDTO statusDTO=new SetOrderStatusDTO();
        statusDTO.setOrderNo(request.getOrderNo());
        statusDTO.setStatus(OrderStatus.FINISHED.getStatus());
        statusDTO.setStatusRemark(OrderStatus.FINISHED.getStatusRemark());
        statusDTO.setSubStatus(request.getSubStatus());
        return setOrderStatus(statusDTO);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDomain.deleteOrderById(id);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderDomain.getOrderByOrderNo(orderNo);
    }

    @Override
    public Order orderBindOwner(OrderBindOwnerDTO request) {
        if(request.getQrCodeId()==null){
            throw new BusinessException("二维码ID不能为空");
        }
        QrCode qrCode=qrCodeService.getQrCodeById(request.getQrCodeId());
        if(qrCode==null){
            throw new BusinessException("二维码不存在");
        }
        if(!request.getOrderNo().equals(qrCode.getBusiness())){
            throw new BusinessException("请扫二维码绑定");
        }
        if(request.getUserId()==null&&request.getPhone()!=null){
            User user=findOrRegisterUser(request.getPhone());
            request.setUserId(user.getId());
        }

        return orderDomain.orderBindOwner(request);
    }

    @Override
    public OrderVO getOrderVOById(Long id) {
        return orderDomain.getOrderVOById(id);
    }

    @Override
    public OrderVO getOrderVOByOrderNo(String orderNo) {
        return orderDomain.getOrderVOByOrderNo(orderNo);
    }

    @Override
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        return orderDomain.getOrderVOPage(request,pageable);
    }

    @Override
    public long getOrderCount() {
        return orderDomain.getOrderCount();
    }

    @Override
    public double getOrderAmount() {
        return orderDomain.getOrderAmount();
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime) {
        return orderStatsDomain.getStatsOrderVOListByDays(fromTime,toTime);
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn) {
        return orderStatsDomain.getStatsOrderVOListByUserIdIn(userIdIn);
    }


    @Transactional
    @Override
    public TradeVO payPrepare(OrderPayPrepareDTO request) {

        if(StringUtils.isEmpty(request.getPayChannel())){
            request.setPayChannel(PayChannel.WalletPay.name());
        }

        if(request.getPayChannel().equals(PayChannel.WxPay.name())){
            if(StringUtils.isEmpty(request.getOutPayType())){
                request.setOutPayType(OutPayType.WxPayApp.name());
            }
        }

        if(StringUtils.isEmpty(request.getCurrency())){
            request.setCurrency("RMB");
        }
        if(!request.getCurrency().equals("RMB")&&!request.getCurrency().equals("COIN")){
            throw new  BusinessException("不支付该货币支付");
        }

        Order order=orderDomain.getOrderByOrderNo(request.getOrderNo());


        if(order.getStatus()!=OrderStatus.WAIT_PAY.getStatus()){
            throw new  BusinessException("订单状态异常");
        }

        order.setPayChannel(request.getPayChannel());

        User fromUser=userService.getUserById(order.getUserId());

        User toUser=userService.getUserById(order.getSellerId());
        Merchant merchant=merchantService.getMerchantByUserId(order.getSellerId());

        Currency rmb=walletService.getCurrencyById("RMB");

        long amount=(long) (order.getTotalAmount()*rmb.getOneUnit());

        if(!request.getCurrency().equals("RMB")){
            Currency currency=walletService.getCurrencyById(request.getCurrency());
            amount=amount*(rmb.getPar()/currency.getPar());
        }


        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(order.getOrderNo());
        dto.setFromId(fromUser.getWalletId());
        dto.setToId(merchant.getWalletId()!=null?merchant.getWalletId():toUser.getWalletId());
        dto.setCurrencyId(request.getCurrency());
        dto.setAmount(amount);
        dto.setType(TradeType.Pay.getType());
        dto.setBusinessType(WalletConstant.business_type_pay);
        dto.setBusiness("购买支付");
        dto.setSubject("购买商品");
        dto.setBody(order.getDescription());
        dto.setPayChannel(PayChannel.valueOf(request.getPayChannel()).getType());
        if(!StringUtils.isEmpty(request.getOutPayType())) {
            dto.setOutPayType(OutPayType.valueOf(request.getOutPayType()).getType());
        }

        return payService.tradePrepare(dto);

    }

    @Override
    public List<StatsOrderStatusVO> getStatsOrderStatusVOList(GetStatsOrderStatusListDTO request) {
        return orderDomain.getStatsOrderStatusVOList(request);
    }


    private User findOrRegisterUser(String phone){
        User user=userService.getUserByUsername(phone);
        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setPrincipal(phone);
            registerDTO.setArgs(new HashMap<>());
            user=userService.registerUser(registerDTO);
        }
        return user;
    }




}
