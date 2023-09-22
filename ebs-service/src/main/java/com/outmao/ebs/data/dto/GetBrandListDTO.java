package com.outmao.ebs.data.dto;

import lombok.Data;

@Data
public class GetBrandListDTO {

    private Long userId;
    private Integer[] statusIn;
    private String keyword;


}
