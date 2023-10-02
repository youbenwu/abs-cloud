package com.outmao.ebs.mall.store.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantSetter;
import com.outmao.ebs.mall.store.common.data.StoreStatsSetter;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import com.outmao.ebs.mall.store.entity.StoreContact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


@ApiModel(value = "StoreVO", description = "门店信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StoreVO extends SubjectItemVO implements SimpleMerchantSetter, StoreStatsSetter {

    /**
     *
     * 门店ID
     *
     */
    @ApiModelProperty(name = "id", value = "门店ID")
    private Long id;

    /**
     *
     * 0--门店 1--仓库
     *
     */
    @ApiModelProperty(name = "type", value = "0--门店 1--仓库")
    private int type;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     *
     * 商家ID
     *
     *
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "merchant", value = "商家信息")
    private SimpleMerchantVO merchant;

    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private StoreContact contact;


    /**
     *
     * 店铺标题
     *
     */
    @ApiModelProperty(name = "title", value = "店铺标题",required = true)
    private String title;

    /**
     *
     * 店铺副标题
     *
     */
    @ApiModelProperty(name = "subtitle", value = "店铺副标题")
    private String subtitle;

    /**
     *
     * 店铺简介
     *
     */
    @ApiModelProperty(name = "intro", value = "店铺简介")
    private String intro;

    /**
     *
     * 店铺图标
     *
     */
    @ApiModelProperty(name = "logo", value = "店铺图标")
    private String logo;

    /**
     *
     * 店铺封面
     *
     */
    @ApiModelProperty(name = "image", value = "店铺封面")
    private String image;

    /**
     *
     * 主营区域
     *
     */
    @ApiModelProperty(name = "主营区域", value = "主营区域")
    private String businessArea;

    /**
     *
     * 店铺H5地址
     *
     */
    @ApiModelProperty(name = "url", value = "店铺H5地址")
    private String url;

    /**
     *
     * 店铺二维码地址，扫码访问店铺首页
     *
     */
    @ApiModelProperty(name = "qrCode", value = "店铺二维码地址，扫码访问店铺首页")
    private String qrCode;

    /**
     *
     * 店铺状态
     *
     */
    @ApiModelProperty(name = "status", value = "店铺状态0-未审核 1-正常 2-禁用")
    private int status;

    /**
     *
     * 店铺备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "店铺备注")
    private String statusRemark;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private StoreStatsVO storeStats;

    @Override
    public void setStats(StoreStatsVO stats) {
        storeStats=stats;
    }


}
