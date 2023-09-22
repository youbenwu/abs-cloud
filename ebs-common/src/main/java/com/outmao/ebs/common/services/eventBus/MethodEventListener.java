package com.outmao.ebs.common.services.eventBus;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
public class MethodEventListener extends EventListener<MethodEvent> {


    @Subscribe
    @Override
    public void onEvent(MethodEvent event) {

        System.out.println("/* MethodEventListener MethodEvent */");

        ActionEvent actionEvent=getActionEvent(event);
        if(actionEvent.async()) {
            eventService.postAsync(actionEvent);
        }else{
            eventService.post(actionEvent);
        }

    }

    private ActionEvent getActionEvent(MethodEvent event){
        try {
            ActionEvent actionEvent= event.getAction().newInstance();
            actionEvent.setValue(event);
            return actionEvent;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
