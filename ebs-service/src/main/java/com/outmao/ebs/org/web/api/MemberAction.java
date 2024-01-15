package com.outmao.ebs.org.web.api;


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




@Api(value = "org-member", tags = "组织-成员")
@RestController
@RequestMapping("/api/org/member")
public class MemberAction {


    @Autowired
    private MemberService memberService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存成员信息", notes = "保存成员信息")
    @PostMapping("/save")
    public void saveMember(@RequestBody MemberDTO request){
        memberService.saveMember(request);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取成员信息", notes = "获取成员信息")
    @PostMapping("/get")
    public MemberVO getMemberVO(Long orgId,Long userId){
        return memberService.getMemberVO(orgId,userId);
    }


}
