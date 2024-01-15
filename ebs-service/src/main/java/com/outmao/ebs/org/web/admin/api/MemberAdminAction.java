package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AccessPermissionGroup(title="组织管理",url="/org",name="",children = {

        @AccessPermissionParent(title = "成员管理",url = "/org/member",name = "",children = {
                @AccessPermission(title = "保存成员",url = "/org/member",name = "save"),
                @AccessPermission(title = "删除成员",url = "/org/member",name = "delete"),
                @AccessPermission(title = "读取成员",url = "/org/member",name = "read"),
                @AccessPermission(title = "设置成员状态",url = "/org/member",name = "status"),
                @AccessPermission(title = "保存成员角色",url = "/org/member/role",name = "save"),
                @AccessPermission(title = "删除成员角色",url = "/org/member/role",name = "delete"),
                @AccessPermission(title = "读取成员角色",url = "/org/member/role",name = "read"),
        }),

})


@Api(value = "admin-org-member", tags = "后台-组织-成员")
@RestController
@RequestMapping("/api/admin/org/member")
public class MemberAdminAction {


    @Autowired
    private MemberService memberService;


    @PreAuthorize("hasPermission(#request.orgId,'/org/member','save')")
    @ApiOperation(value = "保存成员信息", notes = "保存成员信息")
    @PostMapping("/save")
    public Member saveMember(@RequestBody MemberDTO request){
        return memberService.saveMember(request);
    }


    @PreAuthorize("hasPermission('/org/member','delete')")
    @ApiOperation(value = "删除成员信息", notes = "删除成员信息")
    @PostMapping("/delete")
    public void deleteMemberById(Long id){
        memberService.deleteMemberById(id);
    }


    @PreAuthorize("hasPermission('/org/member','save')")
    @ApiOperation(value = "设置成员状态", notes = "设置成员状态")
    @PostMapping("/setStatus")
    public void setMemberStatus(SetMemberStatusDTO request){
        memberService.setMemberStatus(request);
    }


    @PreAuthorize("hasPermission(#request.orgId,'/org/member','read')")
    @ApiOperation(value = "获取成员列表", notes = "获取成员列表")
    @PostMapping("/page")
    public Page<MemberVO> getMemberVOPage(@RequestBody GetMemberListDTO request, Pageable pageable) {
        return memberService.getMemberVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/org/member/role','save')")
    @ApiOperation(value = "设置成员角色", notes = "设置成员角色")
    @PostMapping("/role/set")
    public void setMemberRole(@RequestBody SetMemberRoleDTO request){
        memberService.setMemberRole(request);
    }

    @PreAuthorize("hasPermission('/org/member','save')")
    @ApiOperation(value = "保存成员类型", notes = "保存成员类型")
    @PostMapping("/type/save")
    public void saveMemberType(MemberTypeDTO request){
        memberService.saveMemberType(request);
    }



}
