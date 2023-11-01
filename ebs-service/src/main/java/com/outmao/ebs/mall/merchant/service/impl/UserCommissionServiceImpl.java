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
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.dto.TradeRechargeDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.outmao.ebs.wallet.entity.QRecharge.recharge;

@Service
public class UserCommissionServiceImpl extends BaseService implements UserCommissionService {


    @Autowired
    private UserCommissionDomain userCommissionDomain;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private TradeService tradeService;

    @Override
    public UserCommission saveUserCommission(UserCommissionDTO request) {
        return userCommissionDomain.saveUserCommission(request);
    }

    @Override
    public UserCommission getUserCommissionById(Long id) {
        return userCommissionDomain.getUserCommissionById(id);
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

    @Transactional
    @Override
    public UserCommissionCash saveUserCommissionCash(UserCommissionCashDTO request) {
        UserCommissionCash cash= userCommissionDomain.saveUserCommissionCash(request);
        if(cash.getAmount()<1000){
            //不用审核
            SetUserCommissionCashStatusDTO statusDTO=new SetUserCommissionCashStatusDTO();
            statusDTO.setId(cash.getId());
            statusDTO.setStatus(2);
            statusDTO.setStatusRemark("已提现到钱包");
            setUserCommissionCashStatus(statusDTO);
        }
        return cash;
    }

    @Transactional
    @Override
    public UserCommissionCash setUserCommissionCashStatus(SetUserCommissionCashStatusDTO request) {
        UserCommissionCash cash= userCommissionDomain.setUserCommissionCashStatus(request);
        if(cash.getStatus()==2&&cash.getOrderNo()==null){
          cash(cash);
        }
        return cash;
    }

    private void cash(UserCommissionCash cash){

        User user=userService.getUserById(cash.getUserId());
        Currency currency=walletService.getCurrencyById("RMB");
        long amount=(long)( cash.getAmount()*currency.getOneUnit());
        Trade trade=tradeService.tradeRecharge(new TradeRechargeDTO(user.getWalletId(), currency.getId(), amount));
        cash.setOrderNo(trade.getTradeNo());

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
