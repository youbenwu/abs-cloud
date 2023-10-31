package com.outmao.ebs.wallet.pay.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.exception.WalletPasswordErrorException;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePayToDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.dto.TradeRefundDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.pay.alipay.AlipayService;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.pay.wechatpay.WechatPayService;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.wechat.pay.java.service.payments.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private TradeService tradeService;


	@Autowired
    private AlipayService alipayService;


    @Autowired
	private WechatPayService wechatPayService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    @Override
    public void addStatusListener(TradeStatusListener listener) {
        tradeService.addStatusListener(listener);
    }


    @Override
    public TradeVO tradePrepare(TradePrepareDTO request) {
        tradeQuery(request.getTradeNo());
        Trade trade= tradeService.tradePrepare(request);
        return tradeService.getTradeVOById(trade.getId());
    }



    @Override
    public Object appPayPrepare(PayPrepareDTO request) {

        tradeQuery(request.getTradeNo());

        Trade trade =tradeService.getTradeByTradeNo(request.getTradeNo());

        if (trade.getPayChannel()== PayChannel.WalletPay.getType()) {
            return trade;
        } else if (trade.getPayChannel()== PayChannel.AliPay.getType()) {
            Object result = alipayService.tradeAppPay(trade.getBusiness(), trade.getRemark(),
                    trade.getTradeNo(), trade.getTotalAmount()/100.0);
            return result;
        } else if (trade.getPayChannel()== PayChannel.WxPay.getType()) {
            Object result = wechatPayService.prepayApp(trade.getTradeNo(),trade.getTotalAmount(),
                    trade.getBusiness()!=null?trade.getBusiness():trade.getRemark());
            return  result;

        }
        return null;
    }




    @Override
    public Trade payWallet(PayWalletDTO request) {

        tradeQuery(request.getTradeNo());

        Trade trade =tradeService.getTradeByTradeNo(request.getTradeNo());

        Wallet wallet=trade.getFrom();

        if(!StringUtils.isEmpty(wallet.getPassword())) {
            if (!passwordEncoder.matches(request.getPassword(), wallet.getPassword())) {
                throw new WalletPasswordErrorException();
            }
        }

        trade= tradeService.tradePay(request.getTradeNo());

        return trade;
    }



    @Transactional
    @Override
    public Trade tradeQuery(String tradeNo) {
        Trade trade = tradeService.getTradeByTradeNo(tradeNo);

        if(trade==null)
            return trade;

        if (trade.getStatus() == TradeStatus.TRADE_WAIT_PAY.getStatus()) {

            //查一下看有没支付成功
            if (trade.getPayChannel()== PayChannel.AliPay.getType()) {
                String status = alipayService.tradeQuery(tradeNo).getTradeStatus();
                if(status!=null) {
                    if (status.equals("TRADE_SUCCESS")) {
                        trade = tradeService.tradePay(tradeNo);
                    } else if (status.equals("TRADE_FINISHED")) {
                        trade = tradeService.tradePay(tradeNo);
                    } else if (status.equals("TRADE_CLOSED")) {

                    }
                }
            } else if (trade.getPayChannel()== PayChannel.WxPay.getType()) {

                Transaction transaction = wechatPayService.queryOrder(tradeNo);

                switch(transaction.getTradeState()){
                    case SUCCESS:
                        trade =  tradeService.tradePay(tradeNo);
                        break;
                    case CLOSED:
                        break;
                }

            }
        }

        return trade;
    }

    @Override
    public Trade tradePayTo(TradePayToDTO request) {
        return tradeService.tradePayTo(request);
    }

    @Override
    public Trade tradeFinish(String tradeNo) {
        Trade trade = tradeService.tradeFinish(tradeNo);
        if(trade.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
            //关闭外部交易
            if(trade.getPayChannel()== PayChannel.AliPay.getType()){
                alipayService.tradeClose(tradeNo);
            }else if(trade.getPayChannel()== PayChannel.WxPay.getType()){
                wechatPayService.closeOrder(tradeNo);
            }
        }
        return trade;
    }

    @Transactional
    @Override
    public Trade tradeRefund(String tradeNo) {
        Trade trade = tradeService.getTradeByTradeNo(tradeNo);

        if (trade.getStatus() != TradeStatus.TRADE_SUCCEED.getStatus()) {
            throw new BusinessException("交易状态异常");
        }

        long amount=trade.getAmount()-trade.getPayAmount()-trade.getRefundAmount();

        trade=tradeService.tradeRefund(new TradeRefundDTO(tradeNo,amount,0,null,"交易退款"));

        if(trade.isRefundOut()) {
            if (trade.getPayChannel() == PayChannel.AliPay.getType()) {
                alipayService.tradeRefund(tradeNo,amount/100.0);
            } else if (trade.getPayChannel() == PayChannel.WxPay.getType()) {
                wechatPayService.refund(tradeNo, UUID.randomUUID().toString(),amount,"客户退款");
            }
        }

        return trade;
    }


    @Override
    public Trade tradeClose(String tradeNo) {
       tradeQuery(tradeNo);
       return tradeService.tradeClose(tradeNo);
    }




}
