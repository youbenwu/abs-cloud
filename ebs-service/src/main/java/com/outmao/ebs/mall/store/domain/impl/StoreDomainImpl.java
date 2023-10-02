package com.outmao.ebs.mall.store.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.bbs.common.annotation.BindingSubject;
import com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd;
import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant;
import com.outmao.ebs.mall.store.common.annotation.SetStoreStats;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.store.dao.StoreDao;
import com.outmao.ebs.mall.store.domain.StoreDomain;
import com.outmao.ebs.mall.store.domain.conver.SimpleStoreVOConver;
import com.outmao.ebs.mall.store.domain.conver.StoreVOConver;
import com.outmao.ebs.mall.store.dto.GetStoreListDTO;
import com.outmao.ebs.mall.store.dto.SetStoreStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreDTO;
import com.outmao.ebs.mall.store.entity.*;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import com.outmao.ebs.mall.store.vo.StoreVO;
import com.outmao.ebs.org.common.annotation.BindingOrg;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
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
public class StoreDomainImpl extends BaseDomain implements StoreDomain {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private QrCodeService qrcodeService;




    private StoreVOConver storeVOConver=new StoreVOConver();
    private SimpleStoreVOConver simpleStoreVOConver=new SimpleStoreVOConver();


    @Transactional
    @BindingOrg
    @BindingSubject
    @Override
    public Store saveStore(StoreDTO request) {
        Store store=request.getId()==null?null:storeDao.getOne(request.getId());

        if(store==null){
            Merchant m=merchantDao.getOne(request.getMerchantId());
            store=new Store();
            store.setMerchant(m);
            store.setUser(m.getUser());
            store.setCreateTime(new Date());
        }

        if(request.getContact()!=null){
            StoreContact contact=store.getContact();
            if(contact==null){
                contact=new StoreContact();
            }
            store.setContact(contact);
            BeanUtils.copyProperties(request.getContact(),contact);
        }

        BeanUtils.copyProperties(request,store);
        store.setUpdateTime(new Date());

        store.setKeyword(getKeyword(store));

        storeDao.save(store);


        if(store.getUrl()==null){
            String url=config.getBaseUrl()+"/store?id="+store.getId();
            String qrCode=qrcodeService.generateQrCode(new GenerateQrCodeDTO(url,500,500));
            store.setUrl(url);
            store.setQrCode(qrCode);
        }

        return store;
    }


    private String getKeyword(Store m){
        StringBuffer s=new StringBuffer();
        if(m.getTitle()!=null){
            s.append(" "+m.getTitle());
        }
        if(m.getSubtitle()!=null){
            s.append(" "+m.getSubtitle());
        }
        if(m.getIntro()!=null){
            s.append(" "+m.getIntro());
        }
        if(m.getBusinessArea()!=null){
            s.append(" "+m.getBusinessArea());
        }
        if(m.getContact()!=null){
            if(m.getContact().getName()!=null)
                s.append(" "+m.getContact().getName());
            if(m.getContact().getPhone()!=null)
                s.append(" "+m.getContact().getPhone());
            if(m.getContact().getEmail()!=null)
                s.append(" "+m.getContact().getEmail());
        }
        s.append(" "+m.getMerchant().getKeyword());
        return s.toString();
    }

    @Transactional
    @Override
    public Store setStoreStatus(SetStoreStatusDTO request) {
        Store store=storeDao.getOne(request.getId());
        store.setStatus(request.getStatus());
        store.setStatusRemark(request.getStatusRemark());
        storeDao.save(store);

        return store;
    }

    @Override
    public Store getStoreById(Long id) {
        return storeDao.findById(id).orElse(null);
    }

    @SubjectBrowsesAdd
    @SetSimpleMerchant
    @SubjectItemFilter
    @SetStoreStats
    @Override
    public StoreVO getStoreVOById(Long id) {

        QStore e=QStore.store;

        StoreVO vo=queryOne(e,e.id.eq(id),storeVOConver);

        return vo;
    }

    @SetSimpleMerchant
    @SubjectItemFilter
    @SetStoreStats
    @Override
    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable) {

        QStore e=QStore.store;

        Predicate p=null;

        if(request.getMerchantId()!=null){
            p=e.merchant.id.eq(request.getMerchantId());
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<StoreVO> page=queryPage(e,p,storeVOConver,pageable);

        return page;
    }


    @Override
    public List<SimpleStoreVO> getSimpleStoreVOListByIdIn(Collection<Long> idIn) {

        QStore e=QStore.store;

        List<SimpleStoreVO> list=queryList(e,e.id.in(idIn),simpleStoreVOConver);

        return list;
    }




}
