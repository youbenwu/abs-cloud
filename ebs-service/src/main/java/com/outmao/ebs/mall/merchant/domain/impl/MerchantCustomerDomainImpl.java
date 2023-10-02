package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantCustomerStats;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantBroker;
import com.outmao.ebs.mall.merchant.dao.MerchantCustomerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantPartnerDao;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.domain.conver.MerchantCustomerVOConver;
import com.outmao.ebs.mall.merchant.domain.conver.SimpleMerchantCustomerVOConver;
import com.outmao.ebs.mall.merchant.dto.DeleteMerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.dto.GetMerchantCustomerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.entity.QMerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
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
public class MerchantCustomerDomainImpl extends BaseDomain implements MerchantCustomerDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private MerchantCustomerDao merchantCustomerDao;

    @Autowired
    private MerchantPartnerDao merchantPartnerDao;

    @Autowired
    private MerchantBrokerDao merchantMemberDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private MerchantCustomerVOConver merchantCustomerVOConver;

    @Autowired
    private SimpleMerchantCustomerVOConver simpleMerchantCustomerVOConver;



    @Transactional
    @Override
    public MerchantCustomer saveMerchantCustomer(MerchantCustomerDTO request) {

        if(request.getUserId()==null){
            User user=userDomain.getUserByUsername(request.getPhone());
            if(user==null){
                RegisterDTO reg=new RegisterDTO();
                reg.setPrincipal(request.getPhone());
                reg.setOauth(Oauth.PHONE.getName());
                user=userDomain.registerUser(reg);
            }
            request.setUserId(user.getId());
        }

        if(request.getMerchantId()==null){
            MerchantBroker broker=merchantMemberDao.getOne(request.getBrokerId());
            request.setMerchantId(broker.getMerchant().getId());
        }

        MerchantCustomer customer=request.getId()==null?null:merchantCustomerDao.getOne(request.getId());

        if(customer==null){
            customer=merchantCustomerDao.findByMerchantIdAndUserId(request.getMerchantId(),request.getUserId());
        }

        if(customer==null){
            customer=new MerchantCustomer();
            customer.setMerchant(merchantDao.getOne(request.getMerchantId()));
            customer.setUser(userDao.getOne(request.getUserId()));
            customer.setCreateTime(new Date());
        }

        customer.setBroker(request.getBrokerId()==null?null:merchantMemberDao.getOne(request.getBrokerId()));
        customer.setPartner(request.getPartnerId()==null?null:merchantPartnerDao.getOne(request.getPartnerId()));

        customer.setUpdateTime(new Date());

        BeanUtils.copyProperties(request,customer,"id");

        merchantCustomerDao.save(customer);

        return customer;
    }


    @Transactional
    @Override
    public void deleteMerchantCustomerById(Long id) {
        MerchantCustomer customer=merchantCustomerDao.getOne(id);
        merchantCustomerDao.delete(customer);
    }


    @Override
    public MerchantCustomer getMerchantCustomer(Long merchantId, Long userId) {
        return merchantCustomerDao.findByMerchantIdAndUserId(merchantId,userId);
    }

    @SetMerchantCustomerStats
    @SetSimpleMerchantBroker
    @SetSimpleUser
    @Override
    public MerchantCustomerVO getMerchantCustomerVOById(Long id) {
        QMerchantCustomer e= QMerchantCustomer.merchantCustomer;

        MerchantCustomerVO vo=queryOne(e,e.id.eq(id),merchantCustomerVOConver);

        return vo;
    }

    @SetMerchantCustomerStats
    @SetSimpleMerchantBroker
    @SetSimpleUser
    @Override
    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable) {

        QMerchantCustomer e= QMerchantCustomer.merchantCustomer;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
             p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getMerchantId()!=null) {
            p = e.merchant.id.eq(request.getMerchantId()).and(p);
        }

        if(request.getBrokerId()!=null){
            p=e.broker.id.eq(request.getBrokerId()).and(p);
        }

        if(request.getPartnerId()!=null){
            p=e.partner.id.eq(request.getPartnerId()).and(p);
        }

        Page<MerchantCustomerVO> page=queryPage(e,p,merchantCustomerVOConver,pageable);

        return page;
    }


    @Override
    public List<SimpleMerchantCustomerVO> getSimpleMerchantCustomerVOByIdIn(Collection<Long> idIn) {
        QMerchantCustomer e=QMerchantCustomer.merchantCustomer;
        return queryList(e,e.id.in(idIn),simpleMerchantCustomerVOConver);
    }


}
