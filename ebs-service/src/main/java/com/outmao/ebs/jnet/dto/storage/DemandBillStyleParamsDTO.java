package com.outmao.ebs.jnet.dto.storage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel(value = "DemandBillStyleParamsDTO", description = "订单加单款型参数")
public class DemandBillStyleParamsDTO {

    @ApiModelProperty(name = "id", value = "仓库中款型ID")
    private Long id;

    @ApiModelProperty(name = "templateId", value = "样板ID")
    private Long templateId;

    @ApiModelProperty(name = "name", value = "款型名称")
    private String name;

    @ApiModelProperty(name = "specs", value = "规格列表")
    private List<DemandBillStyleSpecParamsDTO> specs;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DemandBillStyleSpecParamsDTO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<DemandBillStyleSpecParamsDTO> specs) {
        this.specs = specs;
    }

}
