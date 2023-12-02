package com.outmao.ebs.jnet.vo.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.material.MaterialSupplier;
import com.outmao.ebs.jnet.entity.material.QMaterialEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "MaterialEntityVO", description = "物料实体")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MaterialEntityVO   implements DslVO<QMaterialEntity> {


    @ApiModelProperty(name = "id", value = "物料实体ID")
    private Long id;

    /*
     *
     * 类型 0--物料 1--辅料
     *
     * */
    @ApiModelProperty(name = "type", value = "类型 0--物料 1--辅料")
    private int type;

    /*
     *
     * 缸号
     *
     * */
    @ApiModelProperty(name = "vatNo", value = "缸号")
    private String   vatNo;

    /*
     *
     * 名称
     *
     * */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;


    /*
     *
     * 单位类型 0:重量单位 1:长度单位 2:面积单位 3:体积单位 4:计数单位
     *
     * */
    @ApiModelProperty(name = "unitType", value = "unitType")
    private int unitType;

    /*
     *
     * 单位
     *
     * */
    @ApiModelProperty(name = "unit", value = "单位")
    private String unit;


    /*
     *
     * 规格名称
     *
     * */
    @ApiModelProperty(name = "color", value = "规格名称")
    private String   color;

    /*
     *
     * 图片
     *
     * */
    @ApiModelProperty(name = "image", value = "图片")
    private String   image;

    /*
     *
     * 单价
     *
     * */
    @ApiModelProperty(name = "price", value = "单价")
    private double   price;

    /*
     *
     * 是否已经删除
     *
     * */
    @ApiModelProperty(name = "deleted", value = "是否已经删除")
    private boolean  deleted;

    @ApiModelProperty(name = "supplierId", value = "供货商Id")
    private Long supplierId;

    @ApiModelProperty(name = "supplier", value = "供货商")
    private MaterialSupplier supplier;

    public static Expression<?>[] select(QMaterialEntity e){
        if(e.material.supplier!=null)
            return new Expression<?>[]{
                    e.id,e.vatNo,e.image,e.color,e.deleted,e.price,e.material.name,e.material.type,e.material.unitType,e.material.unit,e.material.supplier.id
            };
        return new Expression<?>[]{
                    e.id,e.vatNo,e.image,e.color,e.deleted,e.price,e.material.name,e.material.type,e.material.unitType,e.material.unit
        };
    }

    @Override
    public MaterialEntityVO fromTuple(Tuple t, QMaterialEntity e) {
        id=t.get(e.id);
        vatNo=t.get(e.vatNo);
        image=t.get(e.image);
        color=t.get(e.color);
        deleted=t.get(e.deleted);
        price=t.get(e.price);
        name=t.get(e.material.name);
        type=t.get(e.material.type);
        unitType=t.get(e.material.type);
        unit=t.get(e.material.unit);
        if(e.material.supplier!=null)
        supplierId=t.get(e.material.supplier.id);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public MaterialSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MaterialSupplier supplier) {
        this.supplier = supplier;
    }

}
