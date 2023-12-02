package com.outmao.ebs.jnet.entity.material;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
*
*
* @author yeyi
* @date 2019-11-30 21:23:32
*/
@Entity
@Table(name="z_production_template_technology")
@DynamicInsert
@DynamicUpdate
public class ProductionTemplateTechnology{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

    @Column(name="template_id")
    private Long templateId;    // z_production_template 表 id

    @Column(name="create_time")
    private Date createTime;    // 创建时间

    @Column(name="name", length=255)
    private String name;    // 工艺名

    @Column(name="is_deleted")
    private Byte isDeleted;    // 0未删除 1已删除

    @Column(name="cost")
    private BigDecimal cost;    // 成本（元）

    @Column(name="time_spend")
    private Long timeSpend;    // 耗时（秒）

    @Column(name="weight")
    private Integer weight;    // 排序用的权重，越大排越前

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getTemplateId(){
        return this.templateId;
    }
    public void setTemplateId(Long templateId){
        this.templateId=templateId;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Byte getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){
        this.isDeleted=isDeleted;
    }
    public BigDecimal getCost(){
        return this.cost;
    }
    public void setCost(BigDecimal cost){
        this.cost=cost;
    }
    public Long getTimeSpend(){
        return this.timeSpend;
    }
    public void setTimeSpend(Long timeSpend){
        this.timeSpend=timeSpend;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}