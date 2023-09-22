package com.outmao.ebs.common.vo;


import lombok.Data;

@Data
public class Between<T> {

    private T from;

    private T to;

}
