package com.outmao.ebs.jnet.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "z_SubWarehouse",indexes= {@Index(columnList = "warehouseId,userId",unique=true)})
public class SubWarehouse  implements Serializable {

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
     * 主仓库
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouse;


    /*
     *
     * 成员用户
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /*
     *
     * 成员类型 0--普通成员 1--管理员
     *
     * */
    private int memberType;

    /*
     *
     * 和主仓库之间的会话状态
     * 0--正常 1--主仓库给成员发了物料单 2--成员给主仓库发了物料单
     *
     * */
    //private int sessionStatus;


    /*
     *
     * 当前处理的物料单
     *
     * */
    //private Long billId;


    /*
     *
     * 库存价值
     *
     * */
    private double amount;

    /*
     *
     * 当前库存量之和、无视单位
     *
     * */
    private double quantity;


    /*
     *
     * 库存占主仓百分比
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

//    public int getSessionStatus() {
//        return sessionStatus;
//    }
//
//    public void setSessionStatus(int sessionStatus) {
//        this.sessionStatus = sessionStatus;
//    }
//
//    public Long getBillId() {
//        return billId;
//    }
//
//    public void setBillId(Long billId) {
//        this.billId = billId;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

}
