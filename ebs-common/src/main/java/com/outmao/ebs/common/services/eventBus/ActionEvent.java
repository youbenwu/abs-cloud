package com.outmao.ebs.common.services.eventBus;

public abstract class ActionEvent implements Event {


    public abstract void setValue(MethodEvent methodEvent);

    public boolean async(){
        return true;
    }

}
