package com.outmao.ebs.org.dto;


import lombok.Data;


@Data
public class GetOrgListDTO {

    private Long userId;

    private String keyword;

    private Integer type;

}
