package com.outmao.ebs.mall.store.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.store.common.constant.StoreSkuStockOutStatus;
import com.outmao.ebs.mall.store.dao.*;
import com.outmao.ebs.mall.store.domain.StoreSkuDomain;
import com.outmao.ebs.mall.store.domain.conver.StoreSkuStockInVOConver;
import com.outmao.ebs.mall.store.domain.conver.StoreSkuStockOutVOConver;
import com.outmao.ebs.mall.store.domain.conver.StoreSkuVOConver;
import com.outmao.ebs.mall.store.dto.*;
import com.outmao.ebs.mall.store.entity.*;
import com.outmao.ebs.mall.store.vo.StoreSkuStockInVO;
import com.outmao.ebs.mall.store.vo.StoreSkuStockOutVO;
import com.outmao.ebs.mall.store.vo.StoreSkuVO;
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
public class StoreSkuDomainImpl extends BaseDomain implements StoreSkuDomain {


    @Autowired
    private StoreSkuDao storeSkuDao;

    @Autowired
    private StoreSkuStockInDao storeSkuStockInDao;

    @Autowired
    private StoreSkuStockInItemDao storeSkuStockInItemDao;


    @Autowired
    private StoreSkuStockOutDao storeSkuStockOutDao;

    @Autowired
    private StoreSkuStockOutItemDao storeSkuStockOutItemDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StoreSkuVOConver storeSkuVOConver;

    @Autowired
    private StoreSkuStockInVOConver storeSkuStockInVOConver;

    @Autowired
    private StoreSkuStockOutVOConver storeSkuStockOutVOConver;


    @Override
    public void updateStore(Product product) {
        List<StoreSku> list=storeSkuDao.findAllByProductId(product.getId());
        if(list.isEmpty()){
            return;
        }
        Map<Long,ProductSku> idMap=product.getSkus().stream().collect(Collectors.toMap(t->t.getId(),t->t));
        Map<String,ProductSku> nameMap=product.getSkus().stream().collect(Collectors.toMap(t->t.getName(),t->t));

        QStoreSku e=QStoreSku.storeSku;

        product.setStock(0);
        product.getSkus().forEach(t->{
            t.setStock(0);
        });

        list.forEach(t->{
            ProductSku sku=idMap.get(t.getSkuId());
            if(sku==null){
                sku=nameMap.get(t.getSkuName());
            }
            if(sku!=null){
                sku.setStock(sku.getStock()+t.getAvailableStock());
                product.setStock(product.getStock()+t.getAvailableStock());
                QF.update(e)
                        .set(e.skuId,sku.getId())
                        .set(e.skuName,sku.getName())
                        .set(e.skuNo,sku.getSkuNo())
                        .set(e.alarmStock,sku.getAlarmStock())
                        .where(e.id.eq(t.getId())).execute();
            }else{
                if(t.getAvailableStock()==0&&t.getStock()==0){
                    QF.delete(e).where(e.id.eq(t.getId())).execute();
                }else {
                    QF.update(e).set(e.status, 1).where(e.id.eq(t.getId())).execute();
                }
            }
        });

    }

    @Override
    public List<StoreSkuVO> getStoreSkuVOListByProductId(Long productId) {
        QStoreSku e=QStoreSku.storeSku;
        List<StoreSkuVO> list=queryList(e,e.product.id.eq(productId),storeSkuVOConver);
        return list;
    }

    @Transactional
    @Override
    public StoreSkuStockIn saveStoreSkuStockIn(StoreSkuStockInDTO request) {
        StoreSkuStockIn stockIn=new StoreSkuStockIn();
        stockIn.setCreateTime(new Date());
        stockIn.setStoreId(request.getStoreId());
        stockIn.setBatchNo(request.getBatchNo());
        stockIn.setRemark(request.getRemark());
        long stock=0;
        for(StoreSkuStockInItemDTO item:request.getItems()){
            stock+=item.getStock();
        }
        stockIn.setStock(stock);

        storeSkuStockInDao.save(stockIn);


        List<StoreSkuStockInItem> list=new ArrayList<>(request.getItems().size());
        request.getItems().forEach(t->{
            list.add(saveStoreSkuStockInItem(stockIn,t));
        });

        stockIn.setDetails(JsonUtil.toJson(list));

        return stockIn;
    }

