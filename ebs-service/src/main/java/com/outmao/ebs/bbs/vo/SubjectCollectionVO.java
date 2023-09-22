package com.outmao.ebs.bbs.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "SubjectCollectionVO", description = "收藏主题信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SubjectCollectionVO<T> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;
    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;
    @ApiModelProperty(name = "mark", value = "标签")
    private String mark;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    @ApiModelProperty(name = "itemId", value = "绑定ID")
    private Long itemId;
    @ApiModelProperty(name = "itemType", value = "绑定类型")
    private String itemType;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    private T item;

}
