package com.outmao.ebs.jnet.domain.storage.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.jnet.dao.material.ProductionTemplateDao;
import com.outmao.ebs.jnet.dao.material.ProductionTemplateTechnologyDao;
import com.outmao.ebs.jnet.dao.order.OrderDao;
import com.outmao.ebs.jnet.dao.storage.*;
import com.outmao.ebs.jnet.domain.storage.StorageDomain;
import com.outmao.ebs.jnet.dto.storage.DemandBillParamsDTO;
import com.outmao.ebs.jnet.dto.storage.DemandBillStyleParamsDTO;
import com.outmao.ebs.jnet.dto.storage.DemandBillStyleSpecParamsDTO;
import com.outmao.ebs.jnet.dto.storage.StorageParamsDTO;
import com.outmao.ebs.jnet.entity.material.ProductionTemplateTechnology;
import com.outmao.ebs.jnet.entity.storage.*;
import com.outmao.ebs.jnet.vo.storage.DemandBillVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleSpecVO;
import com.outmao.ebs.jnet.vo.storage.StorageStyleVO;
import com.outmao.ebs.jnet.vo.storage.StorageVO;
import com.querydsl.core.Tuple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Transactional
@Component("ZStorageDomain")
public class StorageDomainImpl extends BaseDomain implements StorageDomain {


    @Autowired
    StorageDao storageDao;

    @Autowired
    StorageStyleDao storageStyleDao;

    @Autowired
    StorageStyleSpecDao storageStyleSpecDao;

    @Autowired
    StorageStyleSpecTechDao storageStyleSpecTechDao;

    @Autowired
    DemandBillDao demandBillDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductionTemplateDao productionTemplateDao;

    @Autowired
    ProductionTemplateTechnologyDao productionTemplateTechnologyDao;

    @Autowired
    UserDao userDao;



    //type 0--加单 1--确定加单
    private Storage updateStorageStyles(Long storageId, List<DemandBillStyleParamsDTO> styles, int type){
        Storage storage = storageDao.getOne(storageId);

        for (DemandBillStyleParamsDTO style : styles) {
            saveStyle(storage, style,type);
        }

        storage.setUpdateTime(new Date());
        storage = storageDao.save(storage);

        return storage;
    }


