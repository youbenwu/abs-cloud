package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.bbs.common.data.SubjectItemVO;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.mall.merchant.common.data.MerchantBrokerStatsSetter;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantSetter;
import com.outmao.ebs.mall.store.common.data.SimpleStoreSetter;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSetter;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel(value = "MerchantBrokerVO", description = "商家经纪人信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MerchantBrokerVO extends SubjectItemVO implements MerchantBrokerStatsSetter, SimpleUserSetter, SimpleMerchantSetter, SimpleStoreSetter , UserCommissionSetter , IItem {


    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    /**
     *
     * 商家ID
     *
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "merchant", value = "商家信息")
    private SimpleMerchantVO merchant;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;


    @ApiModelProperty(name = "user", value = "用户信息")
    private SimpleUserVO user;

    /**
     *
     * 门店ID
     *
     */
    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;


    @ApiModelProperty(name = "store", value = "门店信息")
    private SimpleStoreVO store;

    /**
     *
     * 佣金ID
     *
     */
    private Long commissionId;
    @ApiModelProperty(name = "commission", value = "佣金信息")
    private UserCommissionVO commission;

    /**
     *
     * 头像
     *
     */
    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

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
     * 电子邮箱
     *
     */
    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;

    /**
     *
     * 主营房源
     *
     */
    private String business;

    /**
     *
     * 服务佣金
     *
     */
    private String fees;

    /**
     *
     * 员工H5地址
     *
     */
    @ApiModelProperty(name = "url", value = "员工H5地址")
    private String url;

    /**
     *
     * 员工维码地址，扫码访问员工首页
     *
     */
    @ApiModelProperty(name = "qrCode", value = "员工维码地址，扫码访问员工首页")
    private String qrCode;

    /**
     *
     * 绑定客户二维码
     *
     */
    @ApiModelProperty(name = "brokerQrCode", value = "绑定客户二维码")
    private String brokerQrCode;

    /**
     *
     * 绑定兼职经纪人二维码
     *
     */
    @ApiModelProperty(name = "pyramidQrCode", value = "绑定兼职经纪人二维码")
    private String pyramidQrCode;

    /**
     *
     * 经纪人口令
     *
     */
    @ApiModelProperty(name = "code", value = "经纪人口令")
    private String code;


    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核")
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


    /**
     *
     * 统计数据
     *
     */
    @ApiModelProperty(name = "brokerStats", value = "统计数据")
    private MerchantBrokerStatsVO brokerStats;

    @Override
    public void setStats(MerchantBrokerStatsVO stats) {
        brokerStats=stats;
    }


}
