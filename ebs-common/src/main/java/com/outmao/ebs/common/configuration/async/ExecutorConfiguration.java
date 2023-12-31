package com.outmao.ebs.common.configuration.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @title 使用自定义的线程池执行异步任务 , 并设置定时任务的异步处理 
 * @version 1.0
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ExecutorConfiguration implements SchedulingConfigurer, AsyncConfigurer {
    
    private static final Logger LOG = LogManager.getLogger(ExecutorConfiguration.class.getName());

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("async-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
 
    /**
     * @title 异步任务中异常处理 
     * @description 
     * @author Xingbz
     * @createDate 2017年9月11日
     * @return
     * @see AsyncConfigurer#getAsyncUncaughtExceptionHandler()
     * @version 1.0
     *
     */
	
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                LOG.error("/*" + ex.getMessage() + "*/", ex);
                LOG.error("exception method:" + method.getName());
            }
        };
    }



    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        TaskScheduler taskScheduler = taskScheduler();
        taskRegistrar.setTaskScheduler(taskScheduler);
    }
   
    /**
     * 并行任务使用策略：多线程处理
     * 
     * @return ThreadPoolTaskScheduler 线程池
     */
    
     @Bean(destroyMethod = "shutdown")
     public ThreadPoolTaskScheduler taskScheduler() {
         ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
         scheduler.setPoolSize(10);
         scheduler.setThreadNamePrefix("task-");
         scheduler.setWaitForTasksToCompleteOnShutdown(true);
         return scheduler;
     }
     
}

