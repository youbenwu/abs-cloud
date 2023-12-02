package com.outmao.ebs.bbs.domain.impl;


import com.outmao.ebs.bbs.common.data.GetSubjectItemList;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.dao.*;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.domain.conver.SubjectBrowseVOConver;
import com.outmao.ebs.bbs.domain.conver.SubjectCollectionVOConver;
import com.outmao.ebs.bbs.domain.conver.SubjectVOConver;
import com.outmao.ebs.bbs.dto.subject.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.data.dao.CategoryDao;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SubjectDomainImpl extends BaseDomain implements SubjectDomain {


    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private SubjectVoteDao subjectVoteDao;

    @Autowired
    private SubjectCollectionDao subjectCollectionDao;

    @Autowired
    private SubjectGradeDao subjectGradeDao;


    @Autowired
    private SubjectBrowseDao subjectBrowseDao;



    private SubjectVOConver subjectVOConver=new SubjectVOConver();
    private SubjectBrowseVOConver subjectBrowseVOConver=new SubjectBrowseVOConver();
    private SubjectCollectionVOConver subjectCollectionVOConver=new SubjectCollectionVOConver();

    @Transactional
    @Override
    public Subject saveSubject(SubjectDTO request) {
        Subject s = request.getId()==null?null:subjectDao.getOne(request.getId());
        if (s==null) {
            s = new Subject();
            if(request.getCategoryId()!=null){
                s.setCategory(categoryDao.getOne(request.getCategoryId()));
            }
            s.setUser(userDao.getOne(request.getUserId()));
            SubjectStats stats=new SubjectStats();
            stats.setSubject(s);
            s.setStats(stats);
            s.setCreateTime(new Date());
            s.setType(request.getType());
            s.setItem(request.getItem());
        }

        BeanUtils.copyProperties(request,s,"type","item");
        s.setUpdateTime(new Date());
        subjectDao.save(s);

        return s;
    }

    @Transactional
    @Override
    public Subject saveSubject(Long userId, Item item,int type) {
        SubjectDTO request =new SubjectDTO();
        request.setType(type);
        request.setUserId(userId);
        request.setTitle(item.getTitle());
        BindingItem bindingItem=new BindingItem();
        BeanUtils.copyProperties(item,bindingItem);
        request.setItem(bindingItem);
        Subject subject=saveSubject(request);
        return subject;
    }


    @Override
    public SubjectVO getSubjectVOById(Long id) {
        QSubject e=QSubject.subject;
        return queryOne(e,e.id.eq(id),subjectVOConver);
    }

    @Override
    public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable) {
        QSubject e=QSubject.subject;
        Predicate p=null;
        if(request.getItemType()!=null){
            p=e.item.type.eq(request.getItemType()).and(p);
        }
        return queryPage(e,p,subjectVOConver,pageable);
    }


    @Override
    public Page<SubjectVO> getSubjectVOPage(GetCollectionSubjectListDTO request, Pageable pageable) {
        QSubject e=QSubject.subject;
        QSubjectCollection c=QSubjectCollection.subjectCollection;
        Predicate p=e.id.eq(c.subject.id).and(c.user.id.eq(request.getUserId()));
        if(request.getItemType()!=null){
            p=e.item.type.eq(request.getItemType()).and(p);
        }
        JPAQuery<Tuple> query=QF.select(subjectVOConver.select(e)).from(e,c).where(p);
        Page<SubjectVO> page=queryPage(e,query,subjectVOConver,pageable);
        for(SubjectVO vo:page.getContent()){
            vo.setFav(true);
        }
        return page;
    }










    @Transactional
    @Override
    public SubjectCollection saveSubjectCollection(SubjectCollectionDTO request) {
        SubjectCollection c = subjectCollectionDao.findByUserIdAndSubjectId(request.getUserId(), request.getSubjectId());
        if (c != null) {
            throw new BusinessException("已收藏");
        }

        c = new SubjectCollection();
        c.setUser(userDao.getOne(request.getUserId()));
        c.setSubject(subjectDao.getOne(request.getSubjectId()));
        c.setCreateTime(new Date());

        BeanUtils.copyProperties(request,c);

        c.setSubjectUserId(c.getSubject().getUser().getId());
        if(c.getSubject().getItem()!=null) {
            c.setItemId(c.getSubject().getItem().getId());
            c.setItemType(c.getSubject().getItem().getType());
        }

        subjectCollectionDao.save(c);

        return c;
    }

    @Transactional
    @Override
    public SubjectCollection modifySubjectCollection(SubjectCollectionDTO request) {

        SubjectCollection c = subjectCollectionDao.findByUserIdAndSubjectId(request.getUserId(), request.getSubjectId());
        if (c == null) {
            throw new BusinessException("未收藏");
        }
        if(request.getMark()!=null){
            c.setMark(request.getMark());
        }
        if(request.getRemark()!=null){
            c.setRemark(request.getRemark());
        }
        subjectCollectionDao.save(c);
        return c;
    }

    @Transactional
    @Override
    public void deleteSubjectCollection(Long userId, Long subjectId) {
        SubjectCollection c = subjectCollectionDao.findByUserIdAndSubjectId(userId, subjectId);
        if (c == null) {
            throw new BusinessException("未收藏");
        }
        subjectCollectionDao.delete(c);
    }


    @Override
    public List<String> getSubjectCollectionMarkList(Long userId) {
        QSubjectCollection e=QSubjectCollection.subjectCollection;
        List<String> list=QF.select(e.mark).from(e).groupBy(e.mark).where(e.user.id.eq(userId)).fetch();
        return list;
    }



    @Transactional
    @Override
    public SubjectVote saveSubjectVote(Long userId, Long subjectId, int vote) {
        SubjectVote r = subjectVoteDao.findByUserIdAndSubjectId(userId, subjectId);
        if (r != null) {
            throw new BusinessException("已评价");
        }
        r = new SubjectVote();
        r.setUser(userDao.getOne(userId));
        r.setSubject(subjectDao.getOne(subjectId));
        r.setVote(vote);
        r.setCreateTime(new Date());
        subjectVoteDao.save(r);
        return r;
    }

    @Transactional
    @Override
    public void deleteSubjectVote(Long userId, Long subjectId, int vote) {
        SubjectVote r = subjectVoteDao.findByUserIdAndSubjectId(userId, subjectId);
        if (r == null || r.getVote() != vote) {
            throw new BusinessException("出错了");
        }
        subjectVoteDao.delete(r);
    }



    @Override
    public Map<String,Long> getSubjectCount(GetSubjectCountDTO request) {

        QSubject e=QSubject.subject;

        List<Tuple> list=QF.select(e.count(),e.item.type).from(e).groupBy(e.item.type)
                .where(e.user.id.eq(request.getUserId()).and(e.item.type.in(request.getItemTypes()))).fetch();

        Map<String,Long> map=new HashMap<>();

        for(Tuple t:list){
            map.put(t.get(e.item.type),t.get(e.count()));
        }

        return map;
    }



    @Override
    public Map<String,Long> getSubjectCollectionCount(GetSubjectCollectionCountDTO request) {

        QSubjectCollection e=QSubjectCollection.subjectCollection;
        List<Tuple> list=QF.select(e.count(),e.itemType).from(e).groupBy(e.itemType)
                .where(e.user.id.eq(request.getUserId()).and(e.itemType.in(request.getItemTypes()))).fetch();

        Map<String,Long> map=new HashMap<>();

        for(Tuple t:list){
            map.put(t.get(e.itemType),t.get(e.count()));
        }

        return map;
    }

    @Transactional
    @Override
    public SubjectGrade saveSubjectGrade(SubjectGradeDTO request) {

        SubjectGrade grade=subjectGradeDao.findByTypeAndUserIdAndSubjectId(request.getType(),request.getUserId(),request.getSubjectId());

        if(grade!=null){
            throw new BusinessException("已评分");
        }


        grade=new SubjectGrade();
        grade.setType(request.getType());
        grade.setUser(userDao.getOne(request.getUserId()));
        grade.setSubject(subjectDao.getOne(request.getSubjectId()));
        grade.setCreateTime(new Date());

        subjectGradeDao.save(grade);

        return grade;
    }


    @Transactional
    @Override
    public SubjectBrowse saveSubjectBrowse(Long userId, Long subjectId) {
        SubjectBrowse browse=subjectBrowseDao.findByUserIdAndSubjectId(userId,subjectId);

        if(browse==null){
            browse=new SubjectBrowse();
            browse.setUserId(userId);
            browse.setSubjectId(subjectId);
            BindingItem item=subjectDao.findItemById(subjectId);
            if(item!=null){
                browse.setItemId(item.getId());
                browse.setItemType(item.getType());
            }
        }

        browse.setCreateTime(new Date());

        subjectBrowseDao.save(browse);

        return browse;
    }


    @Transactional
    @Override
    public void deleteSubjectBrowseById(Long id) {
        subjectBrowseDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteSubjectBrowseList(Long userId, String itemType) {
        subjectBrowseDao.deleteAllByUserIdAndItemType(userId,itemType);
    }

    @Override
    public Page<SubjectBrowse> getSubjectBrowsePage(Long userId, String itemType, Pageable pageable) {
        return subjectBrowseDao.findAllByUserIdAndItemTypeOrderByCreateTimeDesc(userId,itemType,pageable);
    }

    @Override
    public <T extends SubjectItem> Page<SubjectBrowseVO<T>> getSubjectBrowseVOPage(Long userId, String itemType, GetSubjectItemList<T> getSubjectItemList, Pageable pageable) {

        QSubjectBrowse e=QSubjectBrowse.subjectBrowse;

        Page<SubjectBrowseVO<T>> page=queryPage(e,e.userId.eq(userId).and(e.itemType.eq(itemType)),subjectBrowseVOConver,pageable);

        if(page.getContent().isEmpty())
            return page;

        List<Long> itemIds=page.getContent().stream().map(t->t.getItemId()).collect(Collectors.toList());

        List<T> items=getSubjectItemList.getSubjectItemList(itemIds);

        Map<Long,T> itemMap=items.stream().collect(Collectors.toMap(t->t.getSubjectId(),t->t));

        page.getContent().forEach(t->{
            t.setItem(itemMap.get(t.getSubjectId()));
        });


        return page;
    }

    @Override
    public <T extends SubjectItem> Page<SubjectCollectionVO<T>> getSubjectCollectionVOPage(Long userId, String itemType, GetSubjectItemList<T> getSubjectItemList, Pageable pageable) {

        QSubjectCollection e=QSubjectCollection.subjectCollection;

        Page<SubjectCollectionVO<T>> page=queryPage(e,e.user.id.eq(userId).and(e.itemType.eq(itemType)),subjectCollectionVOConver,pageable);

        if(page.getContent().isEmpty())
            return page;

        List<Long> itemIds=page.getContent().stream().map(t->t.getItemId()).collect(Collectors.toList());

        List<T> items=getSubjectItemList.getSubjectItemList(itemIds);

        Map<Long,T> itemMap=items.stream().collect(Collectors.toMap(t->t.getSubjectId(),t->t));

        page.getContent().forEach(t->{
            t.setItem(itemMap.get(t.getSubjectId()));
        });


        return page;
    }


}
