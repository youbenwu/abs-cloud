package com.outmao.ebs.data.dto;


import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "MediaDTO", description = "保存文件信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {

    @ApiModelProperty(name = "id", value = "文件ID")
    private Long id;

    @ApiModelProperty(name = "uuid", value = "文件UUID")
    private String uuid;

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "item", value = "绑定对象")
    private BindingItem item;

    @ApiModelProperty(name = "contentType", value = "文件类型 image/movie")
    private String contentType;

    @ApiModelProperty(name = "format", value = "文件格式 如：png")
    private String format;

    @ApiModelProperty(name = "url", value = "文件URL")
    private String url;

    @ApiModelProperty(name = "thumbnailUrl", value = "文件缩略图")
    private String thumbnailUrl;

    @ApiModelProperty(name = "size", value = "文件字节大小")
    private Long size;

    @ApiModelProperty(name = "width", value = "宽度")
    private Integer width;

    @ApiModelProperty(name = "height", value = "高度")
    private Integer height;

    @ApiModelProperty(name = "duration", value = "时长")
    private Double duration;

    @ApiModelProperty(name = "orientation", value = "角度")
    private Integer orientation;

}
