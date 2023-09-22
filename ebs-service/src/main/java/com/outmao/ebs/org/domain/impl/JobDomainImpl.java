package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.JobDao;
import com.outmao.ebs.org.dao.JobMemberDao;
import com.outmao.ebs.org.dao.MemberDao;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.domain.JobDomain;
import com.outmao.ebs.org.domain.conver.JobMemberVOConver;
import com.outmao.ebs.org.domain.conver.JobVOConver;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Job;
import com.outmao.ebs.org.entity.JobMember;
import com.outmao.ebs.org.entity.QJob;
import com.outmao.ebs.org.entity.QJobMember;
import com.outmao.ebs.org.vo.JobMemberVO;
import com.outmao.ebs.org.vo.JobVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JobDomainImpl extends BaseDomain implements JobDomain {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private JobMemberDao jobMemberDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrgDao orgDao;

    private JobVOConver jobVOConver=new JobVOConver();

    private JobMemberVOConver jobMemberVOConver=new JobMemberVOConver();


    @Transactional()
    @Override
    public Job saveJob(JobDTO request) {
        Job job= request.getId()==null?null:jobDao.getOne(request.getId());
        if(job==null){
            job=new Job();
            job.setOrg(orgDao.getOne(request.getOrgId()));
            job.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,job);

        job.setUpdateTime(new Date());

        jobDao.save(job);

        return job;
    }

    @Transactional()
    @Override
    public void deleteJob(DeleteJobDTO request) {
        Job job=jobDao.getOne(request.getId());
        jobMemberDao.deleteAllByJobId(request.getId());
        jobDao.delete(job);
    }


    @Override
    public List<JobVO> getJobVOList(GetJobListDTO request) {

        QJob e=QJob.job;

        return queryList(e,e.org.id.eq(request.getOrgId()),jobVOConver);
    }



    @Transactional()
    @Override
    public JobMember saveJobMember(JobMemberDTO request) {
        JobMember m=jobMemberDao.findByJobIdAndMemberId(request.getJobId(),request.getMemberId());
        if(m==null){
            m=new JobMember();
            m.setJob(jobDao.getOne(request.getJobId()));
            m.setMember(memberDao.getOne(request.getMemberId()));
            m.setCreateTime(new Date());
            jobMemberDao.save(m);
            jobDao.membersAdd(request.getJobId(),1);
        }
        return m;
    }

    @Transactional()
    @Override
    public List<JobMember> saveJobMemberList(JobMemberListDTO request) {
        List<Long> memberIds=jobMemberDao.findAllMemberIdByJobId(request.getJobId());
        List<JobMember> list=new ArrayList<>(request.getMembers().size());
        request.getMembers().forEach(memberId->{
            if(!memberIds.contains(memberId)) {
                JobMember m = new JobMember();
                m.setJob(jobDao.getOne(request.getJobId()));
                m.setMember(memberDao.getOne(memberId));
                m.setCreateTime(new Date());
                list.add(m);
            }
        });

        jobMemberDao.saveAll(list);
        jobDao.membersAdd(request.getJobId(),list.size());

        return list;
    }

    @Transactional()
    @Override
    public void deleteJobMember(DeleteJobMemberDTO request) {
        JobMember m=jobMemberDao.findById(request.getId()).orElse(null);
        if(m==null){
            throw new BusinessException("岗位成员不存在");
        }
        jobMemberDao.delete(m);
        jobDao.membersAdd(m.getJob().getId(),-1);
    }

    @Override
    public Page<JobMemberVO> getJobMemberVOPage(GetJobMemberListDTO request, Pageable pageable) {
        QJobMember e=QJobMember.jobMember;

        Predicate p=e.job.id.eq(request.getJobId());

        Page<JobMemberVO> page=queryPage(e,p,jobMemberVOConver,pageable);

        return page;
    }


}
