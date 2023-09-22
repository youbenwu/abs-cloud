package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetArticleCategoryListDTO {

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    private List<Integer> statusIn;

}
