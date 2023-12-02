package com.outmao.ebs.jnet.dto.storage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DemandBillStyleSpecParamsDTO", description = "订单加单款型规格参数")
public class DemandBillStyleSpecParamsDTO {

    @ApiModelProperty(name = "id", value = "仓库中规格ID")
    private Long id;

    @ApiModelProperty(name = "name", value = "规格名称")
    private String name;

    @ApiModelProperty(name = "addNum", value = "加单数量")
    private int addNum;


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

    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }
}
