package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.portal.dao.ArticleCategoryDao;
import com.outmao.ebs.portal.domain.ArticleCategoryDomain;
import com.outmao.ebs.portal.domain.conver.ArticleCategoryVOConvert;
import com.outmao.ebs.portal.dto.ArticleCategoryDTO;
import com.outmao.ebs.portal.dto.GetArticleCategoryListDTO;
import com.outmao.ebs.portal.dto.SetArticleCategoryStatusDTO;
import com.outmao.ebs.portal.entity.ArticleCategory;
import com.outmao.ebs.portal.entity.QArticleCategory;
import com.outmao.ebs.portal.vo.ArticleCategoryVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Component
public class ArticleCategoryDomainImpl extends BaseDomain implements ArticleCategoryDomain {


    @Autowired
    private ArticleCategoryDao articleCategoryDao;

    private ArticleCategoryVOConvert articleCategoryVOConvert=new ArticleCategoryVOConvert();


    @Transactional
    @Override
    public ArticleCategory saveArticleCategory(ArticleCategoryDTO request) {
        ArticleCategory category=request.getId()==null?null:articleCategoryDao.findByIdForUpdate(request.getId());

        if(category==null){
            category=new ArticleCategory();
            category.setOrgId(request.getOrgId());
            category.setCreateTime(new Date());
            category.setLeaf(true);
            if(request.getParentId()!=null){
                ArticleCategory parent=articleCategoryDao.findByIdForUpdate(request.getParentId());
                category.setParent(parent);
                category.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }

        security.hasPermission(category.getOrgId(),null);

        category.setLetter(StringUtil.toPinyin(request.getTitle()));

        BeanUtils.copyProperties(request,category,"orgId");

        category.setUpdateTime(new Date());

        articleCategoryDao.save(category);

        return category;
    }

    @Transactional
    @Override
    public ArticleCategory setArticleCategoryStatus(SetArticleCategoryStatusDTO request) {
        ArticleCategory category=articleCategoryDao.getOne(request.getId());
        security.hasPermission(category.getOrgId(),null);
        category.setStatus(request.getStatus());
        articleCategoryDao.save(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteArticleCategoryById(Long id) {
        ArticleCategory category=articleCategoryDao.findByIdForUpdate(id);
        if(category==null){
            throw new BusinessException("分类不存在");
        }
        if(!category.isLeaf()){
            throw new BusinessException("请先删除下级分类");
        }
        security.hasPermission(category.getOrgId(),null);
        articleCategoryDao.delete(category);
    }

    @Override
    public List<ArticleCategoryVO> getArticleCategoryVOList(GetArticleCategoryListDTO request) {

        security.hasPermission(request.getOrgId(),null);

        QArticleCategory e=QArticleCategory.articleCategory;


        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId()).and(p);
        }

        if(request.getStatusIn()==null){
            p=e.status.eq(0).and(p);
        }else{
            p=e.status.in(request.getStatusIn()).and(p);
        }

        p=e.level.eq(0).and(p);

        List<ArticleCategoryVO> list=queryList(e,p,e.sort.asc(),articleCategoryVOConvert);
        loadArticleCategoryVOListChildren(list);
        return list;
    }

    private void loadArticleCategoryVOListChildren(List<ArticleCategoryVO> list){
        QArticleCategory e=QArticleCategory.articleCategory;
        for(ArticleCategoryVO item:list){
            if(!item.isLeaf()){
                List<ArticleCategoryVO> children=queryList(e,e.parent.id.eq(item.getId()),e.sort.asc(),articleCategoryVOConvert);
                loadArticleCategoryVOListChildren(children);
                item.setChildren(children);
            }
        }
    }



}
