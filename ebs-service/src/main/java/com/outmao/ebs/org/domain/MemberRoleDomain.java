package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.GetMemberRoleListDTO;
import com.outmao.ebs.org.dto.MemberRoleDTO;
import com.outmao.ebs.org.dto.SetMemberRoleDTO;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.vo.MemberRoleVO;

import java.util.Collection;
import java.util.List;

public interface MemberRoleDomain {

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


}
