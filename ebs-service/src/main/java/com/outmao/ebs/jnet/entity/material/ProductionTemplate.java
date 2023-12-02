package com.outmao.ebs.jnet.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.factory.Factory;
import com.outmao.ebs.jnet.entity.factory.ProductionCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 *
 * 模板
 *
 * */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name = "z_ProductionTemplate")
public class ProductionTemplate  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     *
     * ID
     *
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     *
     * 用户
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /*
     *
     * 工厂
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "factoryId")
    private Factory factory;

    /*
     *
     * 品类
     *
     * */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private ProductionCategory category;

    /*
     *
     * 原料列表
     *
     * */
    @JsonIgnore
    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY)
    private List<ProductionTemplateMaterial> materials;

    /*
     *
     * 款号
     *
     * */
    private String code;

    /*
     *
     * 名称
     *
     * */
    private String name;

    /*
     *
     * 图片
     *
     * */
    @Column(length = 2000)
    private String images;

    /*
     *
     * 生产价格
     *
     * */
    private double productionPrice;

    /*
     *
     * 零售价格
     *
     * */
    private double retailPrice;

    /*
     *
     * 创建时间
     *
     * */
    private Date createTime;


    /*
     *
     * 更新时间
     *
     * */
    private Date updateTime;

    /**
     * 样版其它数据
     */
    @Column(length = 255)
    private String others;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public List<ProductionTemplateMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<ProductionTemplateMaterial> materials) {
        this.materials = materials;
    }

    public ProductionCategory getCategory() {
        return category;
    }

    public void setCategory(ProductionCategory category) {
        this.category = category;
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

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
