package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.domain.MemberRoleDomain;
import com.outmao.ebs.org.domain.MemberTypeDomain;
import com.outmao.ebs.org.domain.MemberVipDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.entity.MemberType;
import com.outmao.ebs.org.entity.MemberVip;
import com.outmao.ebs.org.service.DepartmentService;
import com.outmao.ebs.org.service.JobService;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberTypeVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.org.vo.MemberVipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MemberServiceImpl extends BaseService implements MemberService {


    @Autowired
    private MemberDomain memberDomain;

    @Autowired
    private MemberTypeDomain memberTypeDomain;

    @Autowired
    private MemberRoleDomain memberRoleDomain;

    @Autowired
    private MemberVipDomain memberVipDomain;

    @Autowired
    private JobService jobService;

    @Autowired
    private DepartmentService departmentService;

    @Transactional
    @Override
    public Member saveMember(MemberDTO request) {

        Member member= memberDomain.saveMember(request);

        if(request.getTypes()!=null){
            request.getTypes().forEach(t->{
                t.setMemberId(member.getId());
                saveMemberType(t);
            });
        }

        return member;
    }


    @Transactional
    @Override
    public void deleteMemberById(Long id) {
        departmentService.deleteDepartmentMemberByMemberId(id);
        jobService.deleteJobMemberByMemberId(id);
        memberVipDomain.deleteMemberVipByMemberId(id);
        memberRoleDomain.deleteMemberRoleByMemberId(id);
        memberTypeDomain.deleteMemberTypeByMemberId(id);
        memberDomain.deleteMemberById(id);
    }

    @Override
    public Member setMemberStatus(SetMemberStatusDTO request) {
        return memberDomain.setMemberStatus(request);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberDomain.getMemberById(id);
    }

    @Override
    public Member getMember(Long orgId, Long userId) {
        return memberDomain.getMember(orgId,userId);
    }

    @Override
    public MemberVO getMemberVOById(Long id) {

        MemberVO vo= memberDomain.getMemberVOById(id);

        if(vo!=null){
            setMemberType(Arrays.asList(vo));
            setMemberRole(Arrays.asList(vo));
        }

        return vo;
    }

    @Override
    public MemberVO getMemberVO(Long orgId, Long userId) {
        MemberVO vo= memberDomain.getMemberVO(orgId,userId);

        if(vo!=null){
            setMemberType(Arrays.asList(vo));
            setMemberRole(Arrays.asList(vo));
        }

        return vo;
    }

    @Override
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable) {
        Page<MemberVO> page= memberDomain.getMemberVOPage(request,pageable);
        setMemberType(page.getContent());
        setMemberRole(page.getContent());
        return page;
    }

    private void setMemberType(List<MemberVO> list){
        if(list.isEmpty())
            return;
        Map<Long,MemberVO> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        List<MemberTypeVO> types=getMemberTypeVOListByMemberIdIn(map.keySet());
        for (MemberTypeVO t:types){
            MemberVO vo=map.get(t.getMemberId());
            if(vo.getTypes()==null){
                vo.setTypes(new ArrayList<>());
            }
            vo.getTypes().add(t);
        }

    }

    private void setMemberRole(List<MemberVO> list){
        if(list.isEmpty())
            return;
        Map<Long,MemberVO> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        List<MemberRoleVO> roles=getMemberRoleVOListByMemberIdIn(map.keySet());
        for (MemberRoleVO r:roles){
            MemberVO vo=map.get(r.getMemberId());
            if(vo.getRoles()==null){
                vo.setRoles(new ArrayList<>());
            }
            vo.getRoles().add(r);
        }

    }

    @Override
    public MemberType saveMemberType(MemberTypeDTO request) {
        return memberTypeDomain.saveMemberType(request);
    }

    @Override
    public void deleteMemberTypeById(Long id) {
        memberTypeDomain.deleteMemberTypeById(id);
    }

    @Override
    public void deleteMemberTypeByMemberId(Long memberId) {
        memberTypeDomain.deleteMemberTypeByMemberId(memberId);
    }

    @Override
    public List<MemberTypeVO> getMemberTypeVOListByMemberIdIn(Collection<Long> memberIdIn) {
        return memberTypeDomain.getMemberTypeVOListByMemberIdIn(memberIdIn);
    }

    @Override
    public MemberRole saveMemberRole(MemberRoleDTO request) {
        return memberRoleDomain.saveMemberRole(request);
    }

    @Override
    public List<MemberRole> setMemberRole(SetMemberRoleDTO request) {
        return memberRoleDomain.setMemberRole(request);
    }

    @Override
    public void deleteMemberRoleById(Long id) {
        memberRoleDomain.deleteMemberRoleById(id);
    }


    @Override
    public void deleteMemberRoleByMemberId(Long memberId) {
        memberRoleDomain.deleteMemberRoleByMemberId(memberId);
    }



    @Override
    public List<MemberRoleVO> getMemberRoleVOListByMemberIdIn(Collection<Long> memberIdIn) {
        return memberRoleDomain.getMemberRoleVOListByMemberIdIn(memberIdIn);
    }

    @Transactional
    @Override
    public MemberVip saveMemberVip(MemberVipDTO request) {

        MemberVip vip= memberVipDomain.saveMemberVip(request);

        memberDomain.setMemberVip(vip.getMemberId(),vip.getVip());

        return vip;
    }

    @Override
    public void deleteMemberVipByMemberId(Long memberId) {
        memberVipDomain.deleteMemberVipByMemberId(memberId);
    }

    @Override
    public MemberVip getMemberVipByMemberId(Long memberId) {
        return memberVipDomain.getMemberVipByMemberId(memberId);
    }

    @Override
    public MemberVipVO getMemberVipVOByMemberId(Long memberId)
    {
        return memberVipDomain.getMemberVipVOByMemberId(memberId);
    }



}
