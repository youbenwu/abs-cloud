package com.outmao.ebs.mall.shop.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ShopVO extends SubjectItemVO {

    /**
     *
     * 店铺ID
     *
     */
    private Long id;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    private Long orgId;

    /**
     *
     * 店铺所属用户
     *
     */
    private Long userId;

    /**
     *
     * 商家ID
     *
     */
    private Long merchantId;

    /**
     *
     * 店铺状态
     *
     */
    private int status;

    /**
     *
     * 店铺备注
     *
     */
    private String statusRemark;

    /**
     *
     * 店铺标题
     *
     */
    private String title;

    /**
     *
     * 店铺副标题
     *
     */
    private String subtitle;

    /**
     *
     * 店铺简介
     *
     */
    private String intro;

    /**
     *
     * 店铺图标
     *
     */
    private String logo;

    /**
     *
     * 店铺封面
     *
     */
    private String image;

    /**
     *
     * 店铺H5地址
     *
     */
    private String url;

    /**
     *
     * 店铺二维码地址，扫码访问店铺首页
     *
     */
    private String qrCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
