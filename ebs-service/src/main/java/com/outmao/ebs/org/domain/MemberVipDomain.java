package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.MemberVipDTO;
import com.outmao.ebs.org.entity.MemberVip;
import com.outmao.ebs.org.vo.MemberVipVO;

public interface MemberVipDomain {

    public MemberVip saveMemberVip(MemberVipDTO request);

    public void deleteMemberVipByMemberId(Long memberId);

    public MemberVip getMemberVipByMemberId(Long memberId);

    public MemberVipVO getMemberVipVOByMemberId(Long memberId);



}
