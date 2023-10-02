package com.outmao.ebs.clients.shpanhe.vo;

import lombok.Data;

@Data
public class PanheResponse<T> {

    private T data;

    private boolean success;

    private String msg;

}
