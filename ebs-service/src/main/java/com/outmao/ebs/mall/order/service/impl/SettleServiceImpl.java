package com.outmao.ebs.mall.order.service.impl;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.mall.order.domain.SettleDomain;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
import com.outmao.ebs.qrCode.dto.ActivateQrCodeDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service
public class SettleServiceImpl extends BaseService implements SettleService {

    @Autowired
    private SettleDomain settleDomain;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private UserService userService;

    @Override
    public SettleVO saveSettle(SettleDTO request) {
        return settleDomain.saveSettle(request);
    }

    @Override
    public void deleteById(Long id) {
        settleDomain.deleteById(id);
    }

    @Override
    public SettleVO createSettle(CreateSettleDTO request) {
        return settleDomain.createSettle(request);
    }

    @Override
    public SettleVO updateSettle(UpdateSettleDTO request) {
        return settleDomain.updateSettle(request);
    }

    @Override
    public SettleVO resettle(Long id) {
        return settleDomain.resettle(id);
    }

    @Override
    public SettleVO checkSettleChanged(Long id) {
        return settleDomain.checkSettleChanged(id);
    }

    @Override
    public SettleVO getSettleById(Long id) {
        return settleDomain.getSettleById(id);
    }

    @Override
    public ToOrderVO buy(ToOrderDTO request) {
        return settleDomain.buy(request);
    }


    @Override
    public QyPadToOrderVO toOrderAndPay(QyPadToOrderDTO request) {

        HotelDevice device=hotelDeviceService.getHotelDeviceByUserId(request.getUserId());

        CreateSettleDTO settleDTO=new CreateSettleDTO();

        if(device!=null){
            settleDTO.setHotelId(device.getHotelId());
            settleDTO.setRoomNo(device.getRoomNo());
        }

        if(device!=null&&StringUtil.isNotEmpty(request.getUserPhone())){
            User user=findOrRegisterUser(request);
            settleDTO.setUserId(user.getId());
        }else{
            settleDTO.setUserId(request.getUserId());
        }

        CreateSettleProductDTO productDTO=new CreateSettleProductDTO();
        productDTO.setQuantity(request.getQuantity());
        productDTO.setSkuId(request.getSkuId());

        settleDTO.setProducts(new ArrayList<>());
        settleDTO.getProducts().add(productDTO);


        SettleVO settleVO=createSettle(settleDTO);

        ToOrderDTO toOrderDTO=new ToOrderDTO();
        toOrderDTO.setSettleId(settleVO.getId());

        ToOrderVO toOrderVO=buy(toOrderDTO);

        OrderPayPrepareDTO payPrepareDTO=new OrderPayPrepareDTO();
        payPrepareDTO.setOrderNo(toOrderVO.getOrders().get(0));
        payPrepareDTO.setPayChannel(request.getPayChannel());
        payPrepareDTO.setOutPayType(request.getOutPayType());

        TradeVO tradeVO=orderService.payPrepare(payPrepareDTO);

        PayPrepareDTO payPrepareDTO1=new PayPrepareDTO();
        payPrepareDTO1.setTradeNo(tradeVO.getTradeNo());
        Object object=payService.payPrepare(payPrepareDTO1);

        log.info("迁眼平板支付信息{}",object);

        OrderVO order=orderService.getOrderVOByOrderNo(tradeVO.getTradeNo());

        QyPadToOrderVO qyPadToOrderVO=new QyPadToOrderVO();
        qyPadToOrderVO.setOrderNo(tradeVO.getTradeNo());
        qyPadToOrderVO.setData(object);
        qyPadToOrderVO.setOrder(order);

        if(object instanceof AlipayTradePrecreateResponse){
            AlipayTradePrecreateResponse ali=(AlipayTradePrecreateResponse)object ;
            qyPadToOrderVO.setQrCode(ali.getQrCode());
            if(ali.getQrCode()!=null) {
                QrCode qrCode=qrCodeService.activateQrCode(new ActivateQrCodeDTO(ali.getQrCode()));
                qyPadToOrderVO.setQrCodeUrl(qrCode.getPath());
            }
        }

       else if(object instanceof PrepayResponse){
            PrepayResponse wx=(PrepayResponse)object ;
            qyPadToOrderVO.setQrCodeUrl(wx.getCodeUrl());
        }



        String code="https://qianyan-4gbx9tubd992d384-1323130392.tcloudbaseapp.com?action=order_bind_owner&type="+order.getType()+"&orderNo="+order.getOrderNo();
        QrCode qrCode=qrCodeService.activateQrCode(new ActivateQrCodeDTO(code,qyPadToOrderVO.getOrderNo()));
        qyPadToOrderVO.setBindQrCodeUrl(qrCode.getPath());
        qyPadToOrderVO.setBindQrCodeId(qrCode.getId());

        return qyPadToOrderVO;
    }


    private User findOrRegisterUser(QyPadToOrderDTO request){
        User user=userService.getUserByUsername(request.getUserPhone());
        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setPrincipal(request.getUserPhone());
            registerDTO.setArgs(new HashMap<>());
            if(request.getUserName()!=null) {
                registerDTO.getArgs().put("nickname", request.getUserName());
            }
            user=userService.registerUser(registerDTO);
        }
        return user;
    }



}
