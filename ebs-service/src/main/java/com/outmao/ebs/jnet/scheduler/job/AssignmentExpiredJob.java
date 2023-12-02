package com.outmao.ebs.jnet.scheduler.job;

import com.outmao.ebs.jnet.domain.assignment.AssignmentDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 我的外发过期定时器
 * @author yeyi
 * @date 2019/9/24 14:36
 **/
@Component
public class AssignmentExpiredJob {

    private static final Logger log = LoggerFactory.getLogger(AssignmentExpiredJob.class);

    @Autowired
    private AssignmentDomain assignmentDomain;

    //# 我的外发过期定时器 by yeyi
    //# 1小时执行1次
    @Scheduled(cron="0 0 */1 * * ?")
    public void process() {
        log.info("AssignmentExpiredJob::process::start >------->");
        try {
            assignmentDomain.checkAssignmentExpired();
        } catch (Exception e) {
            log.error("AssignmentExpiredJob::process::fail", e);
        }
        log.info("AssignmentExpiredJob::process::end <-------<");
    }
}
