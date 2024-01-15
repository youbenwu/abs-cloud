package com.outmao.ebs.wallet.domain;

import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.TradeProfitSharing;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TradeDomain {


    public void addStatusListener(TradeStatusListener listener);


    /**
     *
     * 发起一个交易
     *
     * */
    public Trade tradePrepare(TradePrepareDTO request);


    /**
     *
     * 交易支付
     *
     * */
    public Trade tradePay(TradePayDTO request);


    /**
     *
     * 付款到对方钱包、同一交易可以分多次付款、可以付款给多个人、付款后不能退
     *
     * */
    public Trade tradePayee(TradePayeeDTO request);


    /**
     *
     * 完成交易、完成后不能退
     *
     * */
    public Trade tradeFinish(String tradeNo);

    /**
     *
     * 交易退款
     *
     * */
    public Trade tradeRefund(TradeRefundDTO request);

    /**
     *
     * 关闭交易
     *
     * */
    public Trade tradeClose(String tradeNo);


    /**
     *
     * 充值
     *
     * */
    public Trade tradeRecharge(TradeRechargeDTO request);


    /**
     *
     * 解冻本交易商户金额
     *
     * */
    public Trade tradeUnfreeze(String tradeNo);


    /**
     *
     * 获取交易
     *
     * */
    public Trade getTradeByTradeNo(String tradeNo);



    /**
     *
     * 获取交易
     *
     * */
    public TradeVO getTradeVOById(Long id);

    /**
     *
     * 获取交易
     *
     * */
    public TradeVO getTradeVOByTradeNo(String tradeNo);

    /**
     *
     * 获取交易列表
     *
     * */
    public Page<TradeVO> getTradeVOPage(GetTradeListDTO request, Pageable pageable);


    /**
     *
     * 获取所有超时的交易号
     *
     * */
    public Collection<String> getAllTimeoutTradeNo();

    /**
     *
     * 获取所有自动完成到期的交易号
     *
     * */
    public Collection<String> getAllFinishTimeoutTradeNo();


    /**
     *
     * 获取所有冻结到期的交易号
     *
     * */
    public Collection<String> getAllFreezeExpireTradeNo();


    /**
     *
     * 分账
     *
     * */
    public TradeProfitSharing tradeProfitSharing(String sharingNo);

    /**
     *
     * 分账
     *
     * */
    public TradeProfitSharing tradeProfitSharingAsync(String sharingNo);

    /**
     *
     * 保存分账信息
     *
     * */
    public TradeProfitSharing saveTradeProfitSharing(TradeProfitSharingDTO request);


}
