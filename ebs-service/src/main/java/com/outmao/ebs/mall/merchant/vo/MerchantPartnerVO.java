package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.mall.merchant.common.data.MerchantPartnerStatsSetter;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantBrokerSetter;
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

@ApiModel(value = "MerchantPartnerVO", description = "商家合伙人信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MerchantPartnerVO implements MerchantPartnerStatsSetter, SimpleUserSetter, SimpleStoreSetter, SimpleMerchantBrokerSetter, SimpleMerchantSetter, UserCommissionSetter {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 商家
     */
    @ApiModelProperty(name = "merchantId", value = "商家")
    private Long merchantId;

    @ApiModelProperty(name = "merchant", value = "商家信息")
    private SimpleMerchantVO merchant;

    /**
     * 用户
     */
    @ApiModelProperty(name = "userId", value = "用户")
    private Long userId;

    private SimpleUserVO user;

    @ApiModelProperty(name = "storeId", value = "店铺")
    private Long storeId;

    @ApiModelProperty(name = "store", value = "店铺信息")
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
     * 经纪人
     *
     */
    @ApiModelProperty(name = "brokerId", value = "经纪人")
    private Long brokerId;

    private SimpleMerchantBrokerVO broker;

    /**
     *
     * 分类的父分类
     *
     */
    @ApiModelProperty(name = "parentId", value = "分类的父分类")
    private Long parentId;

    /**
     * 分类的子分类
     *
     */
    //private List<MerchantPartnerVO> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    @ApiModelProperty(name = "level", value = "多级分类中所处的级别，级别从0开始")
    private int level;

    /**
     * 多级分类中是否是叶子节点的标识
     *
     */
    @ApiModelProperty(name = "leaf", value = "多级分类中是否是叶子节点的标识")
    private boolean leaf;

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
     * 身份证号码
     *
     */
    @ApiModelProperty(name = "idCardNo", value = "身份证号码")
    private String idCardNo;

    /**
     *
     * 身份证正面
     *
     */
    @ApiModelProperty(name = "idCardFront", value = "身份证正面")
    private String idCardFront;

    /**
     *
     * 身份证反面
     *
     */
    @ApiModelProperty(name = "idCardBack", value = "身份证反面")
    private String idCardBack;


    /**
     *
     * 合伙人H5地址
     *
     */
    @ApiModelProperty(name = "url", value = "合伙人H5地址")
    private String url;

    /**
     *
     * 二维码地址，扫码访问合伙人H5首页
     *
     */
    @ApiModelProperty(name = "qrCode", value = "二维码地址，扫码访问合伙人H5首页")
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
     * 绑定下级兼职经纪人二维码
     *
     */
    @ApiModelProperty(name = "pyramidQrCode", value = "绑定下级兼职经纪人二维码")
    private String pyramidQrCode;

    /**
     *
     * 兼职经纪人口令
     *
     */
    @ApiModelProperty(name = "code", value = "兼职经纪人口令")
    private String code;


    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
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
    @ApiModelProperty(name = "stats", value = "统计数据")
    private MerchantPartnerStatsVO stats;





}
