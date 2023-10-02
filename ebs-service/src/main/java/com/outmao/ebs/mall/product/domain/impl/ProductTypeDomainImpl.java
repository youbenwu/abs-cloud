package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.product.dao.ProductTypeAttributeDao;
import com.outmao.ebs.mall.product.dao.ProductTypeAttributeGroupDao;
import com.outmao.ebs.mall.product.dao.ProductTypeDao;
import com.outmao.ebs.mall.product.dao.ProductTypePropertyDao;
import com.outmao.ebs.mall.product.domain.ProductTypeDomain;
import com.outmao.ebs.mall.product.domain.conver.ProductTypeAttributeGroupVOConver;
import com.outmao.ebs.mall.product.domain.conver.ProductTypeAttributeVOConver;
import com.outmao.ebs.mall.product.domain.conver.ProductTypePropertyVOConver;
import com.outmao.ebs.mall.product.domain.conver.ProductTypeVOConver;
import com.outmao.ebs.mall.product.dto.ProductTypeAttributeDTO;
import com.outmao.ebs.mall.product.dto.ProductTypeAttributeGroupDTO;
import com.outmao.ebs.mall.product.dto.ProductTypeDTO;
import com.outmao.ebs.mall.product.dto.ProductTypePropertyDTO;
import com.outmao.ebs.mall.product.entity.*;
import com.outmao.ebs.mall.product.vo.ProductTypeAttributeGroupVO;
import com.outmao.ebs.mall.product.vo.ProductTypeAttributeVO;
import com.outmao.ebs.mall.product.vo.ProductTypePropertyVO;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductTypeDomainImpl extends BaseDomain implements ProductTypeDomain {


    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private ProductTypePropertyDao productTypePropertyDao;
    @Autowired
    private ProductTypeAttributeGroupDao productTypeAttributeGroupDao;
    @Autowired
    private ProductTypeAttributeDao productTypeAttributeDao;

    private ProductTypeVOConver productTypeVOConver=new ProductTypeVOConver();
    private ProductTypeAttributeGroupVOConver productTypeAttributeGroupVOConver=new ProductTypeAttributeGroupVOConver();
    private ProductTypeAttributeVOConver productTypeAttributeVOConver=new ProductTypeAttributeVOConver();
    private ProductTypePropertyVOConver productTypePropertyVOConver=new ProductTypePropertyVOConver();


    @Transactional
    @Override
    public ProductType saveProductType(ProductTypeDTO request) {

        ProductType type=request.getId()==null?null:productTypeDao.getOne(request.getId());

        if(type==null){
            type=new ProductType();
            type.setCreateTime(new Date());
        }

        if(request.getAttributes()!=null) {
            int attrCount = 0;
            for (ProductTypeAttributeGroupDTO g : request.getAttributes()) {
                attrCount += g.getAttributes().size();
            }
            type.setAttributeCount(attrCount);
        }

        if(request.getPropertys()!=null) {
            type.setPropertyCount(request.getPropertys().size());
        }

        BeanUtils.copyProperties(request,type);
        type.setUpdateTime(new Date());

        productTypeDao.save(type);

        if(request.getAttributes()!=null) {
            saveProductTypeAttributeGroupList(type, request.getAttributes());
        }
        if(request.getPropertys()!=null) {
            saveProductTypePropertyList(type, request.getPropertys());
        }

        return type;
    }


    private void saveProductTypePropertyList(ProductType type,List<ProductTypePropertyDTO> data){
        Map<String,ProductTypePropertyDTO> dataMap=data.stream().collect(Collectors.toMap(t->t.getKey(), t->t,(v1,v2)->v1));

        List<ProductTypeProperty> list=productTypePropertyDao.findAllByTypeId(type.getId());

        Map<String,ProductTypeProperty> listMap=list.stream().collect(Collectors.toMap(t->t.getKey(),t->t,(v1,v2)->v1));

        List<ProductTypeProperty> dels=new ArrayList<>();

        list.forEach(t->{
            if(!dataMap.containsKey(t.getKey())){
                dels.add(t);
            }
        });

        List<ProductTypeProperty> saves=new ArrayList<>();


        data.forEach(t->{
            ProductTypeProperty property=listMap.get(t.getKey());
            if(property==null){
                property=new ProductTypeProperty();
                property.setType(type);
                property.setCreateTime(new Date());
            }
            t.setId(property.getId());
            BeanUtils.copyProperties(t,property);
            property.setUpdateTime(new Date());
            saves.add(property);
        });



        if(saves.size()>0)
            productTypePropertyDao.saveAll(saves);
        if(dels.size()>0)
            productTypePropertyDao.deleteAll(dels);
    }


    private void saveProductTypeAttributeGroupList(ProductType type,List<ProductTypeAttributeGroupDTO> data){

        Map<String,ProductTypeAttributeGroupDTO> dataMap=data.stream().collect(Collectors.toMap(t->t.getKey(), t->t,(v1,v2)->v1));

        List<ProductTypeAttributeGroup> list=productTypeAttributeGroupDao.findAllByTypeId(type.getId());

        Map<String,ProductTypeAttributeGroup> listMap=list.stream().collect(Collectors.toMap(t->t.getKey(),t->t,(v1,v2)->v1));

        List<ProductTypeAttributeGroup> dels=new ArrayList<>();

        list.forEach(t->{
            if(!dataMap.containsKey(t.getKey())){
                dels.add(t);
            }
        });

        List<ProductTypeAttributeGroup> saves=new ArrayList<>();


        data.forEach(t->{
            ProductTypeAttributeGroup group=listMap.get(t.getKey());

            if(group==null){
                group=new ProductTypeAttributeGroup();
                group.setType(type);
                group.setCreateTime(new Date());
            }
            t.setId(group.getId());
            BeanUtils.copyProperties(t,group);
            group.setUpdateTime(new Date());
            saves.add(group);
        });

        if(saves.size()>0)
            productTypeAttributeGroupDao.saveAll(saves);
        if(dels.size()>0)
            productTypeAttributeGroupDao.deleteAll(dels);


        for(int i=0;i<saves.size();i++){
            saveProductTypeAttributeList(saves.get(i),data.get(i).getAttributes());
        }


    }


    private void saveProductTypeAttributeList(ProductTypeAttributeGroup group,List<ProductTypeAttributeDTO> data){
        Map<String,ProductTypeAttributeDTO> dataMap=data.stream().collect(Collectors.toMap(t->t.getKey(),t->t,(v1,v2)->v1));

        List<ProductTypeAttribute> list=productTypeAttributeDao.findAllByGroupId(group.getId());

        Map<String,ProductTypeAttribute> listMap=list.stream().collect(Collectors.toMap(t->t.getKey(),t->t,(v1,v2)->v1));

        List<ProductTypeAttribute> dels=new ArrayList<>();

        list.forEach(t->{
            if(!dataMap.containsKey(t.getKey())){
                dels.add(t);
            }
        });

        List<ProductTypeAttribute> saves=new ArrayList<>();

        data.forEach(t->{
            ProductTypeAttribute attr=listMap.get(t.getKey());

            if(attr==null){
                attr=new ProductTypeAttribute();
                attr.setType(group.getType());
                attr.setGroup(group);
                attr.setCreateTime(new Date());
            }
            t.setId(attr.getId());
            BeanUtils.copyProperties(t,attr);
            attr.setUpdateTime(new Date());
            saves.add(attr);
        });

        if(saves.size()>0)
            productTypeAttributeDao.saveAll(saves);
        if(dels.size()>0)
            productTypeAttributeDao.deleteAll(dels);

    }



    @Transactional
    @Override
    public void deleteProductTypeById(Long id) {
        ProductType type=productTypeDao.getOne(id);

        if(type.getAttributeCount()>0){
            throw new BusinessException("请先删除关联的参数");
        }

        if(type.getPropertyCount()>0){
            throw new BusinessException("请先删除关联的属性");
        }

        productTypeDao.delete(type);
    }

    @Override
    public ProductTypeVO getProductTypeVOById(Long id) {

        QProductType e=QProductType.productType;

        ProductTypeVO vo=queryOne(e,e.id.eq(id),productTypeVOConver);

        vo.setPropertys(getProductTypePropertyVOList(id));

        vo.setAttributes(getProductTypeAttributeGroupVOList(id));

        return vo;
    }

    private List<ProductTypePropertyVO> getProductTypePropertyVOList(Long typeId){
        QProductTypeProperty e=QProductTypeProperty.productTypeProperty;

        List<ProductTypePropertyVO> list=queryList(e,e.type.id.eq(typeId),productTypePropertyVOConver);

        return list;

    }

    private List<ProductTypeAttributeGroupVO> getProductTypeAttributeGroupVOList(Long typeId){

        QProductTypeAttributeGroup e=QProductTypeAttributeGroup.productTypeAttributeGroup;

        List<ProductTypeAttributeGroupVO> list=queryList(e,e.type.id.eq(typeId),productTypeAttributeGroupVOConver);

        list.forEach(t->{
            t.setAttributes(getProductTypeAttributeVOList(t.getId()));
        });

        return list;

    }

    private List<ProductTypeAttributeVO> getProductTypeAttributeVOList(Long groupId){
        QProductTypeAttribute e=QProductTypeAttribute.productTypeAttribute;

        List<ProductTypeAttributeVO> list=queryList(e,e.group.id.eq(groupId),productTypeAttributeVOConver);

        return list;
    }


    @Override
    public List<ProductTypeVO> getProductTypeVOList() {

        QProductType e=QProductType.productType;

        List<ProductTypeVO> list=queryList(e,e.id.gt(0),productTypeVOConver);

        return list;
    }



    @Transactional
    @Override
    public ProductTypeAttribute saveProductTypeAttribute(ProductTypeAttributeDTO request) {

        if(productTypeAttributeDao.existsByTypeIdAndKeyAndIdNot(request.getTypeId(),request.getKey(),request.getId())){
            throw new BusinessException("KEY重复");
        }

        ProductTypeAttribute a=request.getId()==null?null:productTypeAttributeDao.getOne(request.getId());
        if(a==null){
            a=new ProductTypeAttribute();
            a.setType(productTypeDao.getOne(request.getTypeId()));
            a.setCreateTime(new Date());
            a.getType().setAttributeCount(a.getType().getAttributeCount()+1);
        }
        BeanUtils.copyProperties(request,a);
        a.setUpdateTime(new Date());
        productTypeAttributeDao.save(a);
        return a;
    }

    @Transactional
    @Override
    public void deleteProductTypeAttributeById(Long id) {
        ProductTypeAttribute a=productTypeAttributeDao.getOne(id);
        a.getType().setAttributeCount(a.getType().getAttributeCount()-1);
        productTypeAttributeDao.delete(a);

    }

    @Transactional
    @Override
    public ProductTypeAttributeGroup saveProductTypeAttributeGroup(ProductTypeAttributeGroupDTO request) {

        if(productTypeAttributeGroupDao.existsByTypeIdAndKeyAndIdNot(request.getTypeId(),request.getKey(),request.getId())){
            throw new BusinessException("KEY重复");
        }

        ProductTypeAttributeGroup g=request.getId()==null?null:productTypeAttributeGroupDao.getOne(request.getId());
        if(g==null){
            g=new ProductTypeAttributeGroup();
            g.setType(productTypeDao.getOne(request.getTypeId()));
            g.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,g);
        g.setUpdateTime(new Date());
        productTypeAttributeGroupDao.save(g);
        return g;
    }

    @Transactional
    @Override
    public void deleteProductTypeAttributeGroupById(Long id) {
        productTypeAttributeGroupDao.deleteById(id);
    }

    @Transactional
    @Override
    public ProductTypeProperty saveProductTypeProperty(ProductTypePropertyDTO request) {

        if(productTypePropertyDao.existsByTypeIdAndKeyAndIdNot(request.getTypeId(),request.getKey(),request.getId())){
            throw new BusinessException("KEY重复");
        }
        ProductTypeProperty p=request.getId()==null?null:productTypePropertyDao.getOne(request.getId());
        if(p==null){
            p=new ProductTypeProperty();
            p.setType(productTypeDao.getOne(request.getTypeId()));
            p.setCreateTime(new Date());
            p.getType().setPropertyCount(p.getType().getPropertyCount()+1);
        }
        BeanUtils.copyProperties(request,p);
        p.setUpdateTime(new Date());
        productTypePropertyDao.save(p);
        return p;
    }

    @Transactional
    @Override
    public void deleteProductTypePropertyById(Long id) {
        ProductTypeProperty p =productTypePropertyDao.getOne(id);
        p.getType().setPropertyCount(p.getType().getPropertyCount()-1);
        productTypePropertyDao.delete(p);
    }


}
