package com.outmao.ebs.jnet.dto.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MaterialBillUpdateDTO", description = "修改物料单信息")
public class MaterialBillUpdateDTO {

    @ApiModelProperty(name = "id", value = "物料单ID")
    private Long id;

    /*
     *
     * 主仓库
     *
     * */
    @ApiModelProperty(name = "warehouseId", value = "主仓库ID")
    private Long warehouseId;

    /*
     *
     * 物料单创建用户
     *
     * */
    @ApiModelProperty(name = "userId", value = "物料单创建用户")
    private Long userId;

    /*
     *
     * 物料列表
     *
     * */
    @ApiModelProperty(name = "materials", value = "物料列表,整个列表传过来，不管有没修改")
    private List<MaterialBillMaterialUpdateDTO> materials;


    @ApiModelProperty(name = "totalAmountInc", value = "主仓采购价值变化量")
    private double totalAmountInc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public List<MaterialBillMaterialUpdateDTO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialBillMaterialUpdateDTO> materials) {
        this.materials = materials;
    }

    public double getTotalAmountInc() {
        return totalAmountInc;
    }

    public void setTotalAmountInc(double totalAmountInc) {
        this.totalAmountInc = totalAmountInc;
    }
}
