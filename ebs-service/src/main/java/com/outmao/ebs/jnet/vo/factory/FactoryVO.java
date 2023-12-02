package com.outmao.ebs.jnet.vo.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.jnet.entity.factory.FactoryProductionCategory;
import com.outmao.ebs.jnet.entity.factory.FactoryProductionTechnology;
import com.outmao.ebs.jnet.entity.factory.QFactory;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "FactoryVO", description = "工厂信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class FactoryVO  implements DslVO<QFactory> , Serializable {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     * 所属用户
     */
    @ApiModelProperty(name = "userId", value = "所属用户")
    private Long userId;

    /*
     *
     * 绑定评论系统主题
     *
     */
    @ApiModelProperty(name = "subjectId", value = "绑定评论系统主题")
    private Long subjectId;

    /**
     * 工厂状态 0--待审 1--正常 2--审核失败 3--关闭
     */
    @ApiModelProperty(name = "status", value = "工厂状态 0--待审 1--正常 2--审核失败 3--关闭")
    private int status;

    /**
     * 工厂状态备注
     */
    @ApiModelProperty(name = "statusRemark", value = "工厂状态备注")
    private String statusRemark;


    /**
     *
     * 工厂类型 0--个体 1--公司
     *
     */
    @ApiModelProperty(name = "type", value = "工厂类型 0--个体 1--公司")
    private int type;

    /**
     * 工厂名称
     */
    @ApiModelProperty(name = "name", value = "工厂名称")
    private String name;


    /**
     * 工厂图片集
     */
    @ApiModelProperty(name = "images", value = "工厂图片集")
    private String images;


    /**
     * 简介
     */
    @ApiModelProperty(name = "intro", value = "简介")
    private String intro;


    /**
     * 联系方式
     */
    @ApiModelProperty(name = "contact", value = "联系方式")
    private Contact contact;

    /**
     * 起接单量
     */
    @ApiModelProperty(name = "minOrderQuantity", value = "起接单量")
    private int minOrderQuantity;

    /**
     * 报价速度
     */
    @ApiModelProperty(name = "quoteSpeed", value = "报价速度")
    private Integer quoteSpeed;

    /**
     * 是否支持开发票
     */
    @ApiModelProperty(name = "invoiceable", value = "是否支持开发票")
    private Boolean invoiceable;

    /**
     * 员工数
     */
    @ApiModelProperty(name = "employees", value = "员工数")
    private int employees;

    /**
     * 员工数
     */
    @ApiModelProperty(name = "employees", value = "员工数")
    private String employeesText;

    /**
     * 注册资金
     */
    @ApiModelProperty(name = "regCapital", value = "注册资金")
    private String regCapital;

    /**
     * 成立时间
     */
    @ApiModelProperty(name = "regTime", value = "成立时间")
    private String regTime;

    /**
     * 档期
     */
    @ApiModelProperty(name = "timeline", value = "档期")
    private Date timeline;

    /**
     * 隐私模式
     */
    @ApiModelProperty(name = "privacy", value = "隐私模式")
    private boolean privacy;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(name = "updateTime", value = "修改时间")
    private Date updateTime;

    /**
     * 浏览次数
     */
    @ApiModelProperty(name = "browses", value = "浏览次数")
    private int browses;



    private List<FactoryProductionCategory> productionCategorys;
    private List<FactoryProductionTechnology> productionTechnologys;

    public static Expression<?>[] select(QFactory e){
        return new Expression<?>[]{
                e.id,e.subject.id, e.user.id, e.status,e.statusRemark, e.type,
                e.name, e.images, e.intro, e.contact,e.timeline,e.privacy,
                e.minOrderQuantity, e.quoteSpeed,e.invoiceable,e.employees,e.employeesText,
                e.regCapital,e.regTime,e.createTime,e.updateTime,e.timeline,e.subject.stats.browses,
        };
    }

    @Override
    public FactoryVO fromTuple(Tuple t, QFactory e) {
        id=t.get(e.id);
        subjectId=t.get(e.subject.id);
        userId=t.get(e.user.id);
        status=t.get(e.status);
        statusRemark=t.get(e.statusRemark);
        type=t.get(e.type);
        name=t.get(e.name);
        images=t.get(e.images);
        intro=t.get(e.intro);
        contact=t.get(e.contact);
        minOrderQuantity=t.get(e.minOrderQuantity);
        quoteSpeed=t.get(e.quoteSpeed);
        invoiceable=t.get(e.invoiceable);
        employees=t.get(e.employees);
        employeesText=t.get(e.employeesText);
        timeline=t.get(e.timeline);
        privacy=t.get(e.privacy);
        regCapital=t.get(e.regCapital);
        regTime=t.get(e.regTime);
        createTime=t.get(e.createTime);
        updateTime=t.get(e.updateTime);
        timeline=t.get(e.timeline);
        browses=t.get(e.subject.stats.browses);
        return this;
    }


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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    public List<FactoryProductionCategory> getProductionCategorys() {
        return productionCategorys;
    }

    public void setProductionCategorys(List<FactoryProductionCategory> productionCategorys) {
        this.productionCategorys = productionCategorys;
    }

    public List<FactoryProductionTechnology> getProductionTechnologys() {
        return productionTechnologys;
    }

    public void setProductionTechnologys(List<FactoryProductionTechnology> productionTechnologys) {
        this.productionTechnologys = productionTechnologys;
    }

    public int getBrowses() {
        return browses;
    }

    public void setBrowses(int browses) {
        this.browses = browses;
    }

}
