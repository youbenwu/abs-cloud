package com.outmao.ebs.jnet.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.jnet.entity.material.MaterialEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "z_SubWarehouseMaterial",indexes= {@Index(columnList = "subWarehouseId,materialId",unique=true)})
public class SubWarehouseMaterial implements Serializable {

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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subWarehouseId")
    private SubWarehouse subWarehouse;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "materialId")
    private MaterialEntity material;


    /*
     *
     * 存量
     *
     * */
    private double quantity;


    /*
     *
     * 存量单位
     *
     * */
    private String unit;


    /*
     *
     * 存量总价
     *
     * */
    private double amount;

    private Date createTime;

    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubWarehouse getSubWarehouse() {
        return subWarehouse;
    }

    public void setSubWarehouse(SubWarehouse subWarehouse) {
        this.subWarehouse = subWarehouse;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
