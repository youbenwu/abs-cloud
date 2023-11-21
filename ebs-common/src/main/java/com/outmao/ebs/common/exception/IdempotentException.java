package com.outmao.ebs.common.exception;

public class IdempotentException extends BusinessException{

    private Object data;

    public IdempotentException(){
        super("操作重复");
    }

    public IdempotentException(Object data){
        super("操作重复");
        this.data=data;
    }

}
