package com.outmao.ebs.jnet.vo.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.warehouse.QWarehouseMaterial;
import com.outmao.ebs.jnet.vo.material.MaterialEntityVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "WarehouseMaterialVO", description = "仓库物料")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class WarehouseMaterialVO  implements DslVO<QWarehouseMaterial> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "warehouseId", value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(name = "materialId", value = "物料实体ID")
    private Long materialId;

    @ApiModelProperty(name = "material", value = "物料实体")
    private MaterialEntityVO material;

    /*
     *
     * 库存总成本
     *
     * */
    @ApiModelProperty(name = "totalAmount", value = "库存总成本")
    private double totalAmount;

    /*
     *
     * 总采购库存量之和、无视单位
     *
     * */
    @ApiModelProperty(name = "totalQuantity", value = "总采购库存量之和、无视单位")
    private double totalQuantity;

    /*
     *
     * 用量
     *
     * */
    @ApiModelProperty(name = "quantity", value = "用量")
    private double quantity;

    /*
     * 告警库存
     */
    @ApiModelProperty(name = "warningQuantity", value = "告警库存")
    private double warningQuantity;

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

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "更新的时间")
    private Date updateTime;

    public static Expression<?>[] select(QWarehouseMaterial e){
//        return ArrayUtil.merge(
//                MaterialEntityVO.select(e.material),
//                new Expression<?>[]{
//                        e.id,e.warehouse.id,e.quantity,e.unit,e.amount,e.warningQuantity,e.totalAmount,e.totalQuantity,e.createTime
//                }
//        );
        return new Expression<?>[]{
                e.id,e.warehouse.id,e.material.id,e.quantity,e.unit,e.amount,e.warningQuantity,e.totalAmount,e.totalQuantity
                ,e.createTime,e.updateTime
        };

    }


    @Override
    public WarehouseMaterialVO fromTuple(Tuple t, QWarehouseMaterial e) {
        //material=new MaterialEntityVO().fromTuple(t,e.material);
        id=t.get(e.id);
        warehouseId=t.get(e.warehouse.id);
        materialId=t.get(e.material.id);
        quantity=t.get(e.quantity);
        unit=t.get(e.unit);
        amount=t.get(e.amount);
        warningQuantity=t.get(e.warningQuantity);
        totalAmount=t.get(e.totalAmount);
        totalQuantity=t.get(e.totalQuantity);
        createTime=t.get(e.createTime);
        updateTime=t.get(e.updateTime);
        return this;
    }


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

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public MaterialEntityVO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntityVO material) {
        this.material = material;
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

    public double getWarningQuantity() {
        return warningQuantity;
    }

    public void setWarningQuantity(double warningQuantity) {
        this.warningQuantity = warningQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
