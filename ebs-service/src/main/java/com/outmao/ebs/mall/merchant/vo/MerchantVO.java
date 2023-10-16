package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.data.vo.EnterpriseVO;
import com.outmao.ebs.mall.merchant.common.data.MerchantStatsSetter;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "MerchantVO", description = "商家信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MerchantVO implements MerchantStatsSetter , SimpleUserSetter {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
     *
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败 5--审核成功 7--欠费")
    private int status;

    /**
     *
     * 状态
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "状态")
    private String statusRemark;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户")
    private Long userId;

    private SimpleUserVO user;

    /**
     *
     * 店铺ID
     *
     */
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    /**
     *
     * 商家类型 0--企业 1--个人
     *
     * */
    @ApiModelProperty(name = "type", value = "商家类型 0--企业 1--个人")
    private int type;


    /**
     *
     * 企业ID
     *
     * */
    @ApiModelProperty(name = "enterpriseId", value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(name = "enterprise", value = "企业信息")
    private EnterpriseVO enterprise;

    /**
     *
     * 商家名称
     *
     * */
    @ApiModelProperty(name = "name", value = "商家名称")
    private String name;


    /**
     *
     * 商家简介
     *
     *
     */
    @ApiModelProperty(name = "intro", value = "商家简介")
    private String intro;

    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private Contact contact;


    /**
     *
     * 商家H5地址
     *
     */
    @ApiModelProperty(name = "url", value = "商家H5地址")
    private String url;

    /**
     *
     * 商家二维码地址，扫码访问商家首页
     *
     */
    @ApiModelProperty(name = "qrCode", value = "商家二维码地址，扫码访问商家首页")
    private String qrCode;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


    @ApiModelProperty(name = "stats", value = "统计数据")
    private MerchantStatsVO stats;




}
