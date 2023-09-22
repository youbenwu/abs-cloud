package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.domain.TradeDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl extends BaseService implements TradeService {

    @Autowired
    private TradeDomain tradeDomain;

    @Override
    public void addStatusListener(TradeStatusListener listener) {
        tradeDomain.addStatusListener(listener);
    }

    @Override
    public Trade tradePrepare(TradePrepareDTO request) {
        return tradeDomain.tradePrepare(request);
    }


    @Override
    public Trade tradePayPrepare(TradePayPrepareDTO request) {
        return tradeDomain.tradePayPrepare(request);
    }

    @Override
    public Trade tradePay(String tradeNo) {
        return tradeDomain.tradePay(tradeNo);
    }

    @Override
    public Trade tradePayTo(TradePayToDTO request) {
        return tradeDomain.tradePayTo(request);
    }

    @Override
    public Trade tradeFinish(String tradeNo) {
        return tradeDomain.tradeFinish(tradeNo);
    }

    @Override
    public Trade tradeRefund(TradeRefundDTO request) {
        return tradeDomain.tradeRefund(request);
    }

    @Override
    public Trade tradeClose(String tradeNo) {
        return tradeDomain.tradeClose(tradeNo);
    }

    @Override
    public Trade getTradeByTradeNo(String tradeNo) {
        return tradeDomain.getTradeByTradeNo(tradeNo);
    }

    @Override
    public TradeVO getTradeVOByTradeNo(String tradeNo) {
        return tradeDomain.getTradeVOByTradeNo(tradeNo);
    }

    @Override
    public Page<TradeVO> getTradeVOPage(GetTradeListDTO request, Pageable pageable) {
        return tradeDomain.getTradeVOPage(request,pageable);
    }
}
