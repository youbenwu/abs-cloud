package com.outmao.ebs.jnet.vo.warehouse;

import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.warehouse.QMaterialBillMaterial;
import com.outmao.ebs.jnet.vo.material.MaterialEntityVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModelProperty;

public class MaterialBillMaterialVO   implements DslVO<QMaterialBillMaterial> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "billId", value = "物料单ID")
    private Long billId;


    @ApiModelProperty(name = "material", value = "物料实体")
    private MaterialEntityVO material;

    /*
     *
     * 用量
     *
     * */
    @ApiModelProperty(name = "quantity", value = "用量")
    private double quantity;


    @ApiModelProperty(name = "quantityInc", value = "数量变化量，如-5,没修改则0")
    private double quantityInc;

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



    public static Expression<?>[] select(QMaterialBillMaterial e){
        return ArrayUtil.merge(
                MaterialEntityVO.select(e.material),
                new Expression<?>[]{
                        e.id,e.bill.id,e.quantity,e.quantityInc,e.unit,e.amount,
                }
        );

    }


    @Override
    public MaterialBillMaterialVO fromTuple(Tuple t, QMaterialBillMaterial e) {
        material=new MaterialEntityVO().fromTuple(t,e.material);
        id=t.get(e.id);
        billId=t.get(e.bill.id);
        quantity=t.get(e.quantity);
        quantityInc=t.get(e.quantityInc);
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

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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


}
