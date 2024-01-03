package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.mall.merchant.dto.UserCommissionCashDTO;
import com.outmao.ebs.mall.merchant.entity.UserCommissionRecord;
import com.outmao.ebs.mall.merchant.service.UserCommissionService;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantPartnerDao;
import com.outmao.ebs.mall.merchant.dto.UserCommissionRecordDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class OrderUserCommissionAspect {

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private MerchantBrokerDao merchantMemberDao;

    @Autowired
    private MerchantPartnerDao merchantPartnerDao;

    @Autowired
    private UserCommissionService userCommissionService;

    @Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
    public void setOrderStatus() { }


    @Transactional()
    @AfterReturning(returning = "order", pointcut = "setOrderStatus()")
    public void setOrderStatus(JoinPoint jp, Order order){
        if(order==null)
            return;
        if(order.getStatus()!= OrderStatus.FINISHED.getStatus())
            return;
        if(order.getCommissionAmount()==0)
            return;
        if(order.getBrokerId()==null&&order.getPartnerId()==null)
            return;

        Merchant merchant=merchantDao.getOne(order.getMerchantId());

        if(order.getBrokerId()!=null){
            saveUserCommissionRecord(merchant,order);
        }

        if(order.getPartnerId()!=null){
            saveUserCommissionRecordForPartner(merchant,order.getPartnerId(),0,order);
        }


    }

    private void saveUserCommissionRecord(Merchant merchant,Order order){

//        MerchantBroker broker=merchantMemberDao.getOne(order.getBrokerId());
//
//        double amount=order.getCommissionAmount()*merchant.getCommission();
//
//        UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
//        recordDTO.setOrderId(order.getId());
//        recordDTO.setCommissionId(broker.getCommissionId());
//        recordDTO.setAmount(amount);
//        recordDTO.setType(0);
//        if(order.getProducts()!=null&&order.getProducts().size()>0) {
//            recordDTO.setImage(order.getProducts().get(0).getProductImage());
//            recordDTO.setRemark(order.getProducts().get(0).getProductTitle());
//        }
//
//        UserCommissionRecord record=userCommissionService.saveUserCommissionRecord(recordDTO);
//
//        //自动提现
//        try{
//            UserCommissionCashDTO cashDTO=new UserCommissionCashDTO();
//            cashDTO.setCommissionId(recordDTO.getCommissionId());
//            cashDTO.setAmount(recordDTO.getAmount());
//            cashDTO.setRemark("佣金收益");
//            cashDTO.setUserId(record.getUserId());
//            userCommissionService.saveUserCommissionCash(cashDTO);
//        }catch (Exception e){
//            log.error("佣金自动提现出错",e);
//        }


    }


    private void saveUserCommissionRecordForPartner(Merchant merchant,Long partnerId,int level,Order order){

        MerchantPartner partner=merchantPartnerDao.getOne(order.getPartnerId());

        double rate=1;

        double amount=order.getCommissionAmount()*rate;

        UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
        recordDTO.setLevel(level);
        recordDTO.setOrderId(order.getId());
        recordDTO.setCommissionId(partner.getCommissionId());
        recordDTO.setAmount(amount);
        recordDTO.setType(0);
        if(order.getProducts()!=null&&order.getProducts().size()>0) {
            recordDTO.setImage(order.getProducts().get(0).getProductImage());
            recordDTO.setRemark(order.getProducts().get(0).getProductTitle());
        }

        UserCommissionRecord record=userCommissionService.saveUserCommissionRecord(recordDTO);



        //自动提现
        try{
            UserCommissionCashDTO cashDTO=new UserCommissionCashDTO();
            cashDTO.setCommissionId(recordDTO.getCommissionId());
            cashDTO.setAmount(recordDTO.getAmount());
            cashDTO.setRemark("佣金收益");
            cashDTO.setUserId(record.getUserId());
            userCommissionService.saveUserCommissionCash(cashDTO);
        }catch (Exception e){
            log.error("佣金自动提现出错",e);
        }


    }



}
