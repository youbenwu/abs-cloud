package com.outmao.ebs.org.service;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.vo.MemberRoleVO;
import com.outmao.ebs.org.vo.MemberVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 获取组织所有成员信息
     *
     * */
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable);



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
     * 获取成员角色列表
     *
     * */
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request);



}
