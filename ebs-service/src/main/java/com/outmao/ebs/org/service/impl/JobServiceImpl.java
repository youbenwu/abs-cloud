package com.outmao.ebs.org.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.JobDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Job;
import com.outmao.ebs.org.entity.JobMember;
import com.outmao.ebs.org.service.JobService;
import com.outmao.ebs.org.vo.JobMemberVO;
import com.outmao.ebs.org.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl extends BaseService implements JobService {

    @Autowired
    private JobDomain jobDomain;


    @Override
    public Job saveJob(JobDTO request) {
        return jobDomain.saveJob(request);
    }

    @Override
    public void deleteJob(DeleteJobDTO request) {
        jobDomain.deleteJob(request);
    }

    @Override
    public List<JobVO> getJobVOList(GetJobListDTO request) {
        return jobDomain.getJobVOList(request);
    }


    @Override
    public JobMember saveJobMember(JobMemberDTO request) {
        return jobDomain.saveJobMember(request);
    }

    @Override
    public List<JobMember> saveJobMemberList(JobMemberListDTO request) {
        return jobDomain.saveJobMemberList(request);
    }

    @Override
    public void deleteJobMember(DeleteJobMemberDTO request) {
        jobDomain.deleteJobMember(request);
    }

    @Override
    public Page<JobMemberVO> getJobMemberVOPage(GetJobMemberListDTO request, Pageable pageable) {
        return jobDomain.getJobMemberVOPage(request,pageable);
    }


}
