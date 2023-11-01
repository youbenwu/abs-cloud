package com.outmao.ebs.mall.merchant.service;

import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.UserCommission;
import com.outmao.ebs.mall.merchant.entity.UserCommissionCash;
import com.outmao.ebs.mall.merchant.entity.UserCommissionRecord;
import com.outmao.ebs.mall.merchant.vo.UserCommissionCashVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionRecordVO;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserCommissionService {

    public UserCommission saveUserCommission(UserCommissionDTO request);

    public UserCommission getUserCommissionById(Long id);

    public UserCommissionVO getUserCommissionVOById(Long id);

    public List<UserCommissionVO> getUserCommissionVOListByIdIn(Collection<Long> idIn);

    //获取总收益，按时间段
    public double getUserCommissionTotalAmount(GetUserCommissionTotalAmountDTO request);


    public UserCommissionRecord saveUserCommissionRecord(UserCommissionRecordDTO request);

    public Page<UserCommissionRecordVO> getUserCommissionRecordVOPage(GetUserCommissionRecordListDTO request, Pageable pageable);


    public UserCommissionCash saveUserCommissionCash(UserCommissionCashDTO request);

    public UserCommissionCash setUserCommissionCashStatus(SetUserCommissionCashStatusDTO request);

    public void deleteUserCommissionCashById(Long id);

    public UserCommissionCashVO getUserCommissionCashVOById(Long id);

    public Page<UserCommissionCashVO> getUserCommissionCashVOPage(GetUserCommissionCashListDTO request, Pageable pageable);





}
