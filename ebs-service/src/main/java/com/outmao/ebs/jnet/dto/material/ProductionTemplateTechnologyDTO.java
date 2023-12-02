package com.outmao.ebs.jnet.dto.material;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 样版工艺DTO
* @author yeyi
* @date 2019/11/30 22:43
**/
@ApiModel(value = "ProductionTemplateTechnologyDTO", description = "样版工艺DTO")
public class ProductionTemplateTechnologyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "样版ID，创建时不传")
    private Long id;

    @ApiModelProperty(name = "templateId", value = "z_production_template 表 id")
    private Long templateId;

    @ApiModelProperty(name = "name", value = "工艺名")
    @NotNull
    @Size(min=1, max=255)
    private String name;

    @ApiModelProperty(name = "cost", value = "成本（元）")
    @NotNull
    @Size(min=1, max=32)
    private String cost;

    @ApiModelProperty(name = "timeSpend", value = "耗时（秒）")
    @NotNull
    private Long timeSpend;

    @ApiModelProperty(name = "weight", value = "排序用的权重，越大排越前")
    @NotNull
    private Integer weight;

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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Long getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(Long timeSpend) {
        this.timeSpend = timeSpend;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
