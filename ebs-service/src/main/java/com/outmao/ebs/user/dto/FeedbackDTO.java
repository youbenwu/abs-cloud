package com.outmao.ebs.user.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FeedbackDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "name", value = "称号")
    private String name;

    @ApiModelProperty(name = "contact", value = "联系方式")
    private String contact;

    @ApiModelProperty(name = "content", value = "内容")
    private String content;

    @ApiModelProperty(name = "images", value = "图片")
    private String images;

}
