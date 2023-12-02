package com.outmao.ebs.jnet.dto.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialBillMaterialUpdateDTO", description = "物料信息")
public class MaterialBillMaterialUpdateDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "quantity", value = "原来的数量")
    private double quantity;

    @ApiModelProperty(name = "quantityInc", value = "数量变化量，如-5,没修改则传0")
    private double quantityInc;

    @ApiModelProperty(name = "amount", value = "修改后计算出来的价钱")
    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
