package com.outmao.ebs.jnet.entity.config;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* 存的数据库中的配置
*
* @author yeyi
* @date 2019-11-03 14:39:13
*/
@Entity(name = "ZConfig")
@Table(name="z_config")
@DynamicInsert
@DynamicUpdate
public class Config{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="update_time")
    private Date updateTime;

	@Column(name="create_time")
    private Date createTime;

	@Column(name="create_by", length=32)
    private String createBy;

	@Column(name="update_by", length=32)
    private String updateBy;

	@Column(name="is_deleted")
    private Byte isDeleted;    // 0未删除 1已删除

	@Column(name="_key", length=64)
    private String key;    // 参数名

	@Column(name="value", length=512)
    private String value;    // 参数值

	@Column(name="remark", length=128)
    private String remark;    // 参数说明

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public String getCreateBy(){
        return this.createBy;
    }
    public void setCreateBy(String createBy){
        this.createBy=createBy;
    }
    public String getUpdateBy(){
        return this.updateBy;
    }
    public void setUpdateBy(String updateBy){
        this.updateBy=updateBy;
    }
    public Byte getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){
        this.isDeleted=isDeleted;
    }
    public String getKey(){
        return this.key;
    }
    public void setKey(String key){
        this.key=key;
    }
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value=value;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark=remark;
    }

}