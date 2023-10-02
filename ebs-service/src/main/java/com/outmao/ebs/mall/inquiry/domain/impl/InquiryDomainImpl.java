package com.outmao.ebs.mall.inquiry.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.inquiry.dao.InquiryDao;
import com.outmao.ebs.mall.inquiry.domain.InquiryDomain;
import com.outmao.ebs.mall.inquiry.domain.conver.InquiryVOConver;
import com.outmao.ebs.mall.inquiry.dto.GetInquiryListDTO;
import com.outmao.ebs.mall.inquiry.dto.InquiryDTO;
import com.outmao.ebs.mall.inquiry.dto.SetInquiryStatusDTO;
import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import com.outmao.ebs.mall.inquiry.entity.QInquiry;
import com.outmao.ebs.mall.inquiry.vo.StatsInquiryVO;
import com.outmao.ebs.mall.inquiry.vo.InquiryVO;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Component
public class InquiryDomainImpl extends BaseDomain implements InquiryDomain {

    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;


    private InquiryVOConver inquiryVOConver=new InquiryVOConver();

    @Transactional
    @Override
    public Inquiry saveInquiry(InquiryDTO request) {
        Inquiry inquiry=request.getId()==null?null:inquiryDao.getOne(request.getId());

        if(inquiry==null){
            inquiry=new Inquiry();
            inquiry.setCreateTime(new Date());
            if(request.getProductId()!=null) {
                Long shopId = productDao.findShopIdById(request.getProductId());
                if(shopId==null){
                    throw new BusinessException("无此商品");
                }
                Long mId=shopDao.findMerchantIdById(shopId);
                inquiry.setMerchantId(mId);
            }
        }

        BeanUtils.copyProperties(request,inquiry);
        inquiry.setUpdateTime(new Date());

        inquiry.setKeyword(getKeyword(inquiry));

        inquiryDao.save(inquiry);

        return inquiry;
    }




    @Transactional
    @Override
    public Inquiry setInquiryStatus(SetInquiryStatusDTO request) {

        Inquiry inquiry=inquiryDao.getOne(request.getId());

        if(inquiry!=null){
            BeanUtils.copyProperties(request,inquiry);
            inquiry.setUpdateTime(new Date());
        }

        inquiryDao.save(inquiry);

        return inquiry;
    }

    @Transactional
    @Override
    public void deleteInquiryById(Long id) {
        inquiryDao.deleteById(id);
    }

    @Override
    public Inquiry getInquiryById(Long id) {
        return inquiryDao.findById(id).orElse(null);
    }

    @Override
    public Page<Inquiry> getInquiryPage(GetInquiryListDTO request, Pageable pageable) {

        QInquiry e=QInquiry.inquiry;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getMerchantId()!=null){
            p=e.merchantId.eq(request.getMerchantId()).and(p);
        }

        Page<Inquiry> page=p==null?inquiryDao.findAll(pageable):inquiryDao.findAll(p,pageable);


        return page;
    }

    @SetSimpleUser
    @Override
    public Page<InquiryVO> getInquiryVOPage(GetInquiryListDTO request, Pageable pageable) {

        QInquiry e=QInquiry.inquiry;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getMerchantId()!=null){
            p=e.merchantId.eq(request.getMerchantId()).and(p);
        }

        Page<InquiryVO> page=queryPage(e,p,inquiryVOConver,pageable);

        return page;
    }

    private String getKeyword(Inquiry m){
        StringBuffer s=new StringBuffer();
        if(m.getName()!=null){
            s.append(" "+m.getName());
        }
        if(m.getPhone()!=null){
            s.append(" "+m.getPhone());
        }
        if(m.getRemark()!=null){
            s.append(" "+m.getRemark());
        }
        if(m.getProductTitle()!=null){
            s.append(" "+m.getProductTitle());
        }
        if(m.getUserId()!=null) {
            String uk = userDao.findKeywordById(m.getUserId());
            if(uk!=null)
            s.append(" "+uk);
        }
        return s.toString();
    }




    @Override
    public List<StatsInquiryVO> getStatsInquiryVOListByUserIdIn(Collection<Long> userIdIn) {
        QInquiry e=QInquiry.inquiry;

        List<Tuple> list=QF.select(e.userId,e.count()).groupBy(e.userId).from(e).where(e.userId.in(userIdIn)).fetch();

        List<StatsInquiryVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsInquiryVO vo=new StatsInquiryVO();
            vo.setUserId(t.get(e.userId));
            vo.setCount(t.get(e.count()));
            vos.add(vo);
        });

        return vos;
    }

    @Override
    public List<StatsInquiryVO> getStatsInquiryVOListByDays(Date fromTime, Date toTime) {
        QInquiry e=QInquiry.inquiry;

        List<Tuple> list=QF.select(e.count(),e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).groupBy(e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsInquiryVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();
        list.forEach(t->{
            StatsInquiryVO vo=new StatsInquiryVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month()),t.get(e.createTime.dayOfMonth()));
            vo.setTime(calendar.getTime());
            vo.setCount(t.get(e.count()));
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public long getInquiryCount() {
        return inquiryDao.count();
    }
}
