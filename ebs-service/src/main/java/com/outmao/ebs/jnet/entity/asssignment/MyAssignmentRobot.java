package com.outmao.ebs.jnet.entity.asssignment;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
*
*
* @author yeyi
* @date 2019-11-04 16:26:44
*/
@Entity
@Table(name="z_my_assignment_robot")
@DynamicInsert
@DynamicUpdate
public class MyAssignmentRobot{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="update_time")
    private Date updateTime;

	@Column(name="create_time")
    private Date createTime;

	@Column(name="is_deleted")
    private Byte isDeleted;    // 0未删除 1已删除

	@Column(name="img_url", length=1000)
    private String imgUrl;    // 外发图片 url

	@Column(name="text", length=512)
    private String text;    // 外发正文内容

	@Column(name="assignment_id")
    private Long assignmentId;    // 关联的外发ID，0为未关联

	@Column(name="category_id")
    private Long categoryId;    // 外发所属品类ID

	@Column(name="apply_avatar", length=2048)
    private String applyAvatar;    // 申请者头像列表，多个用英文逗号分隔

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
    public Byte getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){
        this.isDeleted=isDeleted;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }
    public String getText(){
        return this.text;
    }
    public void setText(String text){
        this.text=text;
    }
    public Long getAssignmentId(){
        return this.assignmentId;
    }
    public void setAssignmentId(Long assignmentId){
        this.assignmentId=assignmentId;
    }
    public Long getCategoryId(){
        return this.categoryId;
    }
    public void setCategoryId(Long categoryId){
        this.categoryId=categoryId;
    }
    public String getApplyAvatar(){
        return this.applyAvatar;
    }
    public void setApplyAvatar(String applyAvatar){
        this.applyAvatar=applyAvatar;
    }

}