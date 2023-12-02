package com.outmao.ebs.jnet.entity.warehouse;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.jnet.entity.material.MaterialEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "z_MaterialBillMaterial",indexes= {@Index(columnList = "billId,materialId",unique=true)})
public class MaterialBillMaterial implements Serializable {

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
    @JoinColumn(name = "billId")
    private MaterialBill bill;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "materialId")
    private MaterialEntity material;


    /*
     *
     * 数量
     *
     * */
    private double quantity;

    /*
     *
     * 数量变化量，如-5,没修改则传0
     *
     * */
    private double quantityInc;


    /*
     *
     * 数量单位
     *
     * */
    private String unit;


    /*
     *
     * 总价
     *
     * */
    private double amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialBill getBill() {
        return bill;
    }

    public void setBill(MaterialBill bill) {
        this.bill = bill;
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

    public double getQuantityInc() {
        return quantityInc;
    }

    public void setQuantityInc(double quantityInc) {
        this.quantityInc = quantityInc;
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


}
