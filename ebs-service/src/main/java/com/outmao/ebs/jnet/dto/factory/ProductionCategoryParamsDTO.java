package com.outmao.ebs.jnet.dto.factory;

public class ProductionCategoryParamsDTO {

    private Long id;
    private Long parentId;
    private Long industryId;
    private String name;
    private String image;
    private int sort;

    public ProductionCategoryParamsDTO(){}

    public ProductionCategoryParamsDTO(Long id,Long parentId,Long industryId,String name,String image,int sort){
        this.id=id;
        this.parentId=parentId;
        this.industryId=industryId;
        this.name=name;
        this.image=image;
        this.sort=sort;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