    private StoreSkuStockInItem saveStoreSkuStockInItem(StoreSkuStockIn stockIn,StoreSkuStockInItemDTO dto){
        StoreSku sku=stockAdd(stockIn.getStoreId(),dto.getSkuId(),dto.getStock(),true,true);
        StoreSkuStockInItem item=new StoreSkuStockInItem();
        item.setBatchNo(stockIn.getBatchNo());
        item.setStockInId(stockIn.getId());
        item.setProductId(sku.getProduct().getId());
        item.setProductTitle(sku.getProduct().getTitle());
        item.setSkuId(sku.getSkuId());
        item.setSkuName(sku.getSkuName());
        item.setStock(dto.getStock());
        storeSkuStockInItemDao.save(item);
        return item;
    }


//    private StoreSku saveStoreSku(Store store, StoreSkuStockInItemDTO dto){
//        ProductSku productSku=productSkuDao.findByIdLock(dto.getSkuId());
//        Product product=productDao.findByIdLock(productSku.getProduct().getId());
//        StoreSku storeSku=storeSkuDao.findByStoreIdAndSkuId(store.getId(),dto.getSkuId());
//
//        if(storeSku==null){
//            storeSku=new StoreSku();
//            storeSku.setCreateTime(new Date());
//            storeSku.setStore(store);
//            storeSku.setProduct(productSku.getProduct());
//            storeSku.setSkuId(productSku.getId());
//            storeSku.setSkuName(productSku.getName());
//            storeSku.setSkuNo(productSku.getSkuNo());
//            storeSku.setAlarmStock(storeSku.getAlarmStock());
//        }
//
//        storeSku.setStock(storeSku.getStock()+dto.getStock());
//
//        storeSku.setUpdateTime(new Date());
//
//        productSku.setStock(productSku.getStock()+dto.getStock());
//        product.setStock(product.getStock()+dto.getStock());
//
//        storeSkuDao.save(storeSku);
//        productSkuDao.save(productSku);
//        productDao.save(product);
//
//        return storeSku;
//    }

    private StoreSku stockAdd(Long storeId,Long skuId,long stock,boolean lockStock,boolean lockAvailableStock){

        ProductSku productSku=productSkuDao.findByIdForUpdate(skuId);
        Product product=productSku!=null?productDao.findByIdForUpdate(productSku.getProduct().getId()):null;
        StoreSku storeSku=storeSkuDao.findByStoreIdAndSkuId(storeId,skuId);

        if(storeSku==null){
            storeSku=new StoreSku();
            storeSku.setCreateTime(new Date());
            storeSku.setStore(storeDao.getOne(storeId));
            storeSku.setProduct(productSku.getProduct());
            storeSku.setSkuId(productSku.getId());
            storeSku.setSkuName(productSku.getName());
            storeSku.setSkuNo(productSku.getSkuNo());
            storeSku.setAlarmStock(storeSku.getAlarmStock());
        }

        storeSku.setUpdateTime(new Date());

        if(lockAvailableStock){
            storeSku.setAvailableStock(storeSku.getAvailableStock()+stock);
            if(productSku!=null) {
                if(productSku.getStock()+stock<0){
                    throw new BusinessException("库存不足");
                }
                productSku.setStock(productSku.getStock() + stock);
                product.setStock(product.getStock() + stock);
            }
        }
        if(lockStock){
            if(storeSku.getStock()+stock<0){
                throw new BusinessException("库存不足");
            }
            storeSku.setStock(storeSku.getStock()+stock);
        }

        storeSkuDao.save(storeSku);
        if(productSku!=null) {
            productSkuDao.save(productSku);
            productDao.save(product);
        }

        return storeSku;
    }



    @Override
    public Page<StoreSkuStockInVO> getStoreSkuStockInVOPage(GetStoreSkuStockInListDTO request, Pageable pageable) {
        QStoreSkuStockIn e=QStoreSkuStockIn.storeSkuStockIn;

        Predicate p=null;

        if(request.getStoreId()!=null){
            p=e.storeId.eq(request.getStoreId());
        }

        Page<StoreSkuStockInVO> page=queryPage(e,p,storeSkuStockInVOConver,pageable);

        return page;
    }

    @Transactional
    @Override
    public StoreSkuStockOut saveStoreSkuStockOut(StoreSkuStockOutDTO request) {
        StoreSkuStockOut stockOut=new StoreSkuStockOut();

        stockOut.setCreateTime(new Date());
        BeanUtils.copyProperties(request,stockOut);

        long stock=0;
        for(StoreSkuStockOutItemDTO item:request.getItems()){
            stock+=item.getStock();
        }
        stockOut.setStock(stock);

        stockOut.setLockStock(false);
        stockOut.setLockAvailableStock(true);

        storeSkuStockOutDao.save(stockOut);

        List<StoreSkuStockOutItem> list=new ArrayList<>(request.getItems().size());
        request.getItems().forEach(t->{
            list.add(saveStoreSkuStockOutItem(stockOut,t));
        });

        stockOut.setDetails(JsonUtil.toJson(list));

        return stockOut;
    }


