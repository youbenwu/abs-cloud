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
    private UserDao userDao;


    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private MemberRoleDao memberRoleDao;

    @Autowired
    private DepartmentMemberDao departmentMemberDao;

    @Autowired
    private JobMemberDao jobMemberDao;


    @Autowired
    private RoleDao roleDao;


    private MemberRoleVOConver memberRoleVOConver=new MemberRoleVOConver();

    private MemberVOConver memberVOConver=new MemberVOConver();

    private RolePermissionVOConver rolePermissionVOConver=new RolePermissionVOConver();



    @Transactional
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
        Member member=request.getId()==null?null:memberDao.getOne(request.getId());
        if(member==null){
            member=new Member();
            member.setCreateTime(new Date());
            member.setUser(userDao.getOne(request.getUserId()));
            member.setOrg(orgDao.getOne(request.getOrgId()));
        }

        BeanUtils.copyProperties(request,member);
        member.setUpdateTime(new Date());

        member.setKeyword(getKeyword(member));

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
        Member m=memberDao.getOne(request.getId());
        m.setStatus(request.getStatus());
        m.setStatusRemark(request.getStatusRemark());
        m.setUpdateTime(new Date());
        return memberDao.save(m);
    }

    @Transactional
    @Override
    public void deleteMemberById(Long id) {

        Member m=memberDao.getOne(id);

        memberRoleDao.deleteAllByMemberId(m.getId());
        departmentMemberDao.deleteAllByMemberId(m.getId());
        jobMemberDao.deleteAllByMemberId(m.getId());

        memberDao.delete(m);

    }

    @Override
    public Member getMemberById(Long id) {
        return memberDao.findById(id).orElse(null);
    }


    @Override
    public Member getMember(Long orgId, Long userId) {
        return memberDao.findByOrgIdAndUserId(orgId,userId);
    }


    @Override
    public MemberVO getMemberVOById(Long id) {
        QMember e= QMember.member;
        MemberVO vo= queryOne(e,e.id.eq(id),memberVOConver);
        if(vo!=null){
            loadRoles(vo);
        }
        return vo;
    }


    private void loadRoles(MemberVO vo){

        QMemberRole e=QMemberRole.memberRole;
        List<MemberRoleVO> list=queryList(e,e.member.id.eq(vo.getId()),memberRoleVOConver);

        List<RoleVO> roles=list.stream().map(t->t.getRole()).collect(Collectors.toList());

        loadPermissions(roles);

        vo.setRoles(list);

    }



    private void loadPermissions(List<RoleVO> roles){
        QRolePermission e=QRolePermission.rolePermission;
        List<RolePermissionVO> rolePermissions=queryList(e,e.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),rolePermissionVOConver);

        roles.forEach(t->{
            t.setPermissions(rolePermissions.stream().filter(rp->rp.getRoleId().equals(t.getId())).collect(Collectors.toList()));
        });

    }

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

        p=e.org.id.eq(request.getOrgId()).and(p);


        Page<MemberVO> page= queryPage(e,p,memberVOConver,pageable);

        loadRoles(page.getContent());

        return page;
    }

    private void loadRoles(List<MemberVO> members){

        if(members==null||members.isEmpty())
            return;

        List<Long> idIn=members.stream().map(t->t.getId()).collect(Collectors.toList());

        QMemberRole e=QMemberRole.memberRole;

        List<MemberRoleVO> list=queryList(e,e.member.id.in(idIn),memberRoleVOConver);

        members.forEach(t->{
            t.setRoles(list.stream().filter(r->r.getMemberId().equals(t.getId())).collect(Collectors.toList()));
        });

    }



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
        MemberRole mr=memberRoleDao.getOne(id);
        memberRoleDao.delete(mr);
    }


    @Override
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request) {
        QMemberRole e=QMemberRole.memberRole;
        return queryList(e,e.member.id.eq(request.getMemberId()),memberRoleVOConver);
    }



}
