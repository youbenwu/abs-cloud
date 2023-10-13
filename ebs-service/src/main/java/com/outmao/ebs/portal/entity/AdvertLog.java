package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告
 *
 */
@ApiModel(value = "AdvertLog", description = "广告日志")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_AdvertStats")
public class AdvertLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "pv", value = "流量")
    private long pv;

    @ApiModelProperty(name = "uv", value = "独立访客，一台电脑24小时以内访问N次计为1次")
    private long uv;

    private Date updateTime;
    

}
