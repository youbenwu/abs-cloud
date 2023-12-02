package com.outmao.ebs.jnet.dto.factory;

public class IndustryParamsDTO {

    private Long id;
    //private Long parentId;
    private String name;

    public IndustryParamsDTO(){}

    public IndustryParamsDTO(Long id,String name){
        this.id=id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(Long parentId) {
//        this.parentId = parentId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
