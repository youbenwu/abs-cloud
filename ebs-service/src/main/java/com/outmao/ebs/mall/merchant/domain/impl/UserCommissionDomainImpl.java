package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.merchant.dao.UserCommissionCashDao;
import com.outmao.ebs.mall.merchant.dao.UserCommissionDao;
import com.outmao.ebs.mall.merchant.dao.UserCommissionRecordDao;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.domain.conver.UserCommissionCashVOConvert;
import com.outmao.ebs.mall.merchant.domain.conver.UserCommissionRecordVOConver;
import com.outmao.ebs.mall.merchant.domain.conver.UserCommissionVOConver;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.*;
import com.outmao.ebs.mall.merchant.vo.UserCommissionCashVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionRecordVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class UserCommissionDomainImpl extends BaseDomain implements UserCommissionDomain {


    @Autowired
    private UserCommissionDao userCommissionDao;

    @Autowired
    private UserCommissionRecordDao userCommissionRecordDao;

    @Autowired
    private UserCommissionCashDao userCommissionCashDao;

    @Autowired
    private UserDao userDao;

    private UserCommissionVOConver userCommissionVOConver=new UserCommissionVOConver();

    private UserCommissionRecordVOConver userCommissionRecordVOConver=new UserCommissionRecordVOConver();

    private UserCommissionCashVOConvert userCommissionCashVOConvert=new UserCommissionCashVOConvert();


    @Transactional
    @Override
    public UserCommission saveUserCommission(UserCommissionDTO request) {

        UserCommission commission=userCommissionDao.findByTargetId(request.getTargetId());

        if(commission==null){
            commission=new UserCommission();
            commission.setUser(userDao.getOne(request.getUserId()));
            commission.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,commission);
        commission.setUpdateTime(new Date());

        userCommissionDao.save(commission);

        return commission;
    }

    @Override
    public UserCommission getUserCommissionById(Long id) {
        return userCommissionDao.findById(id).orElse(null);
    }

    @Override
    public UserCommissionVO getUserCommissionVOById(Long id) {

        QUserCommission e=QUserCommission.userCommission;

        return queryOne(e,e.id.eq(id),userCommissionVOConver);

    }



    @Override
    public List<UserCommissionVO> getUserCommissionVOListByIdIn(Collection<Long> idIn) {

        QUserCommission e=QUserCommission.userCommission;

        return queryList(e,e.id.in(idIn),userCommissionVOConver);
    }


    @Override
    public double getUserCommissionTotalAmount(GetUserCommissionTotalAmountDTO request) {
        QUserCommissionRecord e=QUserCommissionRecord.userCommissionRecord;

        Predicate p=null;

        if(request.getMerchantId()!=null){
            p=e.merchantId.eq(request.getMerchantId());
        }

        if(request.getCommissionId()!=null){
            p=e.commission.id.eq(request.getCommissionId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getStartTime()!=null&&request.getEndTime()!=null){
            p=e.createTime.between(request.getStartTime(),request.getEndTime()).and(p);
        }

        //只计算订单收益
        p=e.type.eq(0).and(p);

        Double amount=QF.select(e.amount.sum()).from(e).where(p).fetchOne();

        if(amount==null)
            amount=0D;

        return amount;
    }

    @Transactional
    @Override
    public UserCommissionRecord saveUserCommissionRecord(UserCommissionRecordDTO request) {

        UserCommission commission=userCommissionDao.findByIdForUpdate(request.getCommissionId());

        double amount=request.getAmount();

        if(request.getType()==1){
            //提现
            if(amount>commission.getAmount()){
                throw new BusinessException("佣金余额不足");
            }
            amount=-amount;
        }


        //可用佣金
        commission.setAmount(commission.getAmount()+amount);

        if(request.getType()==0) {
            //订单收益、总收益加加
            commission.setTotalAmount(commission.getTotalAmount() + amount);
        }

        commission.setUpdateTime(new Date());

        if(request.getStatsTime()!=null){
            commission.setStatsTime(request.getStatsTime());
        }

        userCommissionDao.save(commission);


        UserCommissionRecord record=new UserCommissionRecord();

        record.setMerchantId(commission.getMerchantId());
        record.setCommission(commission);
        record.setUserId(commission.getUser().getId());
        record.setCreateTime(new Date());

        BeanUtils.copyProperties(request,record);

        userCommissionRecordDao.save(record);

        return record;
    }

    @Override
    public Page<UserCommissionRecordVO> getUserCommissionRecordVOPage(GetUserCommissionRecordListDTO request, Pageable pageable) {

        QUserCommissionRecord e=QUserCommissionRecord.userCommissionRecord;

        Predicate p=null;

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId());
        }
        if(request.getCommissionId()!=null){
            p=e.commission.id.eq(request.getCommissionId()).and(p);
        }

        if(request.getStartTime()!=null&&request.getEndTime()!=null){
            p=e.createTime.between(request.getStartTime(),request.getEndTime()).and(p);
        }

        return queryPage(e,p,userCommissionRecordVOConver,pageable);
    }


    @Transactional
    @Override
    public UserCommissionCash saveUserCommissionCash(UserCommissionCashDTO request) {

        UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
        recordDTO.setType(1);
        recordDTO.setRemark("提现");
        recordDTO.setAmount(request.getAmount());
        recordDTO.setCommissionId(request.getCommissionId());

        UserCommissionRecord record=saveUserCommissionRecord(recordDTO);


        UserCommissionCash cash=new UserCommissionCash();
        BeanUtils.copyProperties(request,cash);
        cash.setMerchantId(record.getMerchantId());
        cash.setTargetId(record.getCommission().getTargetId());
        cash.setType(record.getCommission().getType());
        cash.setCommission(record.getCommission());
        cash.setUserId(record.getUserId());
        cash.setCreateTime(new Date());
        cash.setUpdateTime(new Date());

        userCommissionCashDao.save(cash);

        return cash;
    }


    @Transactional
    @Override
    public UserCommissionCash setUserCommissionCashStatus(SetUserCommissionCashStatusDTO request) {

        UserCommissionCash cash =userCommissionCashDao.findByIdForUpdate(request.getId());

        //状态 0--未处理 1--处理中 2--已退款 3--不通过

        if(cash.getStatus()==request.getStatus())
            return cash;


        BeanUtils.copyProperties(request,cash);
        userCommissionCashDao.save(cash);

        if(cash.getStatus()==3){
            //不通过，佣金退回
            UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
            recordDTO.setType(2);
            recordDTO.setRemark("提现取消");
            recordDTO.setAmount(cash.getAmount());
            recordDTO.setCommissionId(cash.getCommission().getId());
            saveUserCommissionRecord(recordDTO);
        }

        return cash;
    }

    @Transactional
    @Override
    public void deleteUserCommissionCashById(Long id) {
        UserCommissionCash cash=userCommissionCashDao.findByIdForUpdate(id);
        if(cash.getStatus()!=3){
            throw new BusinessException("不允许删除");
        }
        userCommissionCashDao.delete(cash);
    }

    @Override
    public UserCommissionCashVO getUserCommissionCashVOById(Long id) {
        QUserCommissionCash e=QUserCommissionCash.userCommissionCash;

        UserCommissionCashVO vo=queryOne(e,e.id.eq(id),userCommissionCashVOConvert);

        return vo;
    }

    @SetSimpleUser
    @Override
    public Page<UserCommissionCashVO> getUserCommissionCashVOPage(GetUserCommissionCashListDTO request, Pageable pageable) {

        QUserCommissionCash e=QUserCommissionCash.userCommissionCash;

        Predicate p=null;
        if(request.getCommissionId()!=null){
            p=e.commission.id.eq(request.getCommissionId());
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }


        if(request.getMerchantId()!=null){
            p=e.merchantId.eq(request.getMerchantId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<UserCommissionCashVO> page=queryPage(e,p,userCommissionCashVOConvert,pageable);

        return page;
    }





}
