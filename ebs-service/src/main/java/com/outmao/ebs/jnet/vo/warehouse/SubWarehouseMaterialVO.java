package com.outmao.ebs.jnet.vo.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.warehouse.QSubWarehouseMaterial;
import com.outmao.ebs.jnet.vo.material.MaterialEntityVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SubWarehouseMaterialVO", description = "子仓库物料")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class SubWarehouseMaterialVO   implements DslVO<QSubWarehouseMaterial> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "subWarehouseId", value = "子仓库ID")
    private Long subWarehouseId;


    @ApiModelProperty(name = "material", value = "物料实体")
    private MaterialEntityVO material;

    /*
     *
     * 用量
     *
     * */
    @ApiModelProperty(name = "quantity", value = "用量")
    private double quantity;

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


    public static Expression<?>[] select(QSubWarehouseMaterial e){
        return ArrayUtil.merge(
                MaterialEntityVO.select(e.material),
                new Expression<?>[]{
                        e.id,e.subWarehouse.id,e.quantity,e.unit,e.amount,
                }
        );

    }


    @Override
    public SubWarehouseMaterialVO fromTuple(Tuple t, QSubWarehouseMaterial e) {
        material=new MaterialEntityVO().fromTuple(t,e.material);
        id=t.get(e.id);
        subWarehouseId=t.get(e.subWarehouse.id);

        quantity=t.get(e.quantity);
        unit=t.get(e.unit);
        amount=t.get(e.amount);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubWarehouseId() {
        return subWarehouseId;
    }

    public void setSubWarehouseId(Long subWarehouseId) {
        this.subWarehouseId = subWarehouseId;
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


}
