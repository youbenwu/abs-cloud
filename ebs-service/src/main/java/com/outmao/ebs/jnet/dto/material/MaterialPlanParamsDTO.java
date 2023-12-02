package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel(value = "MaterialPlanParamsDTO", description = "保存物料计划信息")
public class MaterialPlanParamsDTO {

    @ApiModelProperty(name = "id", value = "物料计划ID，不传为新增")
    private Long id;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "name", value = "计划名称")
    private String name;

    @ApiModelProperty(name = "amount", value = "总价")
    private double amount;

    @ApiModelProperty(name = "materials", value = "物料列表")
    private List<MaterialPlanMaterialParamsDTO> materials;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<MaterialPlanMaterialParamsDTO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialPlanMaterialParamsDTO> materials) {
        this.materials = materials;
    }


}
