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
@ApiModel(value = "AdvertUvLog", description = "广告UV")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "portal_AdvertUvLog")
public class AdvertUvLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "userId", value = "点击用户")
    private Long userId;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "date", value = "日期")
    private String date;

    @ApiModelProperty(name = "key", value = "KEY:userId_advertId_date")
    @Column(unique = true)
    private String key;


}
