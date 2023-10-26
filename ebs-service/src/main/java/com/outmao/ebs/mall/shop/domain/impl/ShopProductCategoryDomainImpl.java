package com.outmao.ebs.mall.shop.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.shop.dao.ShopProductCategoryDao;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.domain.ShopProductCategoryDomain;
import com.outmao.ebs.mall.shop.domain.conver.ShopProductCategoryVOConver;
import com.outmao.ebs.mall.shop.dto.GetShopProductCategoryListDTO;
import com.outmao.ebs.mall.shop.dto.ShopProductCategoryDTO;
import com.outmao.ebs.mall.shop.entity.QShopProductCategory;
import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import com.outmao.ebs.mall.shop.vo.ShopProductCategoryVO;
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
public class ShopProductCategoryDomainImpl extends BaseDomain implements ShopProductCategoryDomain {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopProductCategoryDao shopCategoryDao;


    private ShopProductCategoryVOConver shopCategoryVOConver=new ShopProductCategoryVOConver();

    @Transactional
    @Override
    public ShopProductCategory saveShopProductCategory(ShopProductCategoryDTO request) {
        ShopProductCategory category=request.getId()==null?null:shopCategoryDao.findByIdForUpdate(request.getId());

        if(category==null){
            category=new ShopProductCategory();
            category.setShop(shopDao.getOne(request.getShopId()));
            category.setCreateTime(new Date());
            category.setLeaf(true);
            if(request.getParentId()!=null){
                ShopProductCategory parent=shopCategoryDao.findByIdForUpdate(request.getParentId());
                category.setParent(parent);
                category.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }


        BeanUtils.copyProperties(request,category);

        category.setUpdateTime(new Date());

        shopCategoryDao.save(category);

        return category;
    }

    @Transactional
    @Override
    public void deleteShopProductCategoryById(Long id) {
        ShopProductCategory category=shopCategoryDao.findByIdForUpdate(id);
        if(category==null){
            throw new BusinessException("分类不存在");
        }
        if(!category.isLeaf()){
            throw new BusinessException("请先删除下级分类");
        }

        shopCategoryDao.delete(category);
    }

    @Override
    public List<ShopProductCategoryVO> getShopProductCategoryVOList(GetShopProductCategoryListDTO request) {
        QShopProductCategory e=QShopProductCategory.shopProductCategory;

        Predicate p=e.shop.id.eq(request.getShopId());

        if(request.getProductType()!=null){
            p=e.productType.eq(request.getProductType()).and(p);
        }

        List<ShopProductCategoryVO> list=queryList(e,p,e.sort.asc(),shopCategoryVOConver);

        return toLevel(list);
    }


    private List<ShopProductCategoryVO> toLevel(List<ShopProductCategoryVO> all){
        Map<Long,ShopProductCategoryVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));
        List<ShopProductCategoryVO> list=new ArrayList<>();
        for(ShopProductCategoryVO vo:all){
            if(vo.getParentId()!=null){
                ShopProductCategoryVO parent=map.get(vo.getParentId());
                if(parent.getChildren()==null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(vo);
            }else{
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public Page<ShopProductCategoryVO> getShopProductCategoryVOPage(GetShopProductCategoryListDTO request, Pageable pageable) {
        QShopProductCategory e=QShopProductCategory.shopProductCategory;

        Predicate p=e.shop.id.eq(request.getShopId());

        if(request.getProductType()!=null){
            p=e.productType.eq(request.getProductType()).and(p);
        }

        return queryPage(e,p,shopCategoryVOConver,pageable);
    }


}
