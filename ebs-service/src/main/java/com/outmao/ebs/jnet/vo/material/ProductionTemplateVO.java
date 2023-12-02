package com.outmao.ebs.jnet.vo.material;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.dto.material.ProductionTemplateTechnologyDTO;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;
import com.outmao.ebs.jnet.entity.material.QProductionTemplate;
import com.outmao.ebs.user.domain.conver.ContactUserVOConver;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "ProductionTemplateVO", description = "样板信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ProductionTemplateVO implements DslVO<QProductionTemplate> {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /*
     *
     * 用户
     *
     * */
    @ApiModelProperty(name = "user", value = "用户")
    private ContactUserVO user;

    /*
     *
     * 工厂
     *
     * */
    @ApiModelProperty(name = "factoryId", value = "工厂ID")
    private Long factoryId;

    /*
     *
     * 品类
     *
     * */
    @ApiModelProperty(name = "category", value = "品类")
    private ProductionCategory category;

    /*
     *
     * 原料列表
     *
     * */
    @ApiModelProperty(name = "materials", value = "原料列表")
    private List<ProductionTemplateMaterialVO> materials;

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

    /*
     *
     * 创建时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;


    /*
     *
     * 更新时间
     *
     * */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(name = "technologies", value = "样版工艺列表")
    private List<ProductionTemplateTechnologyDTO> technologies;

    @ApiModelProperty(name = "others", value = "样版其它")
    private String others;

    public static Expression<?>[] select(QProductionTemplate e){
        return ArrayUtil.merge(
                new ContactUserVOConver().select(e.user),
                new Expression<?>[]{
                        e.id,e.factory.id,e.category,e.code,e.name,e.images,e.productionPrice,e.retailPrice,e.createTime,e.updateTime,e.others,
                }
        );

    }

    @Override
    public ProductionTemplateVO fromTuple(Tuple t, QProductionTemplate e) {
        user=new ContactUserVOConver().fromTuple(t,e.user);
        id=t.get(e.id);
        factoryId=t.get(e.factory.id);
        category=t.get(e.category);
        code=t.get(e.code);
        name=t.get(e.name);
        images=t.get(e.images);
        productionPrice=t.get(e.productionPrice);
        retailPrice=t.get(e.retailPrice);
        createTime=t.get(e.createTime);
        updateTime=t.get(e.updateTime);
        this.setOthers(t.get(e.others));
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactUserVO getUser() {
        return user;
    }

    public void setUser(ContactUserVO user) {
        this.user = user;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public ProductionCategory getCategory() {
        return category;
    }

    public void setCategory(ProductionCategory category) {
        this.category = category;
    }

    public List<ProductionTemplateMaterialVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<ProductionTemplateMaterialVO> materials) {
        this.materials = materials;
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
