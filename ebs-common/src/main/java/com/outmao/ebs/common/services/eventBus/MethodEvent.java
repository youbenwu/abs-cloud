package com.outmao.ebs.common.services.eventBus;

import lombok.Data;

@Data
public class MethodEvent implements Event {

    private Class<? extends ActionEvent> action;

    private Object[] args;

    private Object returning;


}
