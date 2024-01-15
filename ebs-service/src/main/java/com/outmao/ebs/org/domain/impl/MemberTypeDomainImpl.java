package com.outmao.ebs.org.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.MemberTypeDao;
import com.outmao.ebs.org.domain.MemberTypeDomain;
import com.outmao.ebs.org.domain.conver.MemberTypeVOConver;
import com.outmao.ebs.org.dto.MemberTypeDTO;
import com.outmao.ebs.org.entity.MemberType;
import com.outmao.ebs.org.entity.QMemberType;
import com.outmao.ebs.org.vo.MemberTypeVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class MemberTypeDomainImpl extends BaseDomain implements MemberTypeDomain {


    @Autowired
    private MemberTypeDao memberTypeDao;

    private MemberTypeVOConver memberTypeVOConver=new MemberTypeVOConver();

    @Transactional
    @Override
    public MemberType saveMemberType(MemberTypeDTO request) {
        MemberType type=memberTypeDao.findByMemberIdAndType(request.getMemberId(),request.getType());
        if(type==null){
            type=new MemberType();
            type.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,type);
        memberTypeDao.save(type);

        return type;
    }

    @Transactional
    @Override
    public void deleteMemberTypeById(Long id) {
        memberTypeDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteMemberTypeByMemberId(Long memberId) {
        memberTypeDao.deleteAllByMemberId(memberId);
    }


    @Override
    public List<MemberTypeVO> getMemberTypeVOListByMemberIdIn(Collection<Long> memberIdIn) {
        QMemberType e=QMemberType.memberType;

        Predicate p=e.memberId.in(memberIdIn);

        return queryList(e,p,memberTypeVOConver);
    }




}
