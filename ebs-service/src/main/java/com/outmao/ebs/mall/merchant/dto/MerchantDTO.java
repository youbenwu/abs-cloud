package com.outmao.ebs.mall.merchant.dto;


import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.data.dto.EnterpriseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MerchantDTO", description = "保存商家信息参数")
@Data
public class MerchantDTO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户")
    private Long userId;

    /**
     *
     * 商家类型 0--企业 1--个人
     *
     * */
    @ApiModelProperty(name = "type", value = "商家类型 0--企业 1--个人")
    private int type;

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
     * 企业信息
     *
     */
    @ApiModelProperty(name = "enterprise", value = "企业信息")
    private EnterpriseDTO enterprise;

}
