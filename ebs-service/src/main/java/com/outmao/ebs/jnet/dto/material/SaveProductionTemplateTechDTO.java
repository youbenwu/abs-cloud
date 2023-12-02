package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "SaveProductionTemplateTechDTO", description = "更新样板工艺")
public class SaveProductionTemplateTechDTO {

    @ApiModelProperty(name = "templateId", value = "样板ID")
    private Long templateId;

    @ApiModelProperty(name = "technologies", value = "样版工艺列表")
    private List<ProductionTemplateTechnologyDTO> technologies;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public List<ProductionTemplateTechnologyDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<ProductionTemplateTechnologyDTO> technologies) {
        this.technologies = technologies;
    }
}
