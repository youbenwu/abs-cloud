package com.outmao.ebs.wallet.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.dao.CashDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.CashDomain;
import com.outmao.ebs.wallet.domain.conver.CashVOConver;
import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.QCash;
import com.outmao.ebs.wallet.vo.CashVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Component
public class CashDomainImpl extends BaseDomain implements CashDomain {


    @Autowired
    private CashDao cashDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CashVOConver cashVOConver;

    @Transactional
    @Override
    public Cash saveCash(CashDTO request) {
        Cash cash=new Cash();
        cash.setWallet(walletDao.getOne(request.getWalletId()));
        cash.setCreateTime(new Date());
        BeanUtils.copyProperties(request,cash);
        cashDao.save(cash);
        return cash;
    }

    @Transactional
    @Override
    public Cash setCashStatus(SetCashStatusDTO request) {
        Cash cash=cashDao.findByCashNo(request.getCashNo());

        if(cash==null)
            return null;

        if(cash.getStatus()==request.getStatus()){
            return cash;
        }

        if(cash.getStatus()== TradeStatus.TRADE_WAIT_PAY.getStatus()){
            if(request.getStatus()!=TradeStatus.TRADE_SUCCEED.getStatus()
                    ||request.getStatus()!=TradeStatus.TRADE_CLOSED.getStatus()){
                throw new BusinessException("状态异常");
            }
        }else if(cash.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
            if(request.getStatus()!=TradeStatus.TRADE_FINISHED.getStatus()
                    ||request.getStatus()!=TradeStatus.TRADE_CLOSED.getStatus()){
                throw new BusinessException("状态异常");
            }
        }else{
            throw new BusinessException("状态异常");
        }

        cash.setStatus(request.getStatus());
        cash.setStatusRemark(request.getStatusRemark());

        if(cash.getStatus()==TradeStatus.TRADE_SUCCEED.getStatus()){
            cash.setSuccessTime(new Date());
        }else if(cash.getStatus()==TradeStatus.TRADE_FINISHED.getStatus()){
            cash.setFinishTime(new Date());
        }else if(cash.getStatus()==TradeStatus.TRADE_CLOSED.getStatus()){
            cash.setCloseTime(new Date());
        }

        cashDao.save(cash);
        return cash;
    }

    @Override
    public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable) {

        QCash e=QCash.cash;

        Predicate p=null;

        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn());
        }

        if(request.getWalletId()!=null){
            p=e.wallet.id.eq(request.getWalletId()).and(p);
        }

        Page<CashVO> page=queryPage(e,p,cashVOConver,pageable);

        return page;
    }


}
