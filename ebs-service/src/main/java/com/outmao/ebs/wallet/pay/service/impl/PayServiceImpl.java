package com.outmao.ebs.wallet.pay.service.impl;


import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.exception.WalletPasswordErrorException;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePayPrepareDTO;
import com.outmao.ebs.wallet.dto.TradePayToDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.dto.TradeRefundDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.pay.alipay.AlipayService;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.pay.wxpay.WXPayService;
import com.outmao.ebs.wallet.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private TradeService tradeService;


	@Autowired
    private AlipayService alipayService;
	
	@Autowired
    private WXPayService wxpayService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    @Override
    public void addStatusListener(TradeStatusListener listener) {
        tradeService.addStatusListener(listener);
    }


    @Override
    public Trade tradePayTo(TradePayToDTO request) {
        return tradeService.tradePayTo(request);
    }

    @Override
    public Trade tradePrepare(TradePrepareDTO request) {
        return tradeService.tradePrepare(request);
    }


    @Override
    public Object payPrepare(PayPrepareDTO request) {

        tradeQuery(request.getTradeNo());

        Trade trade =tradeService.tradePayPrepare(new TradePayPrepareDTO(request.getTradeNo(), request.getPayChannel()));

        if (trade.getPayChannel()== PayChannel.WalletPay.getType()) {
            return trade;
        } else if (trade.getPayChannel()== PayChannel.AliPay.getType()) {
            Object result = alipayService.tradeAppPay(trade.getBusiness(), trade.getRemark(),
                    trade.getTradeNo(), trade.getTotalAmount()/100.0);
            return result;
        } else if (trade.getPayChannel()== PayChannel.WxPay.getType()) {
            Object result = wxpayService.unifiedOrder(trade.getTradeNo(),
                    trade.getBusiness()!=null?trade.getBusiness():trade.getRemark()
                    , trade.getTotalAmount()/100.0, request.getClientIp());
            return  result;
        }

        return null;
    }




    @Override
    public Trade payWallet(PayWalletDTO request) {

        tradeQuery(request.getTradeNo());

        Trade trade =tradeService.tradePayPrepare(new TradePayPrepareDTO(request.getTradeNo(), PayChannel.WalletPay.getType()));

        Wallet wallet=trade.getFrom();

        if (!passwordEncoder.matches(request.getPassword(), wallet.getPassword())) {
            throw new WalletPasswordErrorException();
        }

        trade= tradeService.tradePay(request.getTradeNo());

        return trade;
    }



    @Transactional
    @Override
    public Trade tradeQuery(String tradeNo) {
        Trade trade = tradeService.getTradeByTradeNo(tradeNo);

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
                Map<String, String> data=wxpayService.orderQuery(tradeNo);
                String trade_state = data.get("trade_state");
                /*
                 *
                 * SUCCESS—支付成功
                 * REFUND—转入退款
                 * NOTPAY—未支付
                 * CLOSED—已关闭
                 * REVOKED—已撤销（刷卡支付）
                 * USERPAYING--用户支付中
                 * PAYERROR--支付失败(其他原因，如银行返回失败)
                 *
                 */
                if(trade_state.equals("SUCCESS")) {
                    trade= tradeService.tradePay(tradeNo);
                }else if(trade_state.equals("REFUND")) {

                }else if(trade_state.equals("NOTPAY")) {

                }else if(trade_state.equals("CLOSED")) {

                }else if(trade_state.equals("REVOKED")) {

                }else if(trade_state.equals("USERPAYING")) {

                }else if(trade_state.equals("PAYERROR")) {

                }

            }
        }

        return trade;
    }

    @Override
    public Trade tradeFinish(String tradeNo) {
        Trade trade = tradeService.tradeFinish(tradeNo);
        if(trade.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
            //关闭外部交易
            if(trade.getPayChannel()== PayChannel.AliPay.getType()){
                alipayService.tradeClose(tradeNo);
            }else if(trade.getPayChannel()== PayChannel.WxPay.getType()){
                wxpayService.closeOrder(tradeNo);
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
                wxpayService.refund(tradeNo, amount/100.0);
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
