package com.outmao.ebs.jnet.dto.order;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "OrderParamsDTO", description = "用户下单")
public class OrderParamsDTO {

    @ApiModelProperty(name = "id", value = "订单ID")
    private Long id;


    /**
     * 下单客户
     */
    @ApiModelProperty(name = "userId", value = "下单客户ID")
    private Long userId;

    /**
     * 生产用户ID
     */
    @ApiModelProperty(name = "producerId", value = "生产用户ID")
    private Long producerId;

    /**
     * 图片
     */
    @ApiModelProperty(name = "image", value = "图片")
    private String image;

    /**
     * 批号
     */
    @ApiModelProperty(name = "lotNum", value = "批号")
    private String lotNum;

    /**
     * 固定需求
     */
    @ApiModelProperty(name = "fixed", value = "固定需求")
    private boolean fixed;

    /**
     * 单价
     */
    @ApiModelProperty(name = "price", value = "单价")
    private double price;


    /**
     * 样板ID
     */
    @ApiModelProperty(name = "templateId", value = "样板ID")
    private Long templateId;

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

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
