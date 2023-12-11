package com.outmao.ebs.jnet.scheduler.job;

import com.outmao.ebs.jnet.domain.index.IndexDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 首页功能模块使用次数自动增加定时器
 * @author yeyi
 * @date 2019/9/24 14:35
 **/
@Component
public class FunctionJob {

    private static final Logger log = LoggerFactory.getLogger(FunctionJob.class);

    @Autowired
    private IndexDomain indexDomain;

    // # 我的外发过期定时器 by yeyi
    //# 1小时执行1次
    @Scheduled(cron="0 0 1 * * ?")
    public void process() {
        log.info("FunctionJob::process::start >------->");
        try {
            indexDomain.randomAddFunctionUseCount();
        } catch (Exception e) {
            log.error("FunctionJob::process::fail", e);
        }
        log.info("FunctionJob::process::end <-------<");
    }


}
