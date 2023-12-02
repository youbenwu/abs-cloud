package com.outmao.ebs.jnet.vo.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.jnet.entity.factory.Industry;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.factory.ProductionTechnology;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "IndustryVO", description = "行业分类")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class IndustryVO  implements Serializable {

    @ApiModelProperty(name = "id", value = "行业分类ID")
    private Long id;
//    @ApiModelProperty(name = "parentId", value = "上级行业分类ID")
//    private Long parentId;
//    @ApiModelProperty(name = "level", value = "级数从0开始")
//    private int level;
//    @ApiModelProperty(name = "leaf", value = "是否最后一级")
//    private boolean leaf;
//    @ApiModelProperty(name = "children", value = "子行业分类列表")
//    private List<IndustryVO> children;
    @ApiModelProperty(name = "name", value = "行业名称")
    private String name;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(name = "productionTechnologies", value = "行业分类下的生产工艺列表")
    private List<ProductionTechnology> productionTechnologies;
    @ApiModelProperty(name = "productionTechnologies", value = "行业分类下的生产品类列表")
    private List<ProductionCategory> productionCategories;


    public IndustryVO(){}

    public IndustryVO(Industry industry){
        BeanUtils.copyProperties(industry,this);
//        parentId=industry.getParent()==null?null:industry.getParent().getId();
//        if(industry.getChildren().size()>0){
//            children=new ArrayList<>(industry.getChildren().size());
//            for (Industry sub : industry.getChildren()){
//                children.add(new IndustryVO(sub));
//            }
//        }
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
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
//    public boolean isLeaf() {
//        return leaf;
//    }
//
//    public void setLeaf(boolean leaf) {
//        this.leaf = leaf;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//    public List<IndustryVO> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<IndustryVO> children) {
//        this.children = children;
//    }

    public List<ProductionTechnology> getProductionTechnologies() {
        return productionTechnologies;
    }

    public void setProductionTechnologies(List<ProductionTechnology> productionTechnologies) {
        this.productionTechnologies = productionTechnologies;
    }

    public List<ProductionCategory> getProductionCategories() {
        return productionCategories;
    }

    public void setProductionCategories(List<ProductionCategory> productionCategories) {
        this.productionCategories = productionCategories;
    }

}
