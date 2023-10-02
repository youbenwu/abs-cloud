package com.outmao.ebs.mall.takeLook.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant;
import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import com.outmao.ebs.mall.store.common.annotation.SetSimpleStore;
import com.outmao.ebs.mall.merchant.dao.MerchantCustomerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantBrokerDao;
import com.outmao.ebs.mall.merchant.domain.conver.SimpleMerchantBrokerVOConver;
import com.outmao.ebs.mall.merchant.entity.*;
import com.outmao.ebs.mall.takeLook.domain.TakeLookDomain;
import com.outmao.ebs.mall.takeLook.domain.conver.TakeLookVOConver;
import com.outmao.ebs.mall.takeLook.dto.SetTakeLookStatusDTO;
import com.outmao.ebs.mall.takeLook.dao.TakeLookDao;
import com.outmao.ebs.mall.takeLook.dao.TakeLookProductDao;
import com.outmao.ebs.mall.takeLook.dto.GetTakeLookListDTO;
import com.outmao.ebs.mall.takeLook.dto.TakeLookDTO;
import com.outmao.ebs.mall.takeLook.dto.TakeLookProductDTO;
import com.outmao.ebs.mall.takeLook.entity.QTakeLook;
import com.outmao.ebs.mall.takeLook.entity.TakeLook;
import com.outmao.ebs.mall.takeLook.entity.TakeLookProduct;
import com.outmao.ebs.mall.takeLook.vo.TakeLookVO;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
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
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class TakeLookDomainImpl extends BaseDomain implements TakeLookDomain {


    @Autowired
    private TakeLookDao takeLookDao;

    @Autowired
    private TakeLookProductDao takeLookProductDao;

    @Autowired
    private MerchantCustomerDao merchantCustomerDao;

    @Autowired
    private MerchantBrokerDao merchantMemberDao;


    private TakeLookVOConver takeLookVOConver=new TakeLookVOConver();

    @Transactional
    @Override
    public TakeLook saveTakeLook(TakeLookDTO request) {
        TakeLook look=request.getId()==null?null:takeLookDao.getOne(request.getId());

        if(look==null){
            look=new TakeLook();

            MerchantBroker broker=merchantMemberDao.getOne(request.getBrokerId());
            MerchantCustomer customer=merchantCustomerDao.getOne(request.getCustomerId());

            look.setMerchantId(broker.getMerchant().getId());
            look.setStoreId(broker.getStoreId());
            look.setBrokerId(broker.getId());
            look.setCustomerId(customer.getId());
            look.setUserId(customer.getUser().getId());
            look.setCreateTime(new Date());
        }

        if(request.getProducts()!=null&&request.getProducts().size()>0) {
            look.setProductType(request.getProducts().get(0).getProductType());
        }

        BeanUtils.copyProperties(request,look,"brokerId","customerId");

        look.setUpdateTime(new Date());

        takeLookDao.save(look);

        saveTakeLookProductList(look,request.getProducts());

        return look;
    }


    private void saveTakeLookProductList(TakeLook look, List<TakeLookProductDTO> products){
        if(products==null||products.size()==0)
            return;
        List<TakeLookProduct> list=takeLookProductDao.findAllByLookId(look.getId());

        Map<Long,TakeLookProduct> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<TakeLookProduct> saves=new ArrayList<>();

        products.forEach(t->{
            TakeLookProduct product=map.get(t.getId());
            if(product==null){
                product=new TakeLookProduct();
                product.setLook(look);
                product.setCreateTime(new Date());
            }
            BeanUtils.copyProperties(t,product);
            saves.add(product);
        });

        List<TakeLookProduct> dels=new ArrayList<>();

        list.forEach(t->{
            if(!saves.contains(t)){
                dels.add(t);
            }
        });


        if(saves.size()>0)
            takeLookProductDao.saveAll(saves);

        if(dels.size()>0)
            takeLookProductDao.deleteAll(dels);


    }


    @Transactional
    @Override
    public void deleteTakeLookById(Long id) {
        takeLookProductDao.deleteAllByLookId(id);
        takeLookDao.deleteById(id);
    }

    @Transactional
    @Override
    public TakeLook setTakeLookStatus(SetTakeLookStatusDTO request) {
        TakeLook look=takeLookDao.getOne(request.getId());
        look.setStatus(request.getStatus());
        takeLookDao.save(look);
        return look;
    }

    @SetSimpleUser
    @SetSimpleMerchantCustomer
    @SetSimpleMerchant
    @SetSimpleStore
    @Override
    public TakeLookVO getTakeLookVOById(Long id) {
        QTakeLook e=QTakeLook.takeLook;
        TakeLookVO vo=queryOne(e,e.id.eq(id),takeLookVOConver);

        List<TakeLookProduct> products =takeLookProductDao.findAllByLookId(id);
        vo.setProducts(products);

        List<Long> brokerIds=new ArrayList<>();
        if(vo.getBrokerId()!=null)
            brokerIds.add(vo.getBrokerId());
        if(vo.getWaiterId()!=null)
            brokerIds.add(vo.getWaiterId());

        if(brokerIds.size()>0) {
            QMerchantBroker q = QMerchantBroker.merchantBroker;
            List<SimpleMerchantBrokerVO> brokers = queryList(q, q.id.in(brokerIds), new SimpleMerchantBrokerVOConver());
            brokers.forEach(t->{
                if(t.getId().equals(vo.getBrokerId()))
                    vo.setBroker(t);
                if(t.getId().equals(vo.getWaiterId()))
                    vo.setWaiter(t);
            });
        }



        return vo;
    }

    @SetSimpleUser
    @SetSimpleMerchantCustomer
    @SetSimpleMerchant
    @SetSimpleStore
    @Override
    public Page<TakeLookVO> getTakeLookVOPage(GetTakeLookListDTO request, Pageable pageable) {

        QTakeLook e=QTakeLook.takeLook;


        Predicate p=null;

        if(request.getProductType()!=null){
            p=e.productType.eq(request.getProductType()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getBrokerId()!=null){
            p=e.brokerId.eq(request.getBrokerId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).eq(p);
        }

        Page<TakeLookVO> page=queryPage(e,p,takeLookVOConver,pageable);
        Map<Long,TakeLookVO> map=page.getContent().stream().collect(Collectors.toMap(t->t.getId(),t->t));
        List<Long> idIn=page.getContent().stream().map(t->t.getId()).collect(Collectors.toList());
        if(idIn.size()>0) {
            List<TakeLookProduct> products = takeLookProductDao.findAllByLookIdIn(idIn);
            products.forEach(t->{
                TakeLookVO vo=map.get(t.getLook().getId());
                if(vo.getProducts()==null){
                    vo.setProducts(new ArrayList<>());
                }
                vo.getProducts().add(t);
            });

        }


        List<Long> brokerIds=new ArrayList<>();

        page.getContent().forEach(t->{
            if(t.getBrokerId()!=null)
                brokerIds.add(t.getBrokerId());
            if(t.getWaiterId()!=null)
                brokerIds.add(t.getWaiterId());
        });

        if(brokerIds.size()>0) {
            QMerchantBroker q = QMerchantBroker.merchantBroker;
            List<SimpleMerchantBrokerVO> brokers = queryList(q, q.id.in(brokerIds), new SimpleMerchantBrokerVOConver());

            Map<Long, SimpleMerchantBrokerVO> brokerMap=brokers.stream().collect(Collectors.toMap(t->t.getId(), t->t));

            page.getContent().forEach(t->{
                if(t.getBrokerId()!=null)
                   t.setBroker(brokerMap.get(t.getBrokerId()));
                if(t.getWaiterId()!=null)
                    t.setWaiter(brokerMap.get(t.getWaiterId()));
            });

        }

        return page;
    }






}
