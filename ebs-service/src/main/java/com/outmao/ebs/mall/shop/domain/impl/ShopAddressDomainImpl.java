package com.outmao.ebs.mall.shop.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.shop.dao.ShopAddressDao;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.domain.ShopAddressDomain;
import com.outmao.ebs.mall.shop.dto.GetShopAddressListDTO;
import com.outmao.ebs.mall.shop.dto.ShopAddressDTO;
import com.outmao.ebs.mall.shop.entity.QShopAddress;
import com.outmao.ebs.mall.shop.entity.ShopAddress;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class ShopAddressDomainImpl extends BaseDomain implements ShopAddressDomain {

    @Autowired
    private ShopAddressDao shopAddressDao;

    @Autowired
    private ShopDao shopDao;

    @Transactional
    @Override
    public ShopAddress saveShopAddress(ShopAddressDTO request) {
        ShopAddress address=request.getId()==null?null:shopAddressDao.getOne(request.getId());

        if(address==null){
            address=new ShopAddress();
            address.setShop(shopDao.getOne(request.getShopId()));
            address.setCreateTime(new Date());
        }

        security.hasPermission(address.getShop().getOrgId(),address.getShop().getUserId());

        BeanUtils.copyProperties(request,address);

        address.setUpdateTime(new Date());

        shopAddressDao.save(address);

        if(address.isDef()){
            QShopAddress e=QShopAddress.shopAddress;
            QF.update(e).set(e.def,false).where(e.type.eq(address.getType()).and(e.shop.eq(address.getShop()).and(e.def.isTrue()))).execute();
        }

        return address;
    }

    @Transactional
    @Override
    public void deleteShopAddressById(Long id) {
       ShopAddress address=shopAddressDao.getOne(id);

       security.hasPermission(address.getShop().getOrgId(),address.getShop().getUserId());

       shopAddressDao.delete(address);
    }

    @Override
    public Page<ShopAddress> getShopAddressPage(GetShopAddressListDTO request, Pageable pageable) {
        QShopAddress e = QShopAddress.shopAddress;
        Predicate p=null;
        if(request.getType()!=null){
            p=e.type.eq(request.getType());
        }
        return shopAddressDao.findAll(p,pageable);
    }


    @Override
    public ShopAddress getDefaultShopAddress(Long shopId, int type) {
        QShopAddress e = QShopAddress.shopAddress;
        return QF.select(e).from(e).where(e.type.eq(type).and(e.shop.id.eq(shopId))).orderBy(e.def.desc()).fetchFirst();
    }
}
