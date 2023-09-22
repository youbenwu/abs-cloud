package com.outmao.ebs.bbs.dto.subject;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SubjectGradeDTO", description = "主题评分参数")
@Data
public class SubjectGradeDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     *
     * 根据业务分多个评分项
     *
     * 0--总评分
     * 1--评分项1
     * 2--评分项2
     *
     *
     */
    @ApiModelProperty(name = "type", value = "根据业务分多个评分项 0--总评分 1--评分项1 2--评分项2")
    private int type;

    /**
     * 分数 1～5
     */
    @ApiModelProperty(name = "grade", value = "分数 1～5")
    private double grade;


}
