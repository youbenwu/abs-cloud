package com.outmao.ebs.jnet.entity.index;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* 首页 banner 
*
* @author yeyi
* @date 2019-10-19 15:30:17
*/
@Entity
@Table(name="z_index_banner")
@DynamicInsert
@DynamicUpdate
public class IndexBanner{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="update_time")
    private Date updateTime;

	@Column(name="create_time")
    private Date createTime;

	@Column(name="is_deleted")
    private Boolean isDeleted;    // 0未删除 1已删除

	@Column(name="img_url", length=255)
    private String imgUrl;    // 图片 url

	@Column(name="remark", length=64)
    private String remark;    // 备注

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
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted=isDeleted;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark=remark;
    }

}