package com.outmao.ebs.wallet.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.dao.RechargeDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.CurrencyDomain;
import com.outmao.ebs.wallet.domain.RechargeDomain;
import com.outmao.ebs.wallet.domain.TradeDomain;
import com.outmao.ebs.wallet.domain.conver.RechargeVOConver;
import com.outmao.ebs.wallet.dto.GetRechargeListDTO;
import com.outmao.ebs.wallet.dto.RechargeDTO;
import com.outmao.ebs.wallet.dto.SetRechargeStatusDTO;
import com.outmao.ebs.wallet.dto.TradeRechargeDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.QRecharge;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.vo.RechargeVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class RechargeDomainImpl extends BaseDomain implements RechargeDomain {


    @Autowired
    private RechargeDao rechargeDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CurrencyDomain currencyDomain;

    @Autowired
    private RechargeVOConver rechargeVOConver;

    @Transactional
    @Override
    public Recharge saveRecharge(RechargeDTO request) {
        Recharge recharge=new Recharge();

        recharge.setWallet(walletDao.getOne(request.getWalletId()));
        recharge.setCreateTime(new Date());
        BeanUtils.copyProperties(request,recharge);
        recharge.setOrderNo(OrderNoUtil.generateOrderNo());
        rechargeDao.save(recharge);
        return recharge;
    }

    @Transactional
    @Override
    public Recharge setRechargeStatus(SetRechargeStatusDTO request) {
        Recharge recharge=rechargeDao.findByOrderNo(request.getRechargeNo());

        if(recharge==null)
            return null;

        if(recharge.getStatus()==request.getStatus()){
            return recharge;
        }

        if(recharge.getStatus()== TradeStatus.TRADE_WAIT_PAY.getStatus()){
            if(request.getStatus()!=TradeStatus.TRADE_SUCCEED.getStatus()
                    ||request.getStatus()!=TradeStatus.TRADE_CLOSED.getStatus()){
                throw new BusinessException("状态异常");
            }
        }else if(recharge.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
            if(request.getStatus()!=TradeStatus.TRADE_FINISHED.getStatus()
                    ||request.getStatus()!=TradeStatus.TRADE_CLOSED.getStatus()){
                throw new BusinessException("状态异常");
            }
        }else{
            throw new BusinessException("状态异常");
        }

        recharge.setStatus(request.getStatus());
        recharge.setStatusRemark(request.getStatusRemark());

        if(recharge.getStatus()==TradeStatus.TRADE_SUCCEED.getStatus()){
            recharge.setSuccessTime(new Date());
        }else if(recharge.getStatus()==TradeStatus.TRADE_FINISHED.getStatus()){
            recharge.setFinishTime(new Date());
        }else if(recharge.getStatus()==TradeStatus.TRADE_CLOSED.getStatus()){
            recharge.setCloseTime(new Date());
        }

        rechargeDao.save(recharge);
        return recharge;
    }

    @Override
    public Recharge getRechargeByOrderNo(String orderNo) {
        return rechargeDao.findByOrderNo(orderNo);
    }

    @Override
    public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable) {
        QRecharge e=QRecharge.recharge;

        Predicate p=null;

        if(request.getStatus()!=null){
            p=e.status.in(request.getStatus());
        }

        if(request.getWalletId()!=null){
            p=e.wallet.id.eq(request.getWalletId()).and(p);
        }

        Page<RechargeVO> page=queryPage(e,p,rechargeVOConver,pageable);

        return page;
    }


}
