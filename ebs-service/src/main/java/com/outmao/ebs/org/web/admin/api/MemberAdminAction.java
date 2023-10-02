package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.entity.MemberRole;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.vo.MemberRoleVO;
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
import java.util.List;


@Api(value = "account-org-member", tags = "后台-组织-成员")
@RestController
@RequestMapping("/api/admin/org/member")
public class MemberAdminAction {


    @Autowired
    private MemberService memberService;



    @PreAuthorize("hasPermission(#request.orgId,'/org/member','save')")
    @ApiOperation(value = "保存成员信息", notes = "保存成员信息")
    @PostMapping("/save")
    public Member saveMember(MemberDTO request){
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
    public Page<MemberVO> getMemberVOPage(GetMemberListDTO request, Pageable pageable) {
        return memberService.getMemberVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/org/member/role','save')")
    @ApiOperation(value = "保存单个成员角色", notes = "保存单个成员角色")
    @PostMapping("/role/save")
    public void saveMemberRole(MemberRoleDTO request){
        memberService.saveMemberRole(request);
    }

    @PreAuthorize("hasPermission('/org/member/role','save')")
    @ApiOperation(value = "设置成员角色列表", notes = "设置成员角色列表")
    @PostMapping("/member/role/set")
    public List<MemberRole> setMemberRole(@RequestBody SetMemberRoleDTO request){
        return memberService.setMemberRole(request);
    }

    @PreAuthorize("hasPermission('/org/member/role','delete')")
    @ApiOperation(value = "删除单个成员角色", notes = "删除单个成员角色")
    @PostMapping("/role/delete")
    public void deleteMemberRoleById(Long id){
        memberService.deleteMemberRoleById(id);
    }

    @PreAuthorize("hasPermission('/org/member/role','read')")
    @ApiOperation(value = "获取成员角色列表", notes = "获取成员角色列表")
    @PostMapping("/role/list")
    public List<MemberRoleVO> getMemberRoleVOList(GetMemberRoleListDTO request){
        return memberService.getMemberRoleVOList(request);
    }


}
