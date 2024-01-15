package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.MemberType;
import com.outmao.ebs.org.vo.MemberTypeVO;

import java.util.Collection;
import java.util.List;

public interface MemberTypeDomain {

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



}
