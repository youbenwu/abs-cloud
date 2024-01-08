package com.outmao.ebs.sys.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FeedbackDTO {

    @ApiModelProperty(name = "userId", value = "反馈用户ID")
    private Long userId;

    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    @ApiModelProperty(name = "contact", value = "联系方式")
    private String contact;

    //0--用户反馈 1--用户投诉
    @ApiModelProperty(name = "type", value = "10--迁眼聊天-异常反馈 11--迁眼聊天-用户投诉")
    private int type;

    //投诉项 逗号隔开
    @ApiModelProperty(name = "items", value = "反馈项 逗号隔开")
    private String items;

    @ApiModelProperty(name = "content", value = "内容")
    private String content;

    @ApiModelProperty(name = "images", value = "图片 逗号隔开")
    private String images;


}
