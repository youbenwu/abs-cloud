package com.outmao.ebs.common.services.eventBus;


public interface EventService {

    public void register(Object listener);

    public void unregister(Object listener);

    public void post(Event event);

    public void postAsync(Event event);

}
