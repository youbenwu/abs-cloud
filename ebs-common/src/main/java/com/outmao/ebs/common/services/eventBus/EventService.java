package com.outmao.ebs.common.services.eventBus;


public interface EventService {

    public void register(EventListener listener);

    public void unregister(EventListener listener);

    public void post(Event event);

    public void postAsync(Event event);

}
