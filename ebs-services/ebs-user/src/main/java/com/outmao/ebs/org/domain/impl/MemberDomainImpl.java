package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.common.util.Validation;
import com.outmao.ebs.org.dao.*;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.domain.conver.*;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.security.vo.SecurityMember;
import com.outmao.ebs.security.vo.SecurityRole;
import com.outmao.ebs.security.vo.SecurityRolePermission;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UserDomain userDomain;


    private MemberRoleVOConver memberRoleVOConver=new MemberRoleVOConver();


    private MemberVOConver memberVOConver=new MemberVOConver();


    @Autowired
    private SecurityMemberConver securityMemberConver;
    @Autowired
    private SecurityMemberRoleConver securityMemberRoleConver;
    @Autowired
    private SecurityRolePermissionConver securityRolePermissionConver;


    @Transactional
    @Override
    public Member saveMember(MemberDTO request) {
        if(StringUtil.isEmpty(request.getName())){
            throw new BusinessException("名称不能为空");
        }
        if(StringUtil.isEmpty(request.getPhone())){
            throw new BusinessException("手机号不能为空");
        }
        if(!Validation.isPhone(request.getPhone())){
            throw new BusinessException("请填写正确的手机号码");
        }
        Member member=request.getId()==null?null:memberDao.getOne(request.getId());
        if(member==null){

            if(request.getUserId()==null){
                User user=userDomain.getUserByUsername(request.getPhone());
                if(user==null) {
                    user = userDomain.registerUser(new RegisterDTO(Oauth.PHONE.getName(), request.getPhone(), null));
                }
                request.setUserId(user.getId());
            }

            member=new Member();
            member.setCreateTime(new Date());
            member.setUser(userDao.getOne(request.getUserId()));
            member.setOrg(orgDao.getOne(request.getOrgId()));
        }

        BeanUtils.copyProperties(request,member);
        member.setUpdateTime(new Date());

        memberDao.save(member);

        return member;
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
    public void deleteMember(DeleteMemberDTO request) {

        Member m=memberDao.getOne(request.getId());

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
    public List<Member> getMemberListByUserId(Long userId) {
        return memberDao.findAllByUserId(userId);
    }

    @Override
    public MemberVO getMemberVOById(Long id) {
        QMember e= QMember.member;
        return queryOne(e,e.id.eq(id),memberVOConver);
    }

    @Override
    public List<MemberVO> getMemberVOList(Long orgId, Collection<Long> userIdIn) {
        QMember e=QMember.member;
        return queryList(e,e.org.id.eq(orgId).and(e.user.id.in(userIdIn)),memberVOConver);
    }

    @Override
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable) {
        QMember e=QMember.member;
        Predicate p=null;
        if(request.getKeyword()!=null){
            p=e.user.nickname.like("%"+request.getKeyword()+"%").or(e.user.username.like("%"+request.getKeyword()+"%"));
        }
        p=e.org.id.eq(request.getOrgId()).and(p);

        if(request.getUsers()!=null){
            p=e.user.id.in(request.getUsers()).and(p);
        }

        Page<MemberVO> page= queryPage(e,p,memberVOConver,pageable);

        loadMemberRoleList(page.getContent());

        return page;
    }


    private void loadMemberRoleList(List<MemberVO> list){
        List<MemberRoleVO> roles=getMemberRoleVOListByMemberIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));

        Map<Long, MemberVO> listMap=list.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        roles.forEach(t->{
            MemberVO vo=listMap.get(t.getMemberId());
            if(vo.getRoles()==null){
                vo.setRoles(new ArrayList<>());
            }
            vo.getRoles().add(t);
        });


    }

    private List<MemberRoleVO> getMemberRoleVOListByMemberIdIn(Collection<Long> memberIdIn) {
        QMemberRole e=QMemberRole.memberRole;
        return queryList(e,e.member.id.in(memberIdIn),memberRoleVOConver);
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
        List<MemberRole> list=memberRoleDao.findAllByMemberId(request.getMemberId());
        List<Long> mrs=list.stream().map(t->t.getRole().getId()).collect(Collectors.toList());
        List<MemberRole> dels=new ArrayList<>();
        list.stream().forEach(v->{
            if(!request.getRoles().contains(v.getRole().getId())){
                dels.add(v);
            }
        });
        List<MemberRole> saves=new ArrayList<>();
        request.getRoles().forEach(rid->{
            if(!mrs.contains(rid)) {
                MemberRole mr = new MemberRole();
                mr.setMember(memberDao.getOne(request.getMemberId()));
                mr.setRole(roleDao.getOne(rid));
                mr.setCreateTime(new Date());
                saves.add(mr);
            }
        });
        if(dels.size()>0)
        memberRoleDao.deleteAll(dels);
        if(saves.size()>0)
        memberRoleDao.saveAll(saves);

        return saves;
    }

    @Transactional
    @Override
    public void deleteMemberRole(DeleteMemberRoleDTO request) {
        MemberRole mr=memberRoleDao.getOne(request.getId());
        memberRoleDao.delete(mr);
    }


    @Override
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request) {
        QMemberRole e=QMemberRole.memberRole;
        return queryList(e,e.member.id.eq(request.getMemberId()),memberRoleVOConver);
    }



    @Override
    public List<SecurityMember> getSecurityMemberListByUserId(Long userId) {
        QMember e=QMember.member;
        List<SecurityMember> members=queryList(e,e.user.id.eq(userId),securityMemberConver);
        if(members.isEmpty())
            return members;

        members.forEach(t->{
            t.setRoles(new ArrayList<>());
        });

        Map<Long,SecurityMember> memberMap=members.stream().collect(Collectors.toMap(t->t.getId(),t->t));


        QMemberRole ar=QMemberRole.memberRole;
        List<SecurityRole> roles=queryList(ar,ar.member.id.in(members.stream().map(t->t.getId()).collect(Collectors.toList())),securityMemberRoleConver);
        if(roles.isEmpty())
            return members;

        roles.forEach(t->{
            t.setPermissions(new ArrayList<>());
            memberMap.get(t.getMemberId()).getRoles().add(t);
        });

        Map<Long, SecurityRole> roleMap=roles.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        QRolePermission rp=QRolePermission.rolePermission;

        List<SecurityRolePermission> permissions=queryList(rp,rp.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),securityRolePermissionConver);

        if(permissions.isEmpty())
            return members;


        permissions.forEach(t->{
            roleMap.get(t.getRoleId()).getPermissions().add(t);
        });

        return members;
    }


}