    @Override
    public Storage saveStorage(StorageParamsDTO params) {
        Storage storage=params.getId()==null?null:storageDao.getOne(params.getId());

        if(storage==null){
            storage=new Storage();
            storage.setUser(userDao.getOne(params.getUserId()));
            storage.setManager(userDao.getOne(params.getManagerId()==null?params.getUserId():params.getManagerId()));
            storage.setOrder(orderDao.getOne(params.getOrderId()));
            storage.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(params,storage);

        storage.setUpdateTime(new Date());

        storage=storageDao.save(storage);

        return storage;
    }


    @Override
    public Storage getStorageByOrderId(Long orderId) {
        return storageDao.findByOrder(orderDao.getOne(orderId));

    }





    @Override
    public DemandBill saveDemandBill(DemandBillParamsDTO params) {
        synchronized (this) {

            Storage storage = params.getStorageId() != null ? storageDao.getOne(params.getStorageId())
                    : storageDao.findByOrder(orderDao.getOne(params.getOrderId()));


            updateStorageStyles(storage.getId(),params.getStyles(),0);

            DemandBill bill=new DemandBill();
            bill.setUser(userDao.getOne(params.getUserId()));
            bill.setStorage(storage);
            bill.setCreateTime(new Date());
            bill.setStyles(JsonUtil.toJson(params.getStyles()));
            bill=demandBillDao.save(bill);

            //如果是自己的单直接确认
            if(storage.getManager().equals(bill.getUser())){
                setDemandBillStatus(bill.getId(),1);
            }

            return bill;
        }
    }


    @Override
    public DemandBill setDemandBillStatus(Long id, int status) {
        synchronized (this){
            DemandBill bill=demandBillDao.getOne(id);

            if(bill.getStatus()==status)
                return bill;

            synchronized (this){
                if(status==1){
                    List<DemandBillStyleParamsDTO> styles=(List<DemandBillStyleParamsDTO>)JsonUtil
                            .fromJson(bill.getStyles(),List.class, DemandBillStyleParamsDTO.class);

                    updateStorageStyles(bill.getStorage().getId(),styles,1);

                }
            }

            bill.setStatus(status);
            bill=demandBillDao.save(bill);

            return bill;
        }
    }

    //type 0--加单 1--确定加单
    private StorageStyle saveStyle(Storage storage, DemandBillStyleParamsDTO style, int type){

        StorageStyle storageStyle=style.getId()==null?storageStyleDao.findByStorageAndName(storage,style.getName())
                :storageStyleDao.getOne(style.getId());

        if(storageStyle==null){
            storageStyle=new StorageStyle();
            storageStyle.setStorage(storage);
            storageStyle.setTemplate(productionTemplateDao.getOne(style.getTemplateId()));

            storageStyle.setName(style.getName());

            storageStyle.setCreateTime(new Date());

            storageStyle=storageStyleDao.save(storageStyle);

        }

        //样板的工艺列表
        List<ProductionTemplateTechnology> technologies=productionTemplateTechnologyDao.findByTemplateIds(Arrays.asList(style.getTemplateId()));



        for (DemandBillStyleSpecParamsDTO spec:style.getSpecs()){
            saveStorageStyleSpec(storage,storageStyle,technologies,spec,type);
        }

        storageStyle.setUpdateTime(new Date());
        storageStyle=storageStyleDao.save(storageStyle);



        return storageStyle;

    }

    ////type 0--加单 1--确定加单
    private StorageStyleSpec saveStorageStyleSpec(Storage storage, StorageStyle storageStyle
            , List<ProductionTemplateTechnology> technologies, DemandBillStyleSpecParamsDTO spec, int type){
        StorageStyleSpec styleSpec=spec.getId()==null?storageStyleSpecDao.findByStyleAndName(storageStyle,spec.getName())
                :storageStyleSpecDao.getOne(spec.getId());

        if(styleSpec==null){
            styleSpec=new StorageStyleSpec();
            styleSpec.setStorage(storage);
            styleSpec.setStyle(storageStyle);
            styleSpec.setName(spec.getName());
            styleSpec.setCreateTime(new Date());
            styleSpec=storageStyleSpecDao.save(styleSpec);
        }

        if(type==0) {
            //加单 待确认++
            styleSpec.setAddNum(styleSpec.getAddNum() + spec.getAddNum());
            storageStyle.setAddNum(storageStyle.getAddNum() + spec.getAddNum());
            storage.setAddNum(storage.getAddNum() + spec.getAddNum());
        }else if(type==1){
            //加单确认 待确认-- 总数++ 待分配数量++
            styleSpec.setAddNum(styleSpec.getAddNum() - spec.getAddNum());
            storageStyle.setAddNum(storageStyle.getAddNum() - spec.getAddNum());
            storage.setAddNum(storage.getAddNum() - spec.getAddNum());

            styleSpec.setNum(styleSpec.getNum() + spec.getAddNum());
            storageStyle.setNum(storageStyle.getNum() + spec.getAddNum());
            storage.setNum(storage.getNum() + spec.getAddNum());

            styleSpec.setUnSendNum(styleSpec.getUnSendNum() + spec.getAddNum());
            storageStyle.setUnSendNum(storageStyle.getUnSendNum() + spec.getAddNum());
            storage.setUnSendNum(storage.getUnSendNum() + spec.getAddNum());
        }

        styleSpec.setUpdateTime(new Date());
        styleSpec=storageStyleSpecDao.save(styleSpec);


        //工艺列表
        for (ProductionTemplateTechnology technology:technologies){
            StorageStyleSpecTech tech=storageStyleSpecTechDao.findBySpecAndName(styleSpec,technology.getName());
            if(tech==null){
                tech=new StorageStyleSpecTech();
                tech.setStorage(storage);
                tech.setStyle(storageStyle);
                tech.setSpec(styleSpec);
                tech.setName(technology.getName());
                tech.setCreateTime(new Date());
                tech.setUpdateTime(new Date());
                tech=storageStyleSpecTechDao.save(tech);
            }

        }


        return styleSpec;
    }


    @Override
    public StorageVO getStorageVOById(Long id) {
        QStorage e= QStorage.storage;
        Tuple t=QF.select(StorageVO.select(e)).from(e).where(e.id.eq(id)).fetchOne();
        if(t==null)
            return null;
        StorageVO vo=new StorageVO().fromTuple(t,e);


        vo.setStyles(getStorageStyleVOListByStorageId(id));

        return vo;
    }

    @Override
    public List<StorageStyleVO> getStorageStyleVOListByStorageId(Long storageId) {
        QStorageStyle e=QStorageStyle.storageStyle;
        return toList(QF.select(StorageStyleVO.select(e)).from(e).where(e.storage.id.eq(storageId)), StorageStyleVO.class,e);
    }

    @Override
    public List<StorageStyleSpecVO> getStorageStyleSpecVOListByStyleId(Long styleId) {
        QStorageStyleSpec e=QStorageStyleSpec.storageStyleSpec;
        return toList(QF.select(StorageStyleSpecVO.select(e)).from(e).where(e.style.id.eq(styleId)), StorageStyleSpecVO.class,e);
    }


    @Override
    public Page<DemandBillVO> getDemandBillVOPage(Long storageId, Pageable pageable) {
        QDemandBill e=QDemandBill.demandBill;
        return toPage(QF.select(DemandBillVO.select(e)).from(e).where(e.storage.id.eq(storageId)).orderBy(e.id.desc()),pageable,DemandBillVO.class,e);
    }
}
