package com.outmao.ebs.jnet.vo.assignment;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentApplyTechnology;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyi
 * @date 2019年9月7日
 */
@ApiModel(value = "ZMyAssignmentApplyTechnonogyVO", description = "外发申请关联工艺")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ZMyAssignmentApplyTechnonogyVO {

    @ApiModelProperty(name = "id", value = "工艺ID")
    private Long id;

    @ApiModelProperty(name = "name", value = "工艺名")
    private String name;

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
    
    public static ZMyAssignmentApplyTechnonogyVO fromMyAssignmentApplyTechnology(MyAssignmentApplyTechnology tech) {
    	ZMyAssignmentApplyTechnonogyVO result = new ZMyAssignmentApplyTechnonogyVO();
    	result.setId(tech.getTechnologyId());
    	result.setName(tech.getTechnologyName());
    	return result;
    }
    
    public static List<ZMyAssignmentApplyTechnonogyVO> fromMyAssignmentApplyTechnologies(List<MyAssignmentApplyTechnology> teches) {
    	List<ZMyAssignmentApplyTechnonogyVO> result = new ArrayList<>(teches.size());
    	for(MyAssignmentApplyTechnology tech: teches) {
    		result.add(ZMyAssignmentApplyTechnonogyVO.fromMyAssignmentApplyTechnology(tech));
    	}
    	return result;
    }
}
