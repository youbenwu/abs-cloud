package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.product.dao.ProductCategoryDao;
import com.outmao.ebs.mall.product.dao.ProductTypeDao;
import com.outmao.ebs.mall.product.domain.ProductCategoryDomain;
import com.outmao.ebs.mall.product.domain.conver.ProductCategoryVOConvert;
import com.outmao.ebs.mall.product.dto.GetProductCategoryListDTO;
import com.outmao.ebs.mall.product.dto.ProductCategoryDTO;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.entity.QProductCategory;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

        category.setKeyword(getKeyword(category));

        productCategoryDao.save(category);

        return category;
    }


    private String getKeyword(ProductCategory data){
        StringBuffer s=new StringBuffer();
        s.append(data.getTitle());
        if(!StringUtils.isEmpty(data.getDescription())){
            s.append(" "+data.getDescription());
        }
        return s.toString();
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

        if(category.getParent()!=null) {
            ProductCategory parent = productCategoryDao.findByIdForUpdate(category.getParent().getId());
            if (parent.getChildren().size() == 1) {
                parent.setLeaf(true);
            }
        }

        productCategoryDao.delete(category);
    }

    @Override
    public List<ProductCategoryVO> getProductCategoryVOList() {
        List<ProductCategoryVO> list=getAll();
        return toLevel(list);
    }

    private List<ProductCategoryVO> toLevel(List<ProductCategoryVO> all){
        Map<Long,ProductCategoryVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));
        List<ProductCategoryVO> list=new ArrayList<>();
        for(ProductCategoryVO vo:all){
            if(vo.getParentId()!=null){
                ProductCategoryVO parent=map.get(vo.getParentId());
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


    private List<ProductCategoryVO> getAll() {
        QProductCategory e=QProductCategory.productCategory;
        List<ProductCategoryVO> list=queryList(e,e.id.isNotNull(),e.sort.asc(),productCategoryVOConvert);
        return list;
    }


    @Override
    public Page<ProductCategoryVO> getProductCategoryVOPage(GetProductCategoryListDTO request, Pageable pageable) {
        QProductCategory e=QProductCategory.productCategory;
        Predicate p=null;

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getProductType()!=null){
            p=e.productType.eq(request.getProductType()).and(p);
        }

        return queryPage(e,p,productCategoryVOConvert,pageable);
    }


    @Transactional
    @Override
    public void sort(List<Long> ids) {
        for(int i=0;i<ids.size();i++){
            productCategoryDao.setSort(ids.get(i),i);
        }
    }


}
