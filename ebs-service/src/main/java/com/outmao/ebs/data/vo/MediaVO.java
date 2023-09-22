package com.outmao.ebs.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MediaVO", description = "文件信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MediaVO {

    /**
     * 文件ID
     */
    @ApiModelProperty(name = "id", value = "文件ID")
    private Long id;

    /**
     *
     * 文件类型 image/video/pdf
     *
     */
    @ApiModelProperty(name = "contentType", value = "文件类型 image/video/pdf")
    private String contentType;

    @ApiModelProperty(name = "format", value = "文件格式 如：png")
    private String format;

    /**
     * 文件访问URL
     */
    @ApiModelProperty(name = "url", value = "文件访问URL")
    private String url;

    /**
     * 文件索引图像URL
     */
    @ApiModelProperty(name = "thumbnailUrl", value = "文件索引图像URL")
    private String thumbnailUrl;

    /**
     * 文件大小字节
     */
    @ApiModelProperty(name = "size", value = "文件大小字节")
    private Long size;

    /**
     * 宽度
     */
    @ApiModelProperty(name = "width", value = "宽度")
    private Integer width;

    /**
     * 高度
     */
    @ApiModelProperty(name = "height", value = "高度")
    private Integer height;

    /**
     * 时长
     */
    @ApiModelProperty(name = "duration", value = "时长")
    private Double duration;

    /**
     * 方向
     */
    @ApiModelProperty(name = "orientation", value = "方向")
    private Integer orientation;


}
