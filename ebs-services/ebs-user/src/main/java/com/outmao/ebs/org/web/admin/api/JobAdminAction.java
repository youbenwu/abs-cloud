package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.service.JobService;
import com.outmao.ebs.org.vo.JobMemberVO;
import com.outmao.ebs.org.vo.JobVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "admin-org-job", tags = "后台-组织-职位")
@RestController
@RequestMapping("/api/admin/org/job")
public class JobAdminAction {



    @Autowired
    private JobService jobService;



    /*
     *
     * 保存职位
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job','save')")
    @ApiOperation(value = "保存职位", notes = "保存职位")
    @PostMapping("/save")
    public void saveJob(JobDTO request){
        jobService.saveJob(request);
    }

    /*
     *
     * 删除职位
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job','delete')")
    @ApiOperation(value = "删除职位", notes = "删除职位")
    @PostMapping("/delete")
    public void deleteJob(DeleteJobDTO request){
        jobService.deleteJob(request);
    }

    /*
     *
     * 获取职位列表
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job','read')")
    @ApiOperation(value = "获取职位列表", notes = "获取职位列表")
    @PostMapping("/list")
    public List<JobVO> getJobVOList(GetJobListDTO request){
        return jobService.getJobVOList(request);
    }


    /*
     *
     * 保存职位成员
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job/member','save')")
    @ApiOperation(value = "保存职位成员", notes = "保存职位成员")
    @PostMapping("/member/save")
    public void saveJobMember(JobMemberDTO request){
        jobService.saveJobMember(request);
    }

    /*
     *
     * 保存职位成员列表
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job/member','save')")
    @ApiOperation(value = "保存职位成员列表", notes = "保存职位成员列表")
    @PostMapping("/member/saveList")
    public void saveJobMemberList(JobMemberListDTO request){
        jobService.saveJobMemberList(request);
    }

    /*
     *
     * 删除职位成员
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job/member','delete')")
    @ApiOperation(value = "删除职位成员", notes = "删除职位成员")
    @PostMapping("/member/delete")
    public void deleteJobMember(DeleteJobMemberDTO request){
        jobService.deleteJobMember(request);
    }

    /*
     *
     * 获取职位成员列表
     *
     * */
    @PreAuthorize("hasPermission(#request.orgId,'/org/job/member','read')")
    @ApiOperation(value = "获取职位成员列表", notes = "获取职位成员列表")
    @PostMapping("/member/page")
    public Page<JobMemberVO> getJobMemberVOPage(GetJobMemberListDTO request,Pageable pageable){
        return jobService.getJobMemberVOPage(request,pageable);
    }

    
}
