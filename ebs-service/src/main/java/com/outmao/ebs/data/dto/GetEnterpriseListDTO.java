package com.outmao.ebs.data.dto;

import lombok.Data;

@Data
public class GetEnterpriseListDTO {


    private Long userId;
    private Integer[] statusIn;
    private String keyword;


}
