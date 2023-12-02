package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialPlanMaterialParamsDTO", description = "物料计划里的物料信息")
public class MaterialPlanMaterialParamsDTO {


    @ApiModelProperty(name = "materialId", value = "物料实体ID")
    private Long materialId;

    @ApiModelProperty(name = "quantity", value = "用量")
    private double quantity;


    @ApiModelProperty(name = "unit", value = "用量单位")
    private String unit;

    @ApiModelProperty(name = "amount", value = "计算出来的价钱")
    private double amount;

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
