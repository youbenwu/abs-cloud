package com.outmao.ebs.mall.manufacturer.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


@ApiModel(value = "ManufacturerVO", description = "开发商")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ManufacturerVO {

    @ApiModelProperty(name = "id", value = "id")
    private Long id;


    /**
     * 开发商名称
     */
    @ApiModelProperty(name = "name", value = "开发商名称")
    private String name;


    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;


}
