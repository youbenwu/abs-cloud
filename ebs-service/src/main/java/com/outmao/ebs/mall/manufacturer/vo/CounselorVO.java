package com.outmao.ebs.mall.manufacturer.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "CounselorVO", description = "置业顾问信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class CounselorVO {


    @ApiModelProperty(name = "id", value = "id")
    private Long id;
    

    /**
     * 用户
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    /**
     * 所属开发商
     */
    @ApiModelProperty(name = "manufacturerId", value = "所属开发商ID")
    private Long manufacturerId;



    @ApiModelProperty(name = "manufacturerName", value = "所属开发商")
    private String manufacturerName;



    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;



}
