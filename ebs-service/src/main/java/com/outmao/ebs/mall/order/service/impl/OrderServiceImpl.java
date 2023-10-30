package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.dto.OrderPayPrepare;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.product.dto.ProductSkuStockOutDTO;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.store.common.constant.StoreSkuStockOutStatus;
import com.outmao.ebs.mall.store.dto.SetStoreSkuStockOutStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutItemDTO;
import com.outmao.ebs.mall.store.service.StoreSkuService;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


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


    @Override
    public void run(String... args) throws Exception {
        payService.addStatusListener(this);
    }

    @Transactional
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
            }else if(trade.getStatus()== TradeStatus.TRADE_CLOSED.getStatus()){
                //交易关闭修改订单状态
                setOrderStatusDTO.setStatus(OrderStatus.CLOSED.getStatus());
                setOrderStatusDTO.setStatusRemark(OrderStatus.CLOSED.getStatusRemark());
            }

            setOrderStatus(setOrderStatusDTO);

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
        return order;
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDomain.deleteOrderById(id);
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
    public TradeVO payPrepare(OrderPayPrepare request) {

        if(StringUtils.isEmpty(request.getPayChannel())){
            request.setPayChannel(PayChannel.WalletPay.name());
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

        Currency rmb=walletService.getCurrencyById("RMB");

        long amount=(long) (order.getTotalAmount()*rmb.getOneUnit());

        if(!request.getCurrency().equals("RMB")){
            Currency currency=walletService.getCurrencyById(request.getCurrency());
            amount=amount*(rmb.getPar()/currency.getPar());
        }


        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(order.getOrderNo());
        dto.setFromId(fromUser.getWalletId());
        dto.setToId(toUser.getWalletId());
        dto.setCurrencyId(request.getCurrency());
        dto.setAmount(amount);
        dto.setType(TradeType.Pay.getType());
        dto.setBusinessType(WalletConstant.business_type_pay);
        dto.setBusiness("购买商品支付");
        dto.setPayChannel(PayChannel.valueOf(request.getPayChannel()).getType());

        return payService.tradePrepare(dto);

    }


}
