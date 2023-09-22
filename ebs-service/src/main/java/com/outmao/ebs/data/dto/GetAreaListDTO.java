package com.outmao.ebs.data.dto;

import lombok.Data;

@Data
public class GetAreaListDTO {

    private Long parentId;

    private Integer level;

    private String keyword;

}
