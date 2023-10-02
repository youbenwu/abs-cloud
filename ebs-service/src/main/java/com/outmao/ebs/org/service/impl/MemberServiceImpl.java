package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.MemberDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class MemberServiceImpl extends BaseService implements MemberService {


    @Autowired
    private MemberDomain memberDomain;

    @Autowired
    private UserService userService;


    @Transactional
    @Override
    public Member saveMember(MemberDTO request) {
        if(request.getUserId()==null){
            findOrRegisterUser(request);
        }
        return memberDomain.saveMember(request);
    }

    private void findOrRegisterUser(MemberDTO request){

        User user=userService.getUserByUsername(request.getPhone());

        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getPhone());
            registerDTO.setCredentials(request.getPassword());
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",request.getName());

            user=userService.registerUser(registerDTO);
        }

        request.setUserId(user.getId());

    }

    @Override
    public void deleteMemberById(Long id) {
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
        return memberDomain.getMemberVOById(id);
    }

    @Override
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
    public void deleteMemberRoleById(Long id) {
        memberDomain.deleteMemberRoleById(id);
    }

    @Override
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request) {
        return memberDomain.getMemberRoleVOList(request);
    }


}
