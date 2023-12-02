package com.outmao.ebs.jnet.dto.material;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialParamsDTO", description = "保存物料信息")
public class MaterialParamsDTO {

    @ApiModelProperty(name = "id", value = "ID")
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
    @ApiModelProperty(name = "supplierId", value = "供货商ID")
    private Long supplierId;

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
//    @ApiModelProperty(name = "images", value = "图片列表D")
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
     * 规格选项列表 字符串数组
     *
     * */
    @ApiModelProperty(name = "colors", value = "规格选项列表")
    private MaterialColorParamsDTO[] colors;


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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public MaterialColorParamsDTO[] getColors() {
        return colors;
    }

    public void setColors(MaterialColorParamsDTO[] colors) {
        this.colors = colors;
    }

}
