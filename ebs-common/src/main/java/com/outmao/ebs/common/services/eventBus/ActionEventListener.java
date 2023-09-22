package com.outmao.ebs.common.services.eventBus;

public abstract class ActionEventListener<T extends ActionEvent> extends EventListener<T> {

    public boolean async(){
        return true;
    }

}
