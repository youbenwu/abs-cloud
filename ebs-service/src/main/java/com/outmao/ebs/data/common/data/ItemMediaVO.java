package com.outmao.ebs.data.common.data;



import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel(value = "ItemMediaVO", description = "相册信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ItemMediaVO {
    
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "mediaId", value = "文件ID")
    private Long mediaId;

    /**
     * 0图片，1视频
     */
    @ApiModelProperty(name = "type", value = "0图片，1视频")
    private Integer type;

    /**
     * 状态 0显示，1隐藏
     */
    @ApiModelProperty(name = "status", value = "状态 0显示，1隐藏")
    private Integer status;

    /**
     * 文件URL
     */
    @ApiModelProperty(name = "url", value = "文件URL")
    private String url;

    /**
     * 名称
     */
    @ApiModelProperty(name = "title", value = "标题名称")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty(name = "description", value = "文字描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    /**
     * 图片创建时间
     */
    @ApiModelProperty(name = "createTime", value = "图片创建时间")
    private Date createTime;



}
