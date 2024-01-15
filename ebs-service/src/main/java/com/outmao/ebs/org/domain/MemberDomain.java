package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.vo.MemberVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberDomain {

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
     * 设置成员VIP等级
     *
     * */
    public Member setMemberVip(Long id,int vip);


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



}
