package com.outmao.ebs.org.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.org.dao.MemberVipDao;
import com.outmao.ebs.org.domain.MemberVipDomain;
import com.outmao.ebs.org.domain.conver.MemberVipVOConver;
import com.outmao.ebs.org.dto.MemberVipDTO;
import com.outmao.ebs.org.entity.MemberVip;
import com.outmao.ebs.org.entity.QMemberVip;
import com.outmao.ebs.org.vo.MemberVipVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class MemberVipDomainImpl extends BaseDomain implements MemberVipDomain {


    @Autowired
    private MemberVipDao memberVipDao;

    private MemberVipVOConver memberVipVOConver=new MemberVipVOConver();

    @Transactional
    @Override
    public MemberVip saveMemberVip(MemberVipDTO request) {

        MemberVip vip=memberVipDao.findByMemberIdLock(request.getMemberId());
        if(vip==null){
            vip=new MemberVip();
            vip.setCreateTime(new Date());
        }

        vip.setAmount(vip.getAmount()+request.getAmount());

        Date now=new Date();
        Date expireTime=vip.getExpireTime();

        if(expireTime==null||expireTime.before(now)){
            expireTime=now;
        }

        TimeSpan time=request.getTime();

        if(time.getField()==TimeSpan.MONTH){
            expireTime= DateUtil.addMonths(expireTime,time.getValue());
        }else if(time.getField()==TimeSpan.YEAR){
            expireTime= DateUtil.addYears(expireTime,time.getValue());
        }

        vip.setExpireTime(expireTime);

        BeanUtils.copyProperties(request,vip,"amount");

        vip.setUpdateTime(new Date());

        memberVipDao.save(vip);

        return vip;
    }

    @Transactional
    @Override
    public void deleteMemberVipByMemberId(Long memberId) {
        memberVipDao.deleteByMemberId(memberId);
    }

    @Override
    public MemberVip getMemberVipByMemberId(Long memberId) {
        return memberVipDao.findByMemberId(memberId);
    }

    @Override
    public MemberVipVO getMemberVipVOByMemberId(Long memberId) {
        QMemberVip e=QMemberVip.memberVip;
        return queryOne(e,e.memberId.eq(memberId),memberVipVOConver);
    }


}
