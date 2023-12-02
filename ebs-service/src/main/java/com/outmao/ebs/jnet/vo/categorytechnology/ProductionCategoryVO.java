package com.outmao.ebs.jnet.vo.categorytechnology;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.factory.QProductionCategory;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author yeyi
 * @date 2019年8月31日
 */
@ApiModel(value = "ProductionCategoryVO", description = "品类")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ProductionCategoryVO implements DslVO<QProductionCategory> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;
    
//    @ApiModelProperty(name = "parentId", value = "分类的父分类")
//    private Long parentId;
//    
//    @ApiModelProperty(name = "industryId", value = "行业ID")
//    private Long industryId;
    
    @ApiModelProperty(name = "level", value = "多级分类中所处的级别，级别从0开始")
    private int level;
    
    @ApiModelProperty(name = "leaf", value = "多级分类中是否是叶子节点的标识")
    private boolean leaf;

    @ApiModelProperty(name = "name", value = "品类名称")
    private String name;

    @ApiModelProperty(name = "image", value = "品类图片")
    private String image;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public Long getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(Long parentId) {
//		this.parentId = parentId;
//	}
//
//	public Long getIndustryId() {
//		return industryId;
//	}
//
//	public void setIndustryId(Long industryId) {
//		this.industryId = industryId;
//	}

	@Override
    public DslVO<QProductionCategory> fromTuple(Tuple t, QProductionCategory e) {
        this.id = t.get(e.id);
//        this.parentId = t.get(e.parent.id);
//        this.industryId = t.get(e.industry.id);
        this.level = t.get(e.level);
        this.leaf = t.get(e.leaf);
        this.name = t.get(e.name);
        this.image = t.get(e.image);
        this.createTime = t.get(e.createTime);
        return this;
    }
}
