package com.outmao.ebs.jnet.vo.material;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.material.QMaterialPlanMaterial;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MaterialPlanMaterialVO", description = "物料计划原料")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MaterialPlanMaterialVO implements DslVO<QMaterialPlanMaterial> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "planId", value = "计划ID")
    private Long planId;


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

    public static Expression<?>[] select(QMaterialPlanMaterial e){
        return ArrayUtil.merge(
                MaterialEntityVO.select(e.material),
                new Expression<?>[]{
                        e.id,e.plan.id,e.quantity,e.unit,e.amount,
                }
        );

    }


    @Override
    public MaterialPlanMaterialVO fromTuple(Tuple t, QMaterialPlanMaterial e) {
        material=new MaterialEntityVO().fromTuple(t,e.material);
        id=t.get(e.id);
        planId=t.get(e.plan.id);
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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
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
