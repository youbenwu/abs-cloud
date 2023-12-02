package com.outmao.ebs.jnet.dto.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MaterialBillParamsDTO", description = "新增物料单信息")
public class MaterialBillParamsDTO {


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
     * 物料单接收用户
     *
     * */
    @ApiModelProperty(name = "toUserId", value = "物料单接收用户")
    private Long toUserId;


    @ApiModelProperty(name = "name", value = "物料单名称")
    private String name;

    /*
     *
     * 物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单
     *
     * */
    @ApiModelProperty(name = "type", value = "物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单")
    private int type;


    /*
     *
     * 物料列表
     *
     * */
    @ApiModelProperty(name = "materials", value = "物料列表")
    private List<MaterialBillMaterialParamsDTO> materials;


    @ApiModelProperty(name = "totalAmountInc", value = "主仓采购价值变化量")
    private double totalAmountInc;


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

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MaterialBillMaterialParamsDTO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialBillMaterialParamsDTO> materials) {
        this.materials = materials;
    }

    public double getTotalAmountInc() {
        return totalAmountInc;
    }

    public void setTotalAmountInc(double totalAmountInc) {
        this.totalAmountInc = totalAmountInc;
    }
}
