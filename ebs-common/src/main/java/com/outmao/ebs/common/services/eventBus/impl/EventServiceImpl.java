package com.outmao.ebs.common.services.eventBus.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.outmao.ebs.common.services.eventBus.Event;
import com.outmao.ebs.common.services.eventBus.EventListener;
import com.outmao.ebs.common.services.eventBus.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    AsyncEventBus asyncEventBus;

    @Autowired
    EventBus eventBus;



    public void register(EventListener listener) {
        eventBus.register(listener);
        asyncEventBus.register(listener);
    }


    public void unregister(EventListener listener) {
        eventBus.unregister(listener);
        asyncEventBus.unregister(listener);
    }


    @Override
    public void post(Event event) {
        eventBus.post(event);
    }


    @Override
    public void postAsync(Event event) {
        asyncEventBus.post(event);
    }


}
