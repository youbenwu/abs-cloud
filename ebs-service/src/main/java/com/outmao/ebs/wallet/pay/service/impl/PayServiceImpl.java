package com.outmao.ebs.wallet.pay.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.common.constant.OutPayType;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.exception.WalletPasswordErrorException;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePayDTO;
import com.outmao.ebs.wallet.dto.TradePayeeDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.dto.TradeRefundDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.pay.alipay.AlipayService;
import com.outmao.ebs.wallet.pay.dto.PayPrepareDTO;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.pay.wechatpay.service.WechatPayService;
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
    private UserService userService;


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
    public Object payPrepare(PayPrepareDTO request) {

        Trade trade =tradeQuery(request.getTradeNo());

        if(trade==null){
            throw new BusinessException("订单不存在");
        }

        if(trade.getStatus()!=TradeStatus.TRADE_WAIT_PAY.getStatus()){
            throw new BusinessException("订单状态异常");
        }

        if (trade.getPayChannel()== PayChannel.WalletPay.getType()) {
            return trade;
        } else if (trade.getPayChannel()== PayChannel.AliPay.getType()) {
            if(trade.getOutPayType()==OutPayType.AliPayAPP.getType()){
                Object result = alipayService.tradeAppPay(trade.getTradeNo(), trade.getTotalAmount()/100.0
                        ,trade.getSubject(), trade.getBody());
                return result;
            }else if(trade.getOutPayType()==OutPayType.AliPayPrecreate.getType()){
                Object result = alipayService.tradePrecreate(trade.getTradeNo(), trade.getTotalAmount()/100.0
                        ,trade.getSubject(), trade.getBody());
                return result;
            }else{
                Object result = alipayService.tradeAppPay(trade.getTradeNo(), trade.getTotalAmount()/100.0
                        ,trade.getSubject(), trade.getBody());
                return result;
            }

        } else if (trade.getPayChannel()== PayChannel.WxPay.getType()) {
            if(trade.getOutPayType()== OutPayType.WxPayApp.getType()){
                Object result = wechatPayService.prepayApp(trade.getTradeNo(),trade.getTotalAmount(),
                        trade.getBody());
                return  result;
            }else if(trade.getOutPayType()== OutPayType.WxPayJsapi.getType()){
                String openId=userService.getWeChatOpenId();
                if(openId==null){
                    throw new BusinessException("请用微信支付");
                }
                Object result = wechatPayService.prepayJsapi(trade.getTradeNo(),trade.getTotalAmount(),
                        trade.getBody(),openId);
                return  result;
            }else if(trade.getOutPayType()== OutPayType.WxPayNativepay.getType()){
                Object result = wechatPayService.prepayNative(trade.getTradeNo(),trade.getTotalAmount(),
                        trade.getBody());
                return  result;
            }else if(trade.getOutPayType()== OutPayType.WxPayH5.getType()){
                Object result = wechatPayService.prepayH5(trade.getTradeNo(),trade.getTotalAmount(),
                        trade.getBody());
                return  result;
            }else{
                Object result = wechatPayService.prepayApp(trade.getTradeNo(),trade.getTotalAmount(),
                        trade.getBody());
                return  result;
            }
        }
        return null;
    }




    @Override
    public Trade payWallet(PayWalletDTO request) {

        tradeQuery(request.getTradeNo());

        Trade trade =tradeService.getTradeByTradeNo(request.getTradeNo());

        if(trade.getPayChannel()!=PayChannel.WalletPay.getType()){
            throw new BusinessException("支付方式异常");
        }

        Wallet wallet=trade.getFrom();

        if(!StringUtils.isEmpty(wallet.getPassword())) {
            if (!passwordEncoder.matches(request.getPassword(), wallet.getPassword())) {
                throw new WalletPasswordErrorException();
            }
        }

        TradePayDTO payDTO=new TradePayDTO();
        payDTO.setTradeNo(request.getTradeNo());
        payDTO.setPayChannel(trade.getPayChannel());
        payDTO.setOutPayType(trade.getOutPayType());
        payDTO.setReceiptAmount(trade.getTotalAmount());
        trade= tradeService.tradePay(payDTO);

        return trade;
    }



    @Override
    public Trade tradeQuery(String tradeNo) {

        Trade trade = tradeService.getTradeByTradeNo(tradeNo);

        if(trade==null)
            return trade;

        if (trade.getStatus() == TradeStatus.TRADE_WAIT_PAY.getStatus()) {

            //查一下看有没支付成功
            if (trade.getPayChannel()== PayChannel.AliPay.getType()) {
                AlipayTradeQueryResponse response = alipayService.tradeQuery(tradeNo);

                if(response!=null) {

                    if (response.getTradeStatus().equals("TRADE_SUCCESS")||response.getTradeStatus().equals("TRADE_FINISHED")) {
                        TradePayDTO payDTO=new TradePayDTO();
                        payDTO.setTradeNo(trade.getTradeNo());
                        payDTO.setPayChannel(PayChannel.AliPay.getType());
                        payDTO.setOutPayType(trade.getOutPayType());
                        payDTO.setReceiptAmount((long) (Double.parseDouble(response.getReceiptAmount())*100));
                        trade = tradeService.tradePay(payDTO);
                    }  else if (response.getTradeStatus().equals("TRADE_CLOSED")) {

                    }
                }
            } else if (trade.getPayChannel()== PayChannel.WxPay.getType()) {

                //微信支付
                Transaction transaction = wechatPayService.queryOrder(tradeNo);

                if(transaction!=null) {
                    /**
                     *
                     * 【交易状态】 交易状态，枚举值：
                     * * SUCCESS：支付成功
                     * * REFUND：转入退款
                     * * NOTPAY：未支付
                     * * CLOSED：已关闭
                     * * REVOKED：已撤销（仅付款码支付会返回）
                     * * USERPAYING：用户支付中（仅付款码支付会返回）
                     * * PAYERROR：支付失败（仅付款码支付会返回）
                     *
                     **/
                    switch (transaction.getTradeState()) {
                        case SUCCESS:
                            TradePayDTO payDTO=new TradePayDTO();
                            payDTO.setTradeNo(trade.getTradeNo());
                            payDTO.setPayChannel(PayChannel.WxPay.getType());
                            payDTO.setOutPayType(trade.getOutPayType());
                            payDTO.setReceiptAmount(transaction.getAmount().getTotal());
                            trade = tradeService.tradePay(payDTO);
                            break;
                        case REFUND:
                            break;
                        case NOTPAY:
                            break;
                        case CLOSED:
                            break;
                        case REVOKED:
                            break;
                        case USERPAYING:
                            break;
                        case PAYERROR:
                            break;
                    }
                }

            }
        }

        return trade;
    }



    @Override
    public Trade tradePayTo(TradePayeeDTO request) {
        return tradeService.tradePayee(request);
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

        long amount=trade.getAmount()-trade.getPayeeAmount()-trade.getRefundAmount();

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
        Trade trade=tradeQuery(tradeNo);
        if(trade.getStatus()==TradeStatus.TRADE_SUCCEED.getStatus()){
            return tradeRefund(tradeNo);
        }
       return tradeService.tradeClose(tradeNo);
    }


    @Override
    public AlipayFundTransToaccountTransferResponse fundTransToaccountTransfer(String outBizNo, double amount, String payeeAccount, String payeeRealName, String remark) {
        return alipayService.fundTransToaccountTransfer(outBizNo,amount,payeeAccount,payeeRealName,remark);
    }


}
