package com.outmao.ebs.wallet.service;

import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TradeService {


    public void addStatusListener(TradeStatusListener listener);

    /**
     *
     * 发起一个交易
     *
     * */
    public Trade tradePrepare(TradePrepareDTO request);

    /**
     *
     * 准备支付
     *
     * */
    public Trade tradePayPrepare(TradePayPrepareDTO request);


    /**
     *
     * 交易支付
     *
     * */
    public Trade tradePay(String tradeNo);


    /**
     *
     * 付款到对方钱包、同一交易可以分多次付款、可以付款给多个人、付款后不能退
     *
     * */
    public Trade tradePayTo(TradePayToDTO request);


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
     * 获取交易
     *
     * */
    public Trade getTradeByTradeNo(String tradeNo);

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


}
