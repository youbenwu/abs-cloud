package com.outmao.ebs.org.entity.enterprise;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@MappedSuperclass
public class EnterpriseBasicInformation {

    /**
     *
     * 企业名称
     * 工商管理部门核发的营业执照上的企业名称
     *
     * */
    @ApiModelProperty(name = "enterpriseName", value = "企业名称-工商管理部门核发的营业执照上的企业名称")
    private String enterpriseName;

    /**
     *
     * 组织机构代码
     * 由全国组织机构代码管理中心颁发的中华人民共和国组织机构代码证上的代码
     *
     * */
    @ApiModelProperty(name = "organizationCode", value = "组织机构代码-由全国组织机构代码管理中心颁发的中华人民共和国组织机构代码证上的代码")
    private String organizationCode;


    /**
     *
     * 工商注册号
     * 由工商行政部门核发的营业执照的注册号编号
     *
     * */
    @ApiModelProperty(name = "industryAndCommerceRegistryNo", value = "工商注册号-由工商行政部门核发的营业执照的注册号编号")
    private String industryAndCommerceRegistryNo;


    /**
     *
     * 登记机关
     * 核发营业执照的工商管理部门
     *
     * */
    @ApiModelProperty(name = "registryAuthority", value = "登记机关-核发营业执照的工商管理部门")
    private String registryAuthority;

    /**
     *
     * 法定代表人
     * 营业执照上的法定代表人或负责人
     *
     * */
    @ApiModelProperty(name = "legalRepresentative", value = "法定代表人-营业执照上的法定代表人或负责人")
    private String legalRepresentative;

    /**
     *
     * 法人代表人证件类型
     *
     */
    @ApiModelProperty(name = "legalRepresentativeCertificateType", value = "法人代表人证件类型")
    private String legalRepresentativeCertificateType;

    /**
     *
     * 法人代表人证件号
     *
     */
    @ApiModelProperty(name = "legalRepresentativeCertificateNumber", value = "法人代表人证件号")
    private String legalRepresentativeCertificateNumber;

    /**
     *
     * 法定代表人证件电子版
     *
     */
    @ApiModelProperty(name = "legalRepresentativeCertificate", value = "法定代表人证件电子版")
    private String legalRepresentativeCertificate;


    /**
     *
     * 企业类型
     * 营业执照上的登记注册的各类企业划分的不同类型
     *
     * */
    @ApiModelProperty(name = "enterpriseType", value = "企业类型-营业执照上的登记注册的各类企业划分的不同类型")
    private String enterpriseType;


    /**
     *
     * 企业简介
     * 企业情况的简要说明
     *
     * */
    @ApiModelProperty(name = "enterpriseIntroduction", value = "企业简介-企业情况的简要说明")
    private String enterpriseIntroduction;

    /**
     *
     * 网址
     * 企业信息所在的互联网地址
     *
     * */
    @ApiModelProperty(name = "webSite", value = "网址-企业信息所在的互联网地址")
    private String webSite;


    /**
     *
     * 联系信息
     * 描述企业的联系方式的信息
     *
     * */
    @ApiModelProperty(name = "contactInformation", value = "联系信息-描述企业的联系方式的信息")
    @Embedded
    private EnterpriseContactInformation contactInformation;

    /**
     *
     * 账户信息
     * 描述企业的银行帐户的信息
     *
     * */
    @ApiModelProperty(name = "accountInformation", value = "账户信息-描述企业的银行帐户的信息")
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<EnterpriseAccountInformation> accountInformation;

    /**
     *
     * 税务信息
     * 描述企业的税务的有关信息
     *
     * */
    @ApiModelProperty(name = "taxInformation", value = "税务信息-描述企业的税务的有关信息")
    @Embedded
    private EnterpriseTaxInformation taxInformation;


    /**
     *
     * 信用信息
     * 描述企业的信用的信息
     *
     * */
    @ApiModelProperty(name = "creditInformation", value = "信用信息-描述企业的信用的信息")
    @Embedded
    private EnterpriseCreditInformation creditInformation;


    /**
     *
     * 品牌信息
     * 企业及其提供的产品或服务的有形和无形的综合表现，其目的是借以辨认组织产品或服务，并使之两只竞争对手的产品或服务区别开来
     *
     * */
    @ApiModelProperty(name = "brandInformation", value = "品牌信息-企业及其提供的产品或服务的有形和无形的综合表现，其目的是借以辨认组织产品或服务，并使之两只竞争对手的产品或服务区别开来")
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<EnterpriseBrandInformation> brandInformation;


    /**
     *
     * 注册资本
     *
     */
    @ApiModelProperty(name = "registeredCapital", value = "注册资本")
    private String registeredCapital;


    /**
     *
     * 企业成立时间
     *
     */
    @ApiModelProperty(name = "regTime", value = "企业成立时间")
    private Date regTime;


    /**
     *
     * 经营范围
     *
     */
    @ApiModelProperty(name = "businessScope", value = "经营范围")
    private String businessScope;

    /**
     *
     * 营业执照电子版
     *
     *
     */
    @ApiModelProperty(name = "businessLicense", value = "营业执照电子版")
    private String businessLicense;

    /**
     *
     * 行政许可证电子版
     *
     */
    @ApiModelProperty(name = "administrativeLicense", value = "行政许可证电子版")
    private String administrativeLicense;






}
