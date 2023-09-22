package com.outmao.ebs.common.services.amap.vo;

import lombok.Data;

@Data
public class AmapResult{

    private int status;
    private String info;
    private String infocode;
    private int count;

    public boolean isSuccess(){
        return status==1;
    }

}
