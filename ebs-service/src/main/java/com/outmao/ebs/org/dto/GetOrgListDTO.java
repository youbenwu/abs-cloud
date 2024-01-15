package com.outmao.ebs.org.dto;


import lombok.Data;


@Data
public class GetOrgListDTO {

    private Integer type;

    private Long userId;

    private String keyword;


}
