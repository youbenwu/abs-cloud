package com.outmao.ebs.user.dto;

import lombok.Data;


@Data
public class GetIdCardListDTO {

    private String keyword;

    private Integer[] statusIn;


}
