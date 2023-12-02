package com.outmao.ebs.jnet.entity.factory;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.common.vo.Itemable;
import com.outmao.ebs.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * 工厂类
 * */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity(name = "ZFactory")
@Table(name = "z_Factory")
public class Factory implements Serializable , Itemable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 所属用户
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",unique = true)
    private User user;

    /*
     *
     * 绑定评论系统主题
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     * 工厂状态 0--待审 1--正常 2--审核失败 3--关闭
     */
    private int status;

    /**
     * 工厂状态备注
     */
    private String statusRemark;


    /**
     *
     * 工厂类型 0--个体 1--公司
     *
     */
    private int type;

    /**
     * 工厂名称
     */
    private String name;


    /**
     * 工厂图片集
     */
    @Column(length = 2000)
    private String images;


    /**
     * 简介
     */
    @Column(length = 500)
    private String intro;


    /**
     * 联系方式
     */
    @Embedded
	@AttributeOverrides({
	 @AttributeOverride(name = "name", column = @Column(name = "contact_realname"))
	,@AttributeOverride(name = "phone", column = @Column(name = "contact_telphone"))
	,@AttributeOverride(name = "address.latitude", column = @Column(name = "contact_address_latitude"))
	,@AttributeOverride(name = "address.longitude", column = @Column(name = "contact_address_longitude"))
	,@AttributeOverride(name = "address.province", column = @Column(name = "contact_address_province"))
    ,@AttributeOverride(name = "address.city", column = @Column(name = "contact_address_city"))
    ,@AttributeOverride(name = "address.district", column = @Column(name = "contact_address_district"))
    ,@AttributeOverride(name = "address.details", column = @Column(name = "contact_address_details"))
    ,@AttributeOverride(name = "address.postalCode", column = @Column(name = "contact_address_zipcode"))
	})
    private Contact contact;

    /**
     * 起接单量
     */
    private int minOrderQuantity;

    /**
     * 报价速度
     */
    private Integer quoteSpeed;

    /**
     * 是否支持开发票
     */
    private Boolean invoiceable;

    /**
     * 员工数索引
     * 10以下
     * 10-20
     * 20-50
     * 50-100
     * 100-200
     * 200-500
     * 500以上
     *
     */
    private int    employees;
    /**
     * 员工数对应范围
     */
    private String employeesText;

    /**
     * 注册资金
     */
    private String regCapital;

    /**
     * 成立时间
     */
    private String regTime;

    /**
     * 档期
     */
    private Date timeline;


    /**
     * 隐私模式
     */
    private boolean privacy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 推荐者id
     */
    private Long inviterId;

    /**
     * 成功推荐人数
     */
    private int invites;


    /**
     * 当天可申请外发数量
     */
    private int dayAssignmentApplyNum;

    /**
     * 每天可申请外发数量上限
     */
    private int dayMaxAssignmentApplyNum;

    /**
     * 更新时间
     */
    private Date dayAssignmentApplyNumUpdateTime;



    @Override
    public Item toItem() {
        return new Item(id,"Factory",name,intro,null,null,images==null?null:images.split(",")[0],null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public int getInvites() {
        return invites;
    }

    public void setInvites(int invites) {
        this.invites = invites;
    }

    public int getDayAssignmentApplyNum() {
        return dayAssignmentApplyNum;
    }

    public void setDayAssignmentApplyNum(int dayAssignmentApplyNum) {
        this.dayAssignmentApplyNum = dayAssignmentApplyNum;
    }

    public int getDayMaxAssignmentApplyNum() {
        return dayMaxAssignmentApplyNum;
    }

    public void setDayMaxAssignmentApplyNum(int dayMaxAssignmentApplyNum) {
        this.dayMaxAssignmentApplyNum = dayMaxAssignmentApplyNum;
    }

    public Date getDayAssignmentApplyNumUpdateTime() {
        return dayAssignmentApplyNumUpdateTime;
    }

    public void setDayAssignmentApplyNumUpdateTime(Date dayAssignmentApplyNumUpdateTime) {
        this.dayAssignmentApplyNumUpdateTime = dayAssignmentApplyNumUpdateTime;
    }

    public int hashCode() {
        return (this.id == null) ? 0 : this.id.hashCode();
    }
    public boolean equals(Object object) {
        if (object instanceof Factory) {
            final Factory obj = (Factory) object;
            return (this.id != null) ? this.id.equals(obj.id) : (obj.id == null);
        }
        return false;
    }


}
