package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.security.vo.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberServiceImpl extends BaseService implements MemberService {


    @Autowired
    private MemberDomain memberDomain;


    @Override
    public Member saveMember(MemberDTO request) {
        return memberDomain.saveMember(request);
    }

    @Override
    public Member getMember(Long orgId, Long userId) {
        return memberDomain.getMember(orgId,userId);
    }

    @Override
    public void deleteMember(DeleteMemberDTO request) {
        memberDomain.deleteMember(request);
    }

    @Override
    public Member setMemberStatus(SetMemberStatusDTO request) {
        return memberDomain.setMemberStatus(request);
    }


    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable) {
        return memberDomain.getMemberVOPage(request,pageable);
    }

    @Override
    public MemberRole saveMemberRole(MemberRoleDTO request) {
        return memberDomain.saveMemberRole(request);
    }

    @Override
    public List<MemberRole> setMemberRole(SetMemberRoleDTO request) {
        return memberDomain.setMemberRole(request);
    }

    @Override
    public void deleteMemberRole(DeleteMemberRoleDTO request) {
        memberDomain.deleteMemberRole(request);
    }

    @Override
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request) {
        return memberDomain.getMemberRoleVOList(request);
    }

    @Override
    public List<SecurityMember> getSecurityMemberListByUserId(Long userId) {
        return memberDomain.getSecurityMemberListByUserId(userId);
    }

}
