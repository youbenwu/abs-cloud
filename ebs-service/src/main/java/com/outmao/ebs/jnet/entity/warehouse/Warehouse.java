package com.outmao.ebs.jnet.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/*
 *
 * 物料仓库
 *
 * */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name = "z_Warehouse")
public class Warehouse  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     *
     * ID
     *
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*
     *
     * 创建用户
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /*
     *
     * 管理员
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId")
    private User manager;

    //0--正常 1--关闭
    private int status;

    /*
     *
     * 仓库名称
     *
     * */
    private String name;

    /*
     *
     * 仓号
     *
     * */
    private String warehouseNo;


    /*
     *
     * 仓库主题图片
     *
     * */
    private String themeImage;


    /*
     *
     * 仓库主题背景色
     *
     * */
    private String themeColor;


    /*
     *
     * 成员数
     *
     * */
    private int members;

    /*
     *
     * 成员ID
     *
     * */
    @Column(length = 500)
    private String memberIds;

    /*
     *
     * 成员头像
     *
     * */
    @Column(length = 2000)
    private String memberAvatars;


    /*
     *
     * 库存总成本
     *
     * */
    private double totalAmount;


    /*
     *
     * 库存价值
     *
     * */
    private double amount;

    /*
     *
     * 总采购库存量之和、无视单位
     *
     * */
    private double totalQuantity;

    /*
     *
     * 当前库存量之和、无视单位
     *
     * */
    private double quantity;


    /*
     *
     * 库存剩余百分比
     *
     * */
    private double quantityPer;


    /*
     *
     * 时间
     *
     * */
    private Date createTime;

    /*
     *
     * 时间
     *
     * */
    private Date updateTime;


    private Date quantityUpdateTime;

    /*
     *
     * 当前使用的生产模板
     *
     * */
    private Long productionTemplate;


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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getMemberAvatars() {
        return memberAvatars;
    }

    public void setMemberAvatars(String memberAvatars) {
        this.memberAvatars = memberAvatars;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantityPer() {
        return quantityPer;
    }

    public void setQuantityPer(double quantityPer) {
        this.quantityPer = quantityPer;
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

    public Date getQuantityUpdateTime() {
        return quantityUpdateTime;
    }

    public void setQuantityUpdateTime(Date quantityUpdateTime) {
        this.quantityUpdateTime = quantityUpdateTime;
    }


    public Long getProductionTemplate() {
        return productionTemplate;
    }

    public void setProductionTemplate(Long productionTemplate) {
        this.productionTemplate = productionTemplate;
    }


}
