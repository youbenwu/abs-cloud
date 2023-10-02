package com.outmao.ebs.mall.store.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.product.common.annotation.SetProduct;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.store.dao.StoreDao;
import com.outmao.ebs.mall.store.dao.StoreProductDao;
import com.outmao.ebs.mall.store.domain.StoreProductDomain;
import com.outmao.ebs.mall.store.domain.conver.StoreProductVOConver;
import com.outmao.ebs.mall.store.dto.GetStoreProductListDTO;
import com.outmao.ebs.mall.store.dto.StoreProductDTO;
import com.outmao.ebs.mall.store.entity.QStoreProduct;
import com.outmao.ebs.mall.store.entity.StoreProduct;
import com.outmao.ebs.mall.store.vo.StoreProductVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class StoreProductDomainImpl extends BaseDomain implements StoreProductDomain {


    @Autowired
    private StoreProductDao storeProductDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private StoreProductVOConver storeProductVOConver;


    @Transactional
    @Override
    public StoreProduct saveStoreProduct(StoreProductDTO request) {
        StoreProduct storeProduct=request.getId()!=null?storeProductDao.getOne(request.getId()):storeProductDao.findByStoreIdAndProductId(request.getStoreId(),request.getProductId());
        if(storeProduct==null){
            storeProduct=new StoreProduct();
            storeProduct.setStore(storeDao.getOne(request.getStoreId()));
            storeProduct.setProduct(productDao.getOne(request.getProductId()));
            storeProduct.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,storeProduct);
        storeProductDao.save(storeProduct);
        return storeProduct;
    }

    @Transactional
    @Override
    public void deleteStoreProductById(Long id) {
        storeProductDao.deleteById(id);
    }


    @SetProduct
    @Override
    public Page<StoreProductVO> getStoreProductVOPage(GetStoreProductListDTO request, Pageable pageable) {
        QStoreProduct e=QStoreProduct.storeProduct;
        Predicate p=null;
        if(request.getCategoryId()!=null){
           p=e.category.id.eq(request.getCategoryId());
        }
        if(request.getStoreId()!=null){
            p=e.store.id.eq(request.getStoreId()).and(p);
        }
        Page<StoreProductVO> page=queryPage(e,p,storeProductVOConver,pageable);
        return page;
    }

    @Override
    public List<String> getStoreTitleListByProductId(Long productId) {
        return storeProductDao.findAllStoreTitleByProductId(productId);
    }


}
