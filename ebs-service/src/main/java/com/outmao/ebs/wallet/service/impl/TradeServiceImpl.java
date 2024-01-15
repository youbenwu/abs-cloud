package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.domain.TradeDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.TradeProfitSharing;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.vo.TradeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
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
    public Trade tradePay(TradePayDTO request) {
        return tradeDomain.tradePay(request);
    }

    @Override
    public Trade tradePayee(TradePayeeDTO request) {
        return tradeDomain.tradePayee(request);
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
    public Trade tradeRecharge(TradeRechargeDTO request) {
        return tradeDomain.tradeRecharge(request);
    }

    @Override
    public Trade tradeUnfreeze(String tradeNo) {
        return tradeDomain.tradeUnfreeze(tradeNo);
    }

    @Override
    public TradeProfitSharing saveTradeProfitSharing(TradeProfitSharingDTO request) {
        TradeProfitSharing sharing=tradeDomain.saveTradeProfitSharing(request);
        tradeDomain.tradeProfitSharingAsync(sharing.getSharingNo());
        return sharing;
    }

    @Override
    public Trade getTradeByTradeNo(String tradeNo) {
        return tradeDomain.getTradeByTradeNo(tradeNo);
    }

    @Override
    public TradeVO getTradeVOById(Long id) {
        return tradeDomain.getTradeVOById(id);
    }

    @Override
    public TradeVO getTradeVOByTradeNo(String tradeNo) {
        return tradeDomain.getTradeVOByTradeNo(tradeNo);
    }

    @Override
    public Page<TradeVO> getTradeVOPage(GetTradeListDTO request, Pageable pageable) {
        return tradeDomain.getTradeVOPage(request,pageable);
    }

    //每小时一次
    @Scheduled(cron = "0 0 0/1 * * *")
    @Override
    public void closeAllTimeoutTrade() {
        Collection<String> nos=tradeDomain.getAllTimeoutTradeNo();
        if(nos.isEmpty())
            return;
        nos.forEach(no->{
            try{
                tradeClose(no);
            }catch (Exception e){
                log.error("关闭超时交易出错",e);
            }
        });
    }

    //每小时一次
    @Scheduled(cron = "0 0 0/1 * * *")
    @Override
    public void finishAllFinishTimeoutTrade() {
        Collection<String> nos=tradeDomain.getAllFinishTimeoutTradeNo();
        if(nos.isEmpty())
            return;
        nos.forEach(no->{
            try{
                tradeFinish(no);
            }catch (Exception e){
                log.error("自动完成交易出错",e);
            }
        });
    }

    //每天一次
    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void unfreezeAllFreezeExpireTrade() {
        Collection<String> nos=tradeDomain.getAllFreezeExpireTradeNo();
        if(nos.isEmpty())
            return;
        nos.forEach(no->{
            try{
                tradeUnfreeze(no);
            }catch (Exception e){
                log.error("解冻交易金额出错",e);
            }
        });
    }



}
