package com.outmao.ebs.jnet.vo.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.jnet.entity.material.Material;
import com.outmao.ebs.jnet.entity.material.MaterialSupplier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@ApiModel(value = "MaterialVO", description = "物料信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MaterialVO {


    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /*
     *
     * 用户
     *
     * */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    /*
     *
     * 物料挂到工厂下
     *
     * */
    @ApiModelProperty(name = "factoryId", value = "工厂ID")
    private Long factoryId;

    /*
     *
     * 供货商
     *
     * */
    @ApiModelProperty(name = "supplier", value = "供货商")
    private MaterialSupplier supplier;

    /*
     *
     * 是否已经删除
     *
     * */
    @ApiModelProperty(name = "deleted", value = "是否已经删除")
    private boolean deleted;

    /*
     *
     * 类型 0--物料 1--辅料
     *
     * */
    @ApiModelProperty(name = "type", value = "类型 0--物料 1--辅料")
    private int type;


    /*
     *
     * 名称
     *
     * */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /*
     *
     * 图片列表
     *
     * */
//    @ApiModelProperty(name = "images", value = "图片列表")
//    private String images;


    /*
     *
     * 单价
     *
     * */
//    @ApiModelProperty(name = "price", value = "单价")
//    private double price;

    /*
     *
     * 单位类型 0:重量单位 1:长度单位 2:面积单位 3:体积单位 4:计数单位
     *
     *
     * */
    @ApiModelProperty(name = "unitType", value = "单位类型 0:重量单位 1:长度单位 2:面积单位 3:体积单位 4:计数单位")
    private int unitType;

    /*
     *
     * 单位
     *
     * */
    @ApiModelProperty(name = "unit", value = "单位")
    private String unit;


    /*
     *
     * 规格选项列表 字符串数组JSON串
     *
     * */
//    @ApiModelProperty(name = "colors", value = "规格选项列表 字符串数组JSON串")
//    private String colors;


    /*
     *
     * 物料实体列表 JSON串
     *
     * */
    @ApiModelProperty(name = "entitys", value = "物料实体列表 JSON串")
    private String entitys;


    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "时间")
    private Date createTime;

    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "updateTime", value = "时间")
    private Date updateTime;


    public MaterialVO(){}

    public MaterialVO(Material material){
        BeanUtils.copyProperties(material,this);
        userId=material.getUser().getId();
        factoryId=material.getFactory().getId();
    }

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

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public MaterialSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MaterialSupplier supplier) {
        this.supplier = supplier;
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

//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
