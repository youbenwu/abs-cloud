package com.outmao.ebs.jnet.vo.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.material.QProductionTemplateMaterial;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductionTemplateMaterialVO", description = "样板原料")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ProductionTemplateMaterialVO  implements DslVO<QProductionTemplateMaterial> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "templateId", value = "样板ID")
    private Long templateId;



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
     * 损耗
     *
     * */
    @ApiModelProperty(name = "loss", value = "损耗")
    private double loss;

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


    public static Expression<?>[] select(QProductionTemplateMaterial e){
        return ArrayUtil.merge(
                MaterialEntityVO.select(e.material),
                new Expression<?>[]{
                        e.id,e.template.id,e.loss,e.quantity,e.unit,e.amount,
                }
        );

    }


    @Override
    public ProductionTemplateMaterialVO fromTuple(Tuple t, QProductionTemplateMaterial e) {
        material=new MaterialEntityVO().fromTuple(t,e.material);
        id=t.get(e.id);
        templateId=t.get(e.template.id);
        loss=t.get(e.loss);
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

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
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
