package com.outmao.ebs.jnet.entity.material;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/*
 *
 * 模板原料
 *
 * */
@Entity
@Table(name = "z_ProductionTemplateMaterial",indexes= {@Index(columnList = "templateId,materialId",unique=true)})
public class ProductionTemplateMaterial  implements Serializable {

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
    @JoinColumn(name = "templateId")
    private ProductionTemplate template;

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
     * 损耗
     *
     * */
    private double loss;

    /*
     *
     * 用量单位
     *
     * */
    private String unit;


    /*
     *
     * 价格
     *
     * */
    private double amount;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ProductionTemplate template) {
        this.template = template;
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

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
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
