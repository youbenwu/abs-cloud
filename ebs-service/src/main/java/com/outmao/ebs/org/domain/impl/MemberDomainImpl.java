package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.*;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.domain.conver.*;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import com.outmao.ebs.user.common.annotation.AutoRegisterUser;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MemberDomainImpl extends BaseDomain implements MemberDomain {


    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private DepartmentMemberDao departmentMemberDao;


    private MemberVOConver memberVOConver=new MemberVOConver();


    @Transactional
    @AutoRegisterUser
    @Override
    public Member saveMember(MemberDTO request) {
//        if(StringUtil.isEmpty(request.getName())){
//            throw new BusinessException("名称不能为空");
//        }
//        if(StringUtil.isEmpty(request.getPhone())){
//            throw new BusinessException("手机号不能为空");
//        }
//        if(!Validation.isPhone(request.getPhone())){
//            throw new BusinessException("请填写正确的手机号码");
//        }
        Member member=request.getId()==null?null:memberDao.findByIdLock(request.getId());
        if(member==null){
            member=new Member();
            member.setCreateTime(new Date());
            member.setUser(userDao.getOne(request.getUserId()));
            member.setOrg(orgDao.getOne(request.getOrgId()));
        }

        BeanUtils.copyProperties(request,member);

        member.setKeyword(getKeyword(member));

        member.setUpdateTime(new Date());

        memberDao.save(member);

        return member;
    }


    private String getKeyword(Member data){
        StringBuffer s=new StringBuffer();
        if(!StringUtils.isEmpty(data.getName())){
            s.append(" "+data.getName());
        }
        if(!StringUtils.isEmpty(data.getPhone())){
            s.append(" "+data.getPhone());
        }
        if(!StringUtils.isEmpty(data.getEmail())){
            s.append(" "+data.getEmail());
        }
        return s.toString().trim();
    }


    @Transactional
    @Override
    public Member setMemberStatus(SetMemberStatusDTO request) {
        Member m=memberDao.findByIdLock(request.getId());
        m.setStatus(request.getStatus());
        m.setStatusRemark(request.getStatusRemark());
        m.setUpdateTime(new Date());
        return memberDao.save(m);
    }

    @Transactional
    @Override
    public Member setMemberVip(Long id, int vip) {
        Member member=memberDao.findByIdLock(id);
        member.setVip(vip);
        memberDao.save(member);
        return member;
    }

    @Transactional
    @Override
    public void deleteMemberById(Long id) {

        departmentMemberDao.deleteAllByMemberId(id);

        memberDao.deleteById(id);

    }



    @Override
    public Member getMemberById(Long id) {
        return memberDao.findById(id).orElse(null);
    }


    @Override
    public Member getMember(Long orgId, Long userId) {
        return memberDao.findByOrgIdAndUserId(orgId,userId);
    }


    @SetSimpleUser
    @Override
    public MemberVO getMemberVOById(Long id) {
        QMember e= QMember.member;
        MemberVO vo= queryOne(e,e.id.eq(id),memberVOConver);
        return vo;
    }

    @SetSimpleUser
    @Override
    public MemberVO getMemberVO(Long orgId, Long userId) {
        QMember e= QMember.member;
        MemberVO vo= queryOne(e,e.org.id.eq(orgId).and(e.user.id.eq(userId)),memberVOConver);
        return vo;
    }

    @SetSimpleUser
    @Override
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable) {

        QMember e=QMember.member;

        Predicate p=null;

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getUsers()!=null){
            p=e.user.id.in(request.getUsers()).and(p);
        }

        if(request.getOrgId()!=null) {
            p = e.org.id.eq(request.getOrgId()).and(p);
        }

        if(request.getTypes()!=null&&request.getTypes().size()>0){
            QMemberType t=QMemberType.memberType;
            p=e.id.eq(t.memberId).and(t.type.in(request.getTypes())).and(p);
        }

        Page<MemberVO> page= queryPage(e,p,memberVOConver,pageable);

        return page;
    }


}
