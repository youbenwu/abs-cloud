package com.outmao.ebs.jnet.vo.warehouse;

public class MaterialBillRecordMaterialVO {

    private Long id;

    private String name;

    private double quantityInc;

    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