    private StoreSkuStockOutItem saveStoreSkuStockOutItem(StoreSkuStockOut stockOut,StoreSkuStockOutItemDTO dto){
        StoreSku sku=stockAdd(stockOut.getStoreId(),dto.getSkuId(),-dto.getStock(),stockOut.isLockStock(),stockOut.isLockAvailableStock());
        StoreSkuStockOutItem item=new StoreSkuStockOutItem();
        item.setStockOutId(stockOut.getId());
        item.setProductId(sku.getProduct().getId());
        item.setProductTitle(sku.getProduct().getTitle());
        item.setSkuId(sku.getSkuId());
        item.setSkuName(sku.getSkuName());
        item.setStock(dto.getStock());
        storeSkuStockOutItemDao.save(item);
        return item;
    }


//    private StoreSku saveStoreSku(Store store, StoreSkuStockOutItemDTO dto){
//
//
//        ProductSku productSku=productSkuDao.findByIdLock(dto.getSkuId());
//
//        Product product=productDao.findByIdLock(productSku.getProduct().getId());
//
//        StoreSku storeSku=storeSkuDao.findByStoreIdAndSkuId(store.getId(),dto.getSkuId());
//
//        storeSku.setUpdateTime(new Date());
//
//        if(storeSku.getStock()<dto.getStock()){
//            throw new BusinessException("库存不足");
//        }
//
//        storeSku.setStock(storeSku.getStock()-dto.getStock());
//        productSku.setStock(productSku.getStock()-dto.getStock());
//        product.setStock(product.getStock()-dto.getStock());
//
//        storeSkuDao.save(storeSku);
//        productSkuDao.save(productSku);
//        productDao.save(product);
//
//        return storeSku;
//    }

    @Transactional
    @Override
    public StoreSkuStockOut setStoreSkuStockOutStatus(SetStoreSkuStockOutStatusDTO request) {
        StoreSkuStockOut stockOut=request.getId()!=null?storeSkuStockOutDao.findByIdForUpdate(request.getId())
                :storeSkuStockOutDao.findByOrderIdForUpdate(request.getOrderId());

        if(stockOut==null)
            return null;

        if(stockOut.getStatus()==request.getStatus()){
            return stockOut;
        }

        if(stockOut.getStatus()>request.getStatus()){
           throw new BusinessException("出货单状态异常");
        }

        if(stockOut.getStatus()== StoreSkuStockOutStatus.FINISHED.getStatus()
                ||stockOut.getStatus()== StoreSkuStockOutStatus.CLOSED.getStatus()){
            throw new BusinessException("出货单状态异常");
        }

        stockOut.setStatus(request.getStatus());
        stockOut.setStatusRemark(request.getStatusRemark());
        stockOut.setUpdateTime(new Date());

        if(stockOut.getStatus()== StoreSkuStockOutStatus.FINISHED.getStatus()){
            //减库存
            storeSkuStockOut(stockOut);
        }

        if(stockOut.getStatus()== StoreSkuStockOutStatus.CLOSED.getStatus()){
            //取消
            backStoreSkuStockOut(stockOut);
        }

        storeSkuStockOutDao.save(stockOut);

        return stockOut;
    }

    private void storeSkuStockOut(StoreSkuStockOut stockOut){
        List<StoreSkuStockOutItem> items=storeSkuStockOutItemDao.findAllByStockOutId(stockOut.getId());
        items.forEach(t->{
            stockAdd(stockOut.getStoreId(),t.getSkuId(),-t.getStock(),!stockOut.isLockStock(),!stockOut.isLockAvailableStock());
        });
        stockOut.setLockStock(true);
        stockOut.setLockAvailableStock(true);
    }

    private void backStoreSkuStockOut(StoreSkuStockOut stockOut){
        List<StoreSkuStockOutItem> items=storeSkuStockOutItemDao.findAllByStockOutId(stockOut.getId());
        items.forEach(t->{
            stockAdd(stockOut.getStoreId(),t.getSkuId(),t.getStock(),stockOut.isLockStock(),stockOut.isLockAvailableStock());
        });
    }

//    private void backStoreSkuStockOutItem(Store store, StoreSkuStockOutItem item){
//
//        Product product=productDao.findByIdLock(item.getProductId());
//
//        ProductSku productSku=productSkuDao.findByIdLock(item.getSkuId());
//
//        StoreSku storeSku=storeSkuDao.findByStoreIdAndSkuId(store.getId(),item.getSkuId());
//
//        storeSku.setStock(storeSku.getStock()+item.getStock());
//
//        storeSku.setUpdateTime(new Date());
//
//        productSku.setStock(productSku.getStock()+item.getStock());
//        product.setStock(product.getStock()+item.getStock());
//
//        storeSkuDao.save(storeSku);
//        productSkuDao.save(productSku);
//        productDao.save(product);
//
//    }


    @Override
    public Page<StoreSkuStockOutVO> getStoreSkuStockOutVOPage(GetStoreSkuStockOutListDTO request, Pageable pageable) {
        QStoreSkuStockOut e=QStoreSkuStockOut.storeSkuStockOut;

        Predicate p=null;

        if(request.getStoreId()!=null){
            p=e.storeId.eq(request.getStoreId());
        }

        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }

        Page<StoreSkuStockOutVO> page=queryPage(e,p,storeSkuStockOutVOConver,pageable);

        return page;
    }


}
