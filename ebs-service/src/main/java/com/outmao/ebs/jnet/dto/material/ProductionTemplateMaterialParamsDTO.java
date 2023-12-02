package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductionTemplateMaterialParamsDTO", description = "保存样板原料信息")
public class ProductionTemplateMaterialParamsDTO {

    @ApiModelProperty(name = "id", value = "不传为新增")
    private Long id;

    @ApiModelProperty(name = "templateId", value = "样板ID")
    private Long templateId;

    @ApiModelProperty(name = "materialId", value = "物料实体ID")
    private Long materialId;
    /*
     *
     * 用量
     *
     * */
    @ApiModelProperty(name = "quantity", value = "用量")
    private double quantity;


    /*
     *
     * 损耗
     *
     * */
    @ApiModelProperty(name = "loss", value = "损耗")
    private double loss;

    /*
     *
     * 用量单位
     *
     * */
    @ApiModelProperty(name = "unit", value = "用量单位")
    private String unit;

    /*
     *
     * 价格
     *
     * */
    @ApiModelProperty(name = "amount", value = "价格")
    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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
