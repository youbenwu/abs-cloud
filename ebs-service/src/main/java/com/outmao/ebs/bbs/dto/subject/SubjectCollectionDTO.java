package com.outmao.ebs.bbs.dto.subject;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SubjectCollectionDTO", description = "收藏主题参数")
@Data
public class SubjectCollectionDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long   userId;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long   subjectId;

    @ApiModelProperty(name = "mark", value = "标签")
    private String mark;

    @ApiModelProperty(name = "remark", value = "备注主题")
    private String remark;

}
