package com.outmao.ebs.jnet.entity.material;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/*
 *
 * 物料计划里的原料
 *
 * */
@Entity
@Table(name = "z_MaterialPlanMaterial",indexes= {@Index(columnList = "planId,materialId",unique=true)})
public class MaterialPlanMaterial  implements Serializable {

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
    @JoinColumn(name = "planId")
    private MaterialPlan plan;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "materialId")
    private MaterialEntity material;


    /*
     *
     * 用量
     *
     * */
    private double quantity;


    /*
     *
     * 用量单位
     *
     * */
    private String unit;


    /*
     *
     * 计算出来的价钱
     *
     * */
    private double amount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialPlan getPlan() {
        return plan;
    }

    public void setPlan(MaterialPlan plan) {
        this.plan = plan;
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


}
