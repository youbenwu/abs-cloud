package com.outmao.ebs.bbs.dto.plaint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "PlaintDTO", description = "投诉参数")
@Data
public class PlaintDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "toId", value = "被投诉用户ID")
    private Long toId;

    @ApiModelProperty(name = "targetId", value = "被投诉内容ID")
    private Long targetId;

    @ApiModelProperty(name = "targetType", value = "被投诉内容类型 Subject--投诉主题 Post--投诉帖子 Comment--投诉评论")
    private String targetType;

    @ApiModelProperty(name = "itemId", value = "被投诉主题关联内容的ID")
    private Long itemId;

    @ApiModelProperty(name = "itemType", value = "被投诉主题关联内容的类型")
    private String itemType;

    @ApiModelProperty(name = "contact", value = "投诉人联系方式")
    private String contact;

    @ApiModelProperty(name = "content", value = "投诉理由")
    private String content;


}
