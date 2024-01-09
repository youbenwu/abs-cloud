package com.outmao.ebs.thirdpartys.rongcloud.dto;


import lombok.Data;

@Data
public class PushDTO<T> {

    private String[] tags;

    private PushBodyDTO<T> body;

}
