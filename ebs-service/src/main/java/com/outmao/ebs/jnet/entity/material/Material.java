package com.outmao.ebs.jnet.entity.material;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.factory.Factory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 *
 * 物料
 *
 * */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name = "z_Material")
public class Material  implements Serializable {

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
     * 用户
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /*
     *
     * 物料挂到工厂下
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "factoryId")
    private Factory factory;

    /*
     *
     * 供货商
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierId")
    private MaterialSupplier supplier;

    /*
     *
     * 是否已经删除
     *
     * */
    private boolean deleted;

    /*
     *
     * 类型 0--物料 1--辅料
     *
     * */
    private int type;


    /*
     *
     * 名称
     *
     * */
    private String name;

    /*
     *
     * 图片列表
     *
     * */
    //private String images;


    /*
     *
     * 单价
     *
     * */
    private double price;

    /*
     *
     * 单位类型 0:重量单位 1:长度单位 2:面积单位 3:体积单位 4:计数单位
     *
     * */
    private int unitType;

    /*
     *
     * 单位
     *
     * */
    private String unit;


    /*
     *
     * 规格选项列表 字符串数组JSON串
     *
     * */
    //private String colors;


    /*
     *
     * 物料实体列表 JSON串
     *
     * */
    @Column(length = 2000)
    private String entitys;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getImages() {
//        return images;
//    }
//
//    public void setImages(String images) {
//        this.images = images;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public MaterialSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MaterialSupplier supplier) {
        this.supplier = supplier;
    }

//    public String getColors() {
//        return colors;
//    }
//
//    public void setColors(String colors) {
//        this.colors = colors;
//    }

    public String getEntitys() {
        return entitys;
    }

    public void setEntitys(String entitys) {
        this.entitys = entitys;
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
