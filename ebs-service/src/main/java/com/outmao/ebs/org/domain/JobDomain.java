package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Job;
import com.outmao.ebs.org.entity.JobMember;
import com.outmao.ebs.org.vo.JobMemberVO;
import com.outmao.ebs.org.vo.JobVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface JobDomain {

    /*
     *
     * 保存职位
     *
     * */
    public Job saveJob(JobDTO request);

    /*
     *
     * 删除职位
     *
     * */
    public void deleteJob(DeleteJobDTO request);


    /*
     *
     * 获取职位列表
     *
     * */
    public List<JobVO> getJobVOList(GetJobListDTO request);

    /*
     *
     * 保存职位成员
     *
     * */
    public JobMember saveJobMember(JobMemberDTO request);

    /*
     *
     * 保存职位成员列表
     *
     * */
    public List<JobMember> saveJobMemberList(JobMemberListDTO request);

    /*
     *
     * 删除职位成员
     *
     * */
    public void deleteJobMember(DeleteJobMemberDTO request);

    /*
     *
     * 删除职位成员
     *
     * */
    public void deleteJobMemberByMemberId(Long memberId);


    /*
     *
     * 获取职位成员列表
     *
     * */
    public Page<JobMemberVO> getJobMemberVOPage(GetJobMemberListDTO request, Pageable pageable);


}
