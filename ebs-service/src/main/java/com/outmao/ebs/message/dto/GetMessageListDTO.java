package com.outmao.ebs.message.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetMessageListDTO {

    private Integer sendType;

    private List<String> types;

    private String keyword;

}
