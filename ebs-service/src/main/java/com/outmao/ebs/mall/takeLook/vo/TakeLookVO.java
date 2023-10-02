package com.outmao.ebs.mall.takeLook.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantCustomerSetter;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantSetter;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import com.outmao.ebs.mall.store.common.data.SimpleStoreSetter;
import com.outmao.ebs.mall.takeLook.entity.TakeLookProduct;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "TakeLookVO", description = "带看信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class TakeLookVO implements SimpleMerchantSetter, SimpleStoreSetter , SimpleMerchantCustomerSetter , SimpleUserSetter {


    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     * 商家Id
     */
    @ApiModelProperty(name = "merchantId", value = "商家Id")
    private Long merchantId;


    @ApiModelProperty(name = "merchant", value = "商家信息")
    private SimpleMerchantVO merchant;

    /**
     * 门店ID
     */
    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "store", value = "门店")
    private SimpleStoreVO store;

    /**
     *
     * 经纪人ID
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;


    @ApiModelProperty(name = "broker", value = "经纪人信息")
    private SimpleMerchantBrokerVO broker;


    /**
     *
     * 带看经纪人ID
     *
     */
    @ApiModelProperty(name = "waiterId", value = "带看经纪人ID")
    private Long waiterId;


    @ApiModelProperty(name = "waiter", value = "带看经纪人信息")
    private SimpleMerchantBrokerVO waiter;


    /**
     *
     * 客户ID
     *
     */
    @ApiModelProperty(name = "customerId", value = "客户ID")
    private Long customerId;


    @ApiModelProperty(name = "customer", value = "客户")
    private SimpleMerchantCustomerVO customer;


    /**
     *
     * 客户用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "客户用户ID")
    private Long userId;

    @ApiModelProperty(name = "userId", value = "客户用户")
    private SimpleUserVO user;


    /**
     *
     * 客户意见
     *
     */
    @ApiModelProperty(name = "feedback", value = "客户意见")
    private String feedback;


    /**
     *
     * 状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭")
    private int status;


    @ApiModelProperty(name = "productType", value = "商品类型 0普通商品 11新楼盘 12二手房 13出租房")
    private int productType;


    private List<TakeLookProduct> products;

    /**
     *
     * 预约时间
     *
     */
    @ApiModelProperty(name = "appointmentTime", value = "预约时间")
    private Date appointmentTime;

    /**
     *
     * 带看时间
     *
     */
    @ApiModelProperty(name = "lookTime", value = "带看时间")
    private Date lookTime;

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


}
