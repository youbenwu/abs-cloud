package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.product.dao.ProductCategoryDao;
import com.outmao.ebs.mall.product.dao.ProductTypeDao;
import com.outmao.ebs.mall.product.domain.ProductCategoryDomain;
import com.outmao.ebs.mall.product.domain.conver.ProductCategoryVOConvert;
import com.outmao.ebs.mall.product.dto.ProductCategoryDTO;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.entity.QProductCategory;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class ProductCategoryDomainImpl extends BaseDomain implements ProductCategoryDomain {


    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductTypeDao productTypeDao;


    private ProductCategoryVOConvert productCategoryVOConvert=new ProductCategoryVOConvert();


    @Transactional
    @Override
    public ProductCategory saveProductCategory(ProductCategoryDTO request) {
        ProductCategory category=request.getId()==null?null:productCategoryDao.findByIdForUpdate(request.getId());

        if(category==null){
            category=new ProductCategory();
            category.setCreateTime(new Date());
            category.setLeaf(true);
            if(request.getParentId()!=null){
                ProductCategory parent=productCategoryDao.findByIdForUpdate(request.getParentId());
                category.setParent(parent);
                category.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }

        if(request.getTypeId()!=null){
            category.setType(productTypeDao.getOne(request.getTypeId()));
        }

        category.setLetter(StringUtil.toPinyin(request.getTitle()));

        BeanUtils.copyProperties(request,category);

        category.setUpdateTime(new Date());

        productCategoryDao.save(category);

        return category;
    }

    @Transactional
    @Override
    public void deleteProductCategoryById(Long id) {
        ProductCategory category=productCategoryDao.findByIdForUpdate(id);
        if(category==null){
            throw new BusinessException("分类不存在");
        }
        if(!category.isLeaf()){
            throw new BusinessException("请先删除下级分类");
        }

        productCategoryDao.delete(category);
    }

    @Override
    public List<ProductCategoryVO> getProductCategoryVOList() {
        QProductCategory e=QProductCategory.productCategory;

        List<ProductCategoryVO> list=queryList(e,e.level.eq(0),e.sort.asc(),productCategoryVOConvert);
        loadProductCategoryVOListChildren(list);
        return list;
    }


    private void loadProductCategoryVOListChildren(List<ProductCategoryVO> list){
        QProductCategory e=QProductCategory.productCategory;
        for(ProductCategoryVO item:list){
            if(!item.isLeaf()){
                List<ProductCategoryVO> children=queryList(e,e.parent.id.eq(item.getId()),e.sort.asc(),productCategoryVOConvert);
                loadProductCategoryVOListChildren(children);
                item.setChildren(children);
            }
        }
    }

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {
        QProductCategory e=QProductCategory.productCategory;
        List<ProductCategoryVO> list=queryList(e,e.level.eq(0),e.sort.asc(),productCategoryVOConvert);
        return list;
    }
}
