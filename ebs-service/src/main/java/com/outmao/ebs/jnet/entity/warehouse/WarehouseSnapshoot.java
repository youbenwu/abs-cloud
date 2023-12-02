package com.outmao.ebs.jnet.entity.warehouse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "z_WarehouseSnapshoot")
public class WarehouseSnapshoot  implements Serializable {

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

    //0 主仓库 1 子仓库
    private int type;

    private Long userId;

    //private String fullname;

    //主仓库ID
    private Long warehouseId;

    //子仓库ID
    private Long subWarehouseId;


    /*
     *
     * 库存总成本
     *
     * */
    private double totalAmount;

    /*
     *
     * 总采购库存量之和、无视单位
     *
     * */
    private double totalQuantity;


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


    //时间
    private Date createTime;



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

//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getSubWarehouseId() {
        return subWarehouseId;
    }

    public void setSubWarehouseId(Long subWarehouseId) {
        this.subWarehouseId = subWarehouseId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
