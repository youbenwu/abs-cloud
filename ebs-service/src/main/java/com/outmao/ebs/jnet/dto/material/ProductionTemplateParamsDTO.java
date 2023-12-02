package com.outmao.ebs.jnet.dto.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "ProductionTemplateParamsDTO", description = "保存模板信息")
public class ProductionTemplateParamsDTO {

    @ApiModelProperty(name = "id", value = "不传为新增")
    private Long id;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "factoryId", value = "工厂ID")
    private Long factoryId;

    @ApiModelProperty(name = "categoryId", value = "品类ID")
    private Long categoryId;

    /*
     *
     * 款号
     *
     * */
    @ApiModelProperty(name = "code", value = "款号")
    private String code;

    /*
     *
     * 名称
     *
     * */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /*
     *
     * 图片
     *
     * */
    @ApiModelProperty(name = "images", value = "图片")
    private String images;


    /*
     *
     * 生产价格
     *
     * */
    @ApiModelProperty(name = "productionPrice", value = "生产价格")
    private double productionPrice;

    /*
     *
     * 零售价格
     *
     * */
    @ApiModelProperty(name = "retailPrice", value = "零售价格")
    private double retailPrice;

    @ApiModelProperty(name = "technologies", value = "样版工艺列表")
    private List<ProductionTemplateTechnologyDTO> technologies;

    @ApiModelProperty(name = "others", value = "样版其它信息")
    private String others;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getProductionPrice() {
        return productionPrice;
    }

    public void setProductionPrice(double productionPrice) {
        this.productionPrice = productionPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public List<ProductionTemplateTechnologyDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<ProductionTemplateTechnologyDTO> technologies) {
        this.technologies = technologies;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
