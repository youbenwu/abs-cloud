package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告
 *
 */
@ApiModel(value = "AdvertPvLog", description = "广告PV")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "portal_AdvertPvLog")
public class AdvertPvLog implements Serializable {

    private static final long serialVersionUID = 1L;

    //类型 0--爆光 1--点击广告链接 2--点击广告视频 3--扫广告二维码
    public static final int TYPE_SHOW=0;
    public static final int TYPE_PV_URl=1;
    public static final int TYPE_PV_VIDEO=2;
    public static final int TYPE_SCAN_QR_CODE=3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "advertType", value = "广告类型 0--平台广告 1--企业广告 2--个人广告")
    private Integer advertType;

    @ApiModelProperty(name = "userId", value = "点击用户")
    private Long userId;

    @ApiModelProperty(name = "spaceId", value = "点击场所ID")
    private Long spaceId;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "date", value = "日期")
    private String date;

    @ApiModelProperty(name = "type", value = "类型 0--爆光 1--点击广告链接 2--点击广告视频 3--扫广告二维码")
    private int type;



}
