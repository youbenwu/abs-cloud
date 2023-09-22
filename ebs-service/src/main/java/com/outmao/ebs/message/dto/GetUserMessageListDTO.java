package com.outmao.ebs.message.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetUserMessageListDTO {

    private Long userId;

    private List<String> types;

}
