package com.outmao.ebs.common.services.eventBus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


public abstract class EventListener<T extends Event> implements ApplicationListener<ApplicationPreparedEvent> {

    @Autowired
    protected EventService eventService;

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        EventListener listener = applicationContext.getBean(this.getClass());
        System.out.println("/* aspect register:"+listener.getClass().toString()+" */");
        eventService.register(listener);
    }


    public abstract void onEvent(T event);



}
