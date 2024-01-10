package com.outmao.ebs.data.dto;


import lombok.Data;

@Data
public class GetCategoryListDTO {

    private Long orgId;

    private Long targetId;

    private String type;


}
