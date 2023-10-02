package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.mall.merchant.common.data.MerchantCustomerStatsSetter;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantBrokerSetter;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "MerchantCustomerVO", description = "商家客户信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MerchantCustomerVO implements MerchantCustomerStatsSetter,SimpleUserSetter , SimpleMerchantBrokerSetter {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 商家ID
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    /**
     * 客户用户ID
     */
    @ApiModelProperty(name = "userId", value = "客户用户ID")
    private Long userId;

    private SimpleUserVO user;


    /**
     *
     * 经纪人ID
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    private SimpleMerchantBrokerVO broker;

    /**
     *
     * 发展这个客户的合伙人ID
     *
     */
    @ApiModelProperty(name = "partnerId", value = "发展这个客户的合伙人ID")
    private Long partnerId;

    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    /**
     *
     * 备注
     *
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(name = "stats", value = "统计数据")
    private MerchantCustomerStatsVO stats;


    @JsonIgnore
    @Override
    public Long getBrokerId() {
        return brokerId;
    }

    @Override
    public void setBroker(SimpleMerchantBrokerVO member) {
        broker=member;
    }
}
