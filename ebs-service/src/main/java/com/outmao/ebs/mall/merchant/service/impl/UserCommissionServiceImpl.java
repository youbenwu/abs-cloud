package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.UserCommission;
import com.outmao.ebs.mall.merchant.entity.UserCommissionCash;
import com.outmao.ebs.mall.merchant.entity.UserCommissionRecord;
import com.outmao.ebs.mall.merchant.service.UserCommissionService;
import com.outmao.ebs.mall.merchant.vo.UserCommissionCashVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionRecordVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserCommissionServiceImpl extends BaseService implements UserCommissionService {


    @Autowired
    private UserCommissionDomain userCommissionDomain;

    @Override
    public UserCommission saveUserCommission(UserCommissionDTO request) {
        return userCommissionDomain.saveUserCommission(request);
    }

    @Override
    public UserCommissionVO getUserCommissionVOById(Long id) {
        return userCommissionDomain.getUserCommissionVOById(id);
    }

    @Override
    public List<UserCommissionVO> getUserCommissionVOListByIdIn(Collection<Long> idIn) {
        return userCommissionDomain.getUserCommissionVOListByIdIn(idIn);
    }

    @Override
    public UserCommissionRecord saveUserCommissionRecord(UserCommissionRecordDTO request) {
        return userCommissionDomain.saveUserCommissionRecord(request);
    }

    @Override
    public Page<UserCommissionRecordVO> getUserCommissionRecordVOPage(GetUserCommissionRecordListDTO request, Pageable pageable) {
        return userCommissionDomain.getUserCommissionRecordVOPage(request,pageable);
    }

    @Override
    public UserCommissionCash saveUserCommissionCash(UserCommissionCashDTO request) {
        return userCommissionDomain.saveUserCommissionCash(request);
    }

    @Override
    public UserCommissionCash setUserCommissionCashStatus(SetUserCommissionCashStatusDTO request) {
        return userCommissionDomain.setUserCommissionCashStatus(request);
    }

    @Override
    public void deleteUserCommissionCashById(Long id) {
        userCommissionDomain.deleteUserCommissionCashById(id);
    }

    @Override
    public UserCommissionCashVO getUserCommissionCashVOById(Long id) {
        return userCommissionDomain.getUserCommissionCashVOById(id);
    }

    @Override
    public Page<UserCommissionCashVO> getUserCommissionCashVOPage(GetUserCommissionCashListDTO request, Pageable pageable) {
        return userCommissionDomain.getUserCommissionCashVOPage(request,pageable);
    }

    @Override
    public double getUserCommissionTotalAmount(GetUserCommissionTotalAmountDTO request) {
        return userCommissionDomain.getUserCommissionTotalAmount(request);
    }
}
