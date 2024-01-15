package com.outmao.ebs.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value = "Media", description = "文件信息")
@Data
@Entity
@Table(name = "ebs_Media")
public class Media implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @ApiModelProperty(name = "id", value = "文件ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * uuid
     */
    @Column(unique = true)
    private String uuid;

    /**
     * orgId
     */
    private Long orgId;

    /**
     * userId
     */
    private Long userId;

    /**
     * 绑定目标
     */
    @Embedded
    private BindingItem item;

    /**
     *
     * 文件类型 image/studio/pdf
     *
     */
    @ApiModelProperty(name = "contentType", value = "文件类型 image/studio/pdf")
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
