package com.outmao.ebs.jnet.dto.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialBillMaterialParamsDTO", description = "物料信息")
public class MaterialBillMaterialParamsDTO {

    @ApiModelProperty(name = "materialId", value = "物料实体ID")
    private Long materialId;

    @ApiModelProperty(name = "quantity", value = "新增不传")
    private double quantity;

    @ApiModelProperty(name = "quantityInc", value = "数量")
    private double quantityInc;


    @ApiModelProperty(name = "unit", value = "用量单位")
    private String unit;

    @ApiModelProperty(name = "amount", value = "计算出来的价钱")
    private double amount;

    @ApiModelProperty(name = "price", value = "如果有修改单价则传")
    private Double price;

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


    public double getQuantityInc() {
        return quantityInc;
    }

    public void setQuantityInc(double quantityInc) {
        this.quantityInc = quantityInc;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
