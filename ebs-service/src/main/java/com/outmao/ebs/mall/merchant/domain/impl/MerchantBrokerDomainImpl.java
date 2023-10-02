package com.outmao.ebs.mall.merchant.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.bbs.common.annotation.BindingSubject;
import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantBrokerStats;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerVO;
import com.outmao.ebs.mall.store.dto.StoreMemberDTO;
import com.outmao.ebs.portal.dao.RecommendDao;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant;
import com.outmao.ebs.mall.store.common.annotation.SetSimpleStore;
import com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission;
import com.outmao.ebs.mall.merchant.dao.MerchantCustomerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerDomain;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.domain.conver.MerchantBrokerVOConver;
import com.outmao.ebs.mall.merchant.domain.conver.SimpleMerchantBrokerVOConver;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.*;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.store.domain.StoreDomain;
import com.outmao.ebs.mall.store.domain.StoreMemberDomain;
import com.outmao.ebs.mall.store.entity.Store;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class MerchantBrokerDomainImpl extends BaseDomain implements MerchantBrokerDomain {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private RecommendDao recommendDao;

    @Autowired
    private MerchantBrokerDao merchantBrokerDao;

    @Autowired
    private MerchantCustomerDao merchantCustomerDao;

    @Autowired
    private StoreDomain storeDomain;

    @Autowired
    private StoreMemberDomain storeMemberDomain;

    @Autowired
    private QrCodeService qrcodeService;



    @Autowired
    private UserCommissionDomain userCommissionDomain;

    @Autowired
    private MerchantBrokerVOConver merchantBrokerVOConver;

    @Autowired
    private SimpleMerchantBrokerVOConver simpleMerchantBrokerVOConver;


    @Transactional
    @BindingSubject
    @Override
    public MerchantBroker saveMerchantBroker(MerchantBrokerDTO request) {


        MerchantBroker m=request.getId()==null?null: merchantBrokerDao.getOne(request.getId());

        if(m==null){
            m=new MerchantBroker();
            m.setCreateTime(new Date());
            m.setMerchant(merchantDao.getOne(request.getMerchantId()));
            m.setUser(userDao.getOne(request.getUserId()));
        }

        m.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,m);

        m.setKeyword(getKeyword(m));

        merchantBrokerDao.save(m);

        if(m.getCommissionId()==null){
            UserCommissionDTO commissionDTO=new UserCommissionDTO();
            commissionDTO.setMerchantId(m.getMerchant().getId());
            commissionDTO.setTargetId(m.getId());
            commissionDTO.setType(0);
            commissionDTO.setUserId(m.getUser().getId());
            UserCommission commission=userCommissionDomain.saveUserCommission(commissionDTO);
            m.setCommissionId(commission.getId());
        }

        if(m.getUrl()==null){
            String url=config.getBaseUrl()+"/merchant/broker?id="+m.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            m.setUrl(url);
            m.setQrCode(qrCode);
        }

        if(m.getBrokerQrCode()==null){
            String url=config.getBaseUrl()+"/merchant/broker/addCustomer?id="+m.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            m.setBrokerQrCode(qrCode);
        }

        if(m.getPyramidQrCode()==null){
            String url=config.getBaseUrl()+"/merchant/broker/addPartner?id="+m.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            m.setPyramidQrCode(qrCode);
        }

        m.setCode(m.getId().toString());

        if(m.getStoreId()!=null){
            Store store=storeDomain.getStoreById(m.getStoreId());
            if(store==null){
                throw new BusinessException("门店不存在");
            }
            if(!store.getMerchant().getId().equals(m.getMerchant().getId())){
                throw new BusinessException("门店["+store.getTitle()+"]不是["+m.getMerchant().getName()+"]下的门店");
            }
            storeMemberDomain.saveStoreMember(new StoreMemberDTO(request.getStoreId(),request.getUserId()));
        }

        return m;
    }


    private String getKeyword(MerchantBroker member){
        StringBuffer s=new StringBuffer();
        if(member.getName()!=null){
            s.append(" "+member.getName());
        }
        if(member.getPhone()!=null){
            s.append(" "+member.getPhone());
        }
        if(member.getEmail()!=null){
            s.append(" "+member.getEmail());
        }
        s.append(member.getUser().getKeyword());
        return s.toString();
    }

    @Override
    public MerchantBroker getMerchantBrokerById(Long id) {
        return merchantBrokerDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteMerchantBrokerById(Long id) {
        merchantBrokerDao.deleteById(id);
    }

    @Transactional
    @Override
    public MerchantBroker setMerchantBrokerStatus(SetMerchantBrokerStatusDTO request) {

        MerchantBroker m= merchantBrokerDao.getOne(request.getId());
        m.setStatus(request.getStatus());
        merchantBrokerDao.save(m);

        return m;
    }

    @SetUserCommission
    @SetSimpleStore
    @SetSimpleMerchant
    @SetSimpleUser
    @SubjectItemFilter
    @Override
    public MerchantBrokerVO getMerchantBrokerVOById(Long id) {
        QMerchantBroker e=QMerchantBroker.merchantBroker;
        MerchantBrokerVO vo=queryOne(e,e.id.eq(id), merchantBrokerVOConver);
        return vo;
    }


    @SetSimpleStore
    @SetSimpleMerchant
    @SetSimpleUser
    @SubjectItemFilter
    @Override
    public Page<MerchantBrokerVO> getMerchantBrokerVOPage(GetMerchantBrokerListDTO request, Pageable pageable) {

        QMerchantBroker e=QMerchantBroker.merchantBroker;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
           p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getStoreId()!=null){
           p=e.storeId.eq(request.getStoreId()).and(p);
        }

        if(request.getMerchantId()!=null){
            p=e.merchant.id.eq(request.getMerchantId()).and(p);
        }

        Page<MerchantBrokerVO> page=queryPage(e,p, merchantBrokerVOConver,pageable);

        return page;
    }


    @SetSimpleStore
    @Override
    public List<SimpleMerchantBrokerVO> getSimpleMerchantBrokerVOListByIdIn(Collection<Long> idIn) {
        QMerchantBroker e=QMerchantBroker.merchantBroker;
        List<SimpleMerchantBrokerVO> list= queryList(e,e.id.in(idIn), simpleMerchantBrokerVOConver);
        return list;
    }

    @SetMerchantBrokerStats
    @SetSimpleStore
    @SetSimpleMerchant
    @SetSimpleUser
    @SubjectItemFilter
    @Override
    public List<MerchantBrokerVO> getMerchantBrokerVOListByCustomerUserId(Long customerUserId) {
        QMerchantCustomer e=QMerchantCustomer.merchantCustomer;
        JPAQuery query=QF.select(merchantBrokerVOConver.select(e.broker)).from(e).where(e.user.id.eq(customerUserId));
        return queryList(e.broker,query, merchantBrokerVOConver);
    }


    @SetMerchantBrokerStats
    @SetSimpleStore
    @SetSimpleMerchant
    @SetSimpleUser
    @SubjectItemFilter
    @Override
    public List<MerchantBrokerVO> getMerchantBrokerVOListByUserId(Long userId) {
        QMerchantBroker e=QMerchantBroker.merchantBroker;
        return queryList(e,e.user.id.eq(userId), merchantBrokerVOConver);
    }

    @SetMerchantBrokerStats
    @SetSimpleStore
    @SetSimpleMerchant
    @SetSimpleUser
    @SubjectItemFilter
    @Override
    public MerchantBrokerVO getMerchantBrokerVOForService(GetMerchantBrokerForServiceDTO request) {

        QMerchantBroker e=QMerchantBroker.merchantBroker;

        if(request.getProductId()!=null){
           Long shopId=productDao.findShopIdById(request.getProductId());
           Long merchantId=shopDao.findMerchantIdById(shopId);
           MerchantCustomer customer=merchantCustomerDao.findByMerchantIdAndUserId(merchantId,request.getUserId());
           if(customer!=null){
               MerchantBrokerVO vo=queryOne(e,e.id.eq(customer.getBroker().getId()), merchantBrokerVOConver);
               return vo;
           }

           List<Long> brokers= merchantBrokerDao.findAllIdByMerchantId(merchantId);

           if(brokers.size()>0){
               MerchantBrokerVO vo=queryOne(e,e.id.eq(brokers.get(new Random().nextInt(brokers.size()))), merchantBrokerVOConver);
               return vo;
           }

        }

        List<Long> brokers=recommendDao.findItemIdAllByTypeAndItemType(0,"MerchantBroker");
        if(brokers.size()>0){
            MerchantBrokerVO vo=queryOne(e,e.id.eq(brokers.get(new Random().nextInt(brokers.size()))), merchantBrokerVOConver);
            return vo;
        }

        brokers= merchantBrokerDao.findAllId();
        if(brokers.size()>0){
            MerchantBrokerVO vo=queryOne(e,e.id.eq(brokers.get(new Random().nextInt(brokers.size()))), merchantBrokerVOConver);
            return vo;
        }

        return null;
    }


}
