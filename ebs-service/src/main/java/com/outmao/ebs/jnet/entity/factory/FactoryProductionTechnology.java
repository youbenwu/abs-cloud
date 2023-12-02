package com.outmao.ebs.jnet.entity.factory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * 工厂生产工艺
 * */
@Entity
@Table(name = "z_FactoryProductionTechnology" ,indexes= {@Index(columnList = "factoryId,technologyId",unique=true)})
public class FactoryProductionTechnology implements Serializable {

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
     *
     * 工厂
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "factoryId")
    private Factory factory;

    /**
     * 生产工艺
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "technologyId")
    private ProductionTechnology technology;

    /**
     * 数量
     */
    private int quantity;

    /**
     * 创建时间
     */
    private Date createTime;


    @Column(name = "productionTechnologyId")
    private Long   technologyId;

    private String technologyName;

    private String technologySuffix;

    private String parentTechnologyName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public ProductionTechnology getTechnology() {
        return technology;
    }

    public void setTechnology(ProductionTechnology technology) {
        this.technology = technology;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getParentTechnologyName() {
        return parentTechnologyName;
    }

    public void setParentTechnologyName(String parentTechnologyName) {
        this.parentTechnologyName = parentTechnologyName;
    }

    public String getTechnologySuffix() {
        return technologySuffix;
    }

    public void setTechnologySuffix(String technologySuffix) {
        this.technologySuffix = technologySuffix;
    }

}
