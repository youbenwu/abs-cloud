package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantPartnerDao;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.dto.UserCommissionRecordDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.order.entity.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
    private UserCommissionDomain userCommissionDomain;

    @Pointcut("execution(public * com.outmao.ebs.mall.order.domain.OrderDomain.setOrderStatus(..))")
    public void setOrderStatus() { }


    @Transactional()
    @AfterReturning(returning = "order", pointcut = "setOrderStatus()")
    public void setOrderStatus(JoinPoint jp, Order order){
        if(order==null)
            return;
        if(order.getStatus()!= OrderStatus.FINISHED.getStatus())
            return;
        if(order.getBrokerId()==null&&order.getPartnerId()==null)
            return;

        if(order.getTotalAmount()==0)
            return;

        Merchant merchant=merchantDao.findByShopId(order.getShop().getId());

        if(order.getBrokerId()!=null&&merchant.getCommission()>0){
            saveUserCommissionRecord(merchant,order);
        }

        if(order.getPartnerId()!=null&&merchant.getPartnerCommission()>0){
            saveUserCommissionRecordForPartner(merchant,order.getPartnerId(),0,order);
        }


    }

    private void saveUserCommissionRecord(Merchant merchant,Order order){

        MerchantBroker broker=merchantMemberDao.getOne(order.getBrokerId());

        double amount=order.getTotalAmount()*merchant.getCommission();

        UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
        recordDTO.setOrderId(order.getId());
        recordDTO.setCommissionId(broker.getCommissionId());
        recordDTO.setAmount(amount);
        recordDTO.setType(0);
        if(order.getProducts()!=null&&order.getProducts().size()>0) {
            recordDTO.setImage(order.getProducts().get(0).getProductImage());
            recordDTO.setRemark(order.getProducts().get(0).getProductTitle());
        }

        userCommissionDomain.saveUserCommissionRecord(recordDTO);
    }


    private void saveUserCommissionRecordForPartner(Merchant merchant,Long partnerId,int level,Order order){

        MerchantPartner partner=merchantPartnerDao.getOne(order.getPartnerId());

        double rate=level==0?merchant.getPartnerCommission():merchant.getPartnerParentCommission();

        double amount=order.getTotalAmount()*rate;

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

        userCommissionDomain.saveUserCommissionRecord(recordDTO);

        if(level==0&&partner.getParent()!=null&&merchant.getPartnerParentCommission()>0){
            saveUserCommissionRecordForPartner(merchant,partner.getParent().getId(),1,order);
        }

    }



}
