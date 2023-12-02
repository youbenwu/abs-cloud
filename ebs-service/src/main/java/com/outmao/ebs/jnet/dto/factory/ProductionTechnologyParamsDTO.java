package com.outmao.ebs.jnet.dto.factory;

public class ProductionTechnologyParamsDTO {

    private Long id;
    private Long parentId;
    private Long industryId;
    private String name;
    private String suffix;

    public ProductionTechnologyParamsDTO(){}

    public ProductionTechnologyParamsDTO(Long id,Long parentId,Long industryId,String name,String suffix){
        this.id=id;
        this.parentId=parentId;
        this.industryId=industryId;
        this.name=name;
        this.suffix=suffix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
