package com.outmao.ebs.org.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.*;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.domain.MemberRoleDomain;
import com.outmao.ebs.org.domain.conver.MemberRoleVOConver;
import com.outmao.ebs.org.domain.conver.MemberVOConver;
import com.outmao.ebs.org.domain.conver.RolePermissionVOConver;
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
public class MemberRoleDomainImpl extends BaseDomain implements MemberRoleDomain {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberRoleDao memberRoleDao;

    @Autowired
    private RoleDao roleDao;

    private MemberRoleVOConver memberRoleVOConver=new MemberRoleVOConver();



    @Transactional
    @Override
    public MemberRole saveMemberRole(MemberRoleDTO request) {
        MemberRole mr=memberRoleDao.findByMemberIdAndRoleId(request.getMemberId(),request.getRoleId());
        if(mr==null){
            mr=new MemberRole();
            mr.setMember(memberDao.getOne(request.getMemberId()));
            mr.setRole(roleDao.getOne(request.getRoleId()));
            mr.setCreateTime(new Date());
            memberRoleDao.save(mr);
        }
        return mr;
    }



    @Transactional
    @Override
    public List<MemberRole> setMemberRole(SetMemberRoleDTO request) {
        Member member=memberDao.getOne(request.getMemberId());
        List<MemberRole> roles=memberRoleDao.findAllByMemberId(member.getId());
        Map<Long,MemberRole> roleMap=roles.stream().collect(Collectors.toMap(t->t.getRole().getId(),t->t));

        List<MemberRole> list=new ArrayList<>();

        request.getRoles().forEach(roleId->{
            MemberRole mr=roleMap.get(roleId);
            if(mr==null){
                mr = new MemberRole();
                mr.setMember(member);
                mr.setRole(roleDao.getOne(roleId));
                mr.setCreateTime(new Date());
            }
            list.add(mr);
        });

        memberRoleDao.saveAll(list);

        memberRoleDao.deleteAllByMemberIdAndRoleIdNotIn(member.getId(),request.getRoles());

        return list;
    }


    @Transactional
    @Override
    public void deleteMemberRoleById(Long id) {
        memberRoleDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteMemberRoleByMemberId(Long memberId) {
        memberRoleDao.deleteAllByMemberId(memberId);
    }

    @Override
    public List<MemberRoleVO> getMemberRoleVOListByMemberIdIn(Collection<Long> memberIdIn) {
        QMemberRole e=QMemberRole.memberRole;
        return queryList(e,e.member.id.in(memberIdIn),memberRoleVOConver);
    }


}
