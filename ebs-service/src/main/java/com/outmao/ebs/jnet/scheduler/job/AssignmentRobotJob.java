package com.outmao.ebs.jnet.scheduler.job;

import com.outmao.ebs.jnet.robot.AssignmentRobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 我的外发过期定时器
 * @author yeyi
 * @date 2019/11/04 14:36
 **/
@Component
public class AssignmentRobotJob {

    private static final Logger log = LoggerFactory.getLogger(AssignmentRobotJob.class);

    @Autowired
    AssignmentRobot assignmentRobot;

    //# 外发机器人定时器 by yeyi
    //# 10分钟执行1次
    @Scheduled(cron="0 */10 * * * ?")
    public void process() {
        log.info("AssignmentRobotJob::process::start >------->");
        try {
        	assignmentRobot.createAssignment();
        } catch (Exception e) {
            log.error("AssignmentRobotJob::process::fail", e);
        }
        log.info("AssignmentRobotJob::process::end <-------<");
    }
}
