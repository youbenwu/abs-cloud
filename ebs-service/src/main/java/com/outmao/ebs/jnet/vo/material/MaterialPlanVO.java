package com.outmao.ebs.jnet.vo.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.material.QMaterialPlan;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "MaterialPlanVO", description = "物料计划信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MaterialPlanVO implements DslVO<QMaterialPlan> {

    /*
     *
     * ID
     *
     * */

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;


    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;


    /*
     *
     * 名称
     *
     * */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /*
     *
     * 总价
     *
     * */
    @ApiModelProperty(name = "amount", value = "总价")
    private double amount;


    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "时间")
    private Date createTime;

    @ApiModelProperty(name = "materials", value = "物料列表")
    private List<MaterialPlanMaterialVO> materials;

    public static Expression<?>[] select(QMaterialPlan e) {
        return new Expression<?>[]{
                e.id,e.user.id,e.name,e.amount, e.createTime,
        };
    }

        @Override
    public MaterialPlanVO fromTuple(Tuple t, QMaterialPlan e) {
        id=t.get(e.id);
        userId=t.get(e.user.id);
        name=t.get(e.name);
        amount=t.get(e.amount);
        createTime=t.get(e.createTime);
        return this;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<MaterialPlanMaterialVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialPlanMaterialVO> materials) {
        this.materials = materials;
    }
}
