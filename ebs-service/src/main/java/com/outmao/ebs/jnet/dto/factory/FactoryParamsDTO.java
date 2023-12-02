package com.outmao.ebs.jnet.dto.factory;


import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "FactoryParamsDTO", description = "保存工厂信息")
public class FactoryParamsDTO {

    @ApiModelProperty(name = "id", value = "工厂ID")
    private Long id;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "inviterId", value = "推荐者ID")
    private Long inviterId;

    @ApiModelProperty(name = "type", value = "工厂类型 0--个体 1--公司")
    private int type;

    @ApiModelProperty(name = "name", value = "工厂名称")
    private String name;

    @ApiModelProperty(name = "images", value = "工厂图片")
    private String images;

    @ApiModelProperty(name = "intro", value = "工厂简介")
    private String intro;

    @ApiModelProperty(name = "contact", value = "联系人")
    private Contact contact;

    @ApiModelProperty(name = "minOrderQuantity", value = "起接单量")
    private int minOrderQuantity;

    @ApiModelProperty(name = "quoteSpeed", value = "报价速度")
    private Integer quoteSpeed;

    @ApiModelProperty(name = "invoiceable", value = "是否支持开发票")
    private Boolean invoiceable;

    @ApiModelProperty(name = "employees", value = "员工数")
    private int employees;

    @ApiModelProperty(name = "employeesText", value = "员工数")
    private String employeesText;

    @ApiModelProperty(name = "timeline", value = "档期格式yyyy-MM-dd HH:mm:ss")
    private Date timeline;

    @ApiModelProperty(name = "privacy", value = "隐私模式")
    private boolean privacy;

    @ApiModelProperty(name = "regCapital", value = "注册资金")
    private String regCapital;

    @ApiModelProperty(name = "regTime", value = "成立时间")
    private String regTime;

    @ApiModelProperty(name = "productionCategorys", value = "生产品类ID列表")
    private List<Long> productionCategorys;


    @ApiModelProperty(name = "productionTechnologys", value = "生产工艺ID列表")
    private List<FactoryProductionTechnologyParamsDTO> productionTechnologys;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(int minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public Integer getQuoteSpeed() {
        return quoteSpeed;
    }

    public void setQuoteSpeed(Integer quoteSpeed) {
        this.quoteSpeed = quoteSpeed;
    }

    public Boolean getInvoiceable() {
        return invoiceable;
    }

    public void setInvoiceable(Boolean invoiceable) {
        this.invoiceable = invoiceable;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getEmployeesText() {
        return employeesText;
    }

    public void setEmployeesText(String employeesText) {
        this.employeesText = employeesText;
    }

    public Date getTimeline() {
        return timeline;
    }

    public void setTimeline(Date timeline) {
        this.timeline = timeline;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }


    public List<Long> getProductionCategorys() {
        return productionCategorys;
    }

    public void setProductionCategorys(List<Long> productionCategorys) {
        this.productionCategorys = productionCategorys;
    }

    public List<FactoryProductionTechnologyParamsDTO> getProductionTechnologys() {
        return productionTechnologys;
    }

    public void setProductionTechnologys(List<FactoryProductionTechnologyParamsDTO> productionTechnologys) {
        this.productionTechnologys = productionTechnologys;
    }
}
