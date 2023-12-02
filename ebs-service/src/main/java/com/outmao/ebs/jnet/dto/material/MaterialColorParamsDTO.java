package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialColorParamsDTO", description = "物料规格")
public class MaterialColorParamsDTO {

    @ApiModelProperty(name = "id", value = "物料实体ID，新增不传")
    private Long id;

    /*
     *
     * 缸号
     *
     * */
    @ApiModelProperty(name = "vatNo", value = "缸号")
    private String   vatNo;

    @ApiModelProperty(name = "color", value = "规格名称,同一个物料下不能重复，否则报错，前端检查再提交")
    private String color;

    @ApiModelProperty(name = "image", value = "图片")
    private String image;

    @ApiModelProperty(name = "price", value = "单价")
    private double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
