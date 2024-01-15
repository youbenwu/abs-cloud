package com.outmao.ebs.org.service;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.entity.MemberType;
import com.outmao.ebs.org.entity.MemberVip;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberTypeVO;
import com.outmao.ebs.org.vo.MemberVO;
import com.outmao.ebs.org.vo.MemberVipVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MemberService {


    /**
     *
     * 保存成员信息
     *
     * */
    public Member saveMember(MemberDTO request);


    /**
     *
     * 删除成员信息
     *
     * */
    public void deleteMemberById(Long id);

    /**
     *
     * 设置成员状态
     *
     * */
    public Member setMemberStatus(SetMemberStatusDTO request);


    /**
     *
     * 获取成员信息
     *
     * */
    public Member getMemberById(Long id);


    /**
     *
     * 获取成员信息
     *
     * */
    public Member getMember(Long orgId, Long userId);


    /**
     *
     * 获取成员详情
     *
     * */
    public MemberVO getMemberVOById(Long id);

    /**
     *
     * 获取成员详情
     *
     * */
    public MemberVO getMemberVO(Long orgId,Long userId);


    /**
     *
     * 获取组织所有成员信息
     *
     * */
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable);


    /**
     *
     * 保存成员类型
     *
     * */
    public MemberType saveMemberType(MemberTypeDTO request);


    /**
     *
     * 删除成员类型
     *
     * */
    public void deleteMemberTypeById(Long id);

    /**
     *
     * 删除成员类型
     *
     * */
    public void deleteMemberTypeByMemberId(Long memberId);

    /**
     *
     * 获取成员类型列表
     *
     * */
    public List<MemberTypeVO> getMemberTypeVOListByMemberIdIn(Collection<Long> memberIdIn);


    /**
     *
     * 保存成员角色
     *
     * */
    public MemberRole saveMemberRole(MemberRoleDTO request);


    /**
     *
     * 保存成员角色列表
     *
     * */
    public List<MemberRole> setMemberRole(SetMemberRoleDTO request);

    /**
     *
     * 删除成员角色
     *
     * */
    public void deleteMemberRoleById(Long id);

    /**
     *
     * 删除成员角色
     *
     * */
    public void deleteMemberRoleByMemberId(Long memberId);

    /**
     *
     * 获取成员角色列表
     *
     * */
    public List<MemberRoleVO> getMemberRoleVOListByMemberIdIn(Collection<Long> memberIdIn);



    public MemberVip saveMemberVip(MemberVipDTO request);

    public void deleteMemberVipByMemberId(Long memberId);

    public MemberVip getMemberVipByMemberId(Long memberId);

    public MemberVipVO getMemberVipVOByMemberId(Long memberId);


}
