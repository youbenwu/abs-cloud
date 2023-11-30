package com.outmao.ebs.wallet.domain.impl;


import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.wallet.common.constant.CashStatus;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.dao.CashDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.CashDomain;
import com.outmao.ebs.wallet.domain.conver.CashVOConver;
import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.GetStatsCashDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.QCash;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.StatsCashStatusVO;
import com.outmao.ebs.wallet.vo.StatsCashVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        cash.setOrderNo(OrderNoUtil.generateOrderNo());
        cash.setKeyword(getKeyword(cash));
        cashDao.save(cash);
        return cash;
    }

    private String getKeyword(Cash data){
        StringBuffer s=new StringBuffer();

        if(data.getAlipayAccount()!=null){
            s.append(" "+data.getAlipayAccount().getName());
            s.append(" "+data.getAlipayAccount().getAccount());
        }

        if(data.getBankAccount()!=null){
            s.append(" "+data.getBankAccount().getAccountName());
            s.append(" "+data.getBankAccount().getAccountNumber());
        }

        return s.toString();
    }

    @Transactional
    @Override
    public void deleteCashById(Long id) {
        Cash cash=cashDao.getOne(id);
        if(cash.getStatus()!=CashStatus.WAIT_PAY.getStatus()&&cash.getStatus()!=CashStatus.CLOSED.getStatus()){
            throw new BusinessException("不能删除");
        }
        cashDao.delete(cash);
    }

    @Override
    public Cash getCashByOrderNo(String orderNo) {
        return cashDao.findByOrderNo(orderNo);
    }

    @Transactional
    @Override
    public Cash setCashStatus(SetCashStatusDTO request) {
        Cash cash=cashDao.findByOrderNo(request.getOrderNo());

        if(cash==null)
            return null;

        if(cash.getStatus()==request.getStatus()){
            return cash;
        }

        if(cash.getStatus()== CashStatus.WAIT_PAY.getStatus()){
            if(request.getStatus()!=CashStatus.SUCCEED.getStatus()
                    &&request.getStatus()!=CashStatus.CLOSED.getStatus()){
                throw new BusinessException("状态异常");
            }
        }else if(cash.getStatus()== CashStatus.SUCCEED.getStatus()){
            if(request.getStatus()!=CashStatus.FINISHED.getStatus()
                    &&request.getStatus()!=CashStatus.CLOSED.getStatus()){
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

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus());
        }

        if(request.getWalletId()!=null){
            p=e.wallet.id.eq(request.getWalletId()).and(p);
        }

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<CashVO> page=queryPage(e,p,cashVOConver,pageable);

        return page;
    }

    @Override
    public StatsCashVO getStatsCashVO(GetStatsCashDTO request) {
        QCash e=QCash.cash;
        Tuple t=QF.select(e.totalAmount.sum(),e.count()).from(e).where(e.wallet.id.eq(request.getWalletId()).and(e.status.in(1,2))).fetchOne();
        StatsCashVO vo=new StatsCashVO();
        if(t!=null){
            vo.setAmount(t.get(e.totalAmount.sum()));
            vo.setCount(t.get(e.count()));
        }
        return vo;
    }


    @Override
    public List<StatsCashStatusVO> getStatsCashStatusVOList() {
        QCash e=QCash.cash;
        List<Tuple> tuples=QF.select(e.totalAmount.sum(),e.status,e.count()).from(e).groupBy(e.status).fetch();
        List<StatsCashStatusVO> list=new ArrayList<>(tuples.size());
        for (Tuple t : tuples){
            StatsCashStatusVO vo=new StatsCashStatusVO();
            vo.setStatus(t.get(e.status));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.totalAmount.sum()));
            list.add(vo);
        }
        return list;
    }



}
