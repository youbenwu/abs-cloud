package com.outmao.ebs.jnet.entity.index;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* 首页功能模块
*
* @author yeyi
* @date 2019-09-23 16:27:50
*/
@Entity
@Table(name="z_function")
@DynamicInsert
@DynamicUpdate
public class Function{

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

	@Column(name="name", length=32)
    private String name;    // 模块名

	@Column(name="use_count")
    private Long useCount;    // 使用次数
	
	@Column(name="img_url", length=255)
    private String imgUrl;    // 图片 url
	
	@Column(name="page_url", length=255)
    private String pageUrl;    // 页面路径

    @Column(name="banner_url", length=2048)
    private String bannerUrl;

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
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Long getUseCount(){
        return this.useCount;
    }
    public void setUseCount(Long useCount){
        this.useCount=useCount;
    }
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}