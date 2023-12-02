package com.outmao.ebs.jnet.entity.asssignment;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* 外发关联工艺
*
* @author yeyi
* @date 2019-10-25 17:17:56
*/
@Entity
@Table(name="z_my_assignment_technology")
@DynamicInsert
@DynamicUpdate
public class MyAssignmentTechnology{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="assignment_id")
    private Long assignmentId;    // z_my_assignment 表 id

	@Column(name="create_time")
    private Date createTime;    // 申请时间

	@Column(name="technology_id")
    private Long technologyId;    // 工艺ID(z_production_technology.id)

	@Column(name="technology_name", length=255)
    private String technologyName;    // 工艺名（冗余）

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getAssignmentId(){
        return this.assignmentId;
    }
    public void setAssignmentId(Long assignmentId){
        this.assignmentId=assignmentId;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Long getTechnologyId(){
        return this.technologyId;
    }
    public void setTechnologyId(Long technologyId){
        this.technologyId=technologyId;
    }
    public String getTechnologyName(){
        return this.technologyName;
    }
    public void setTechnologyName(String technologyName){
        this.technologyName=technologyName;
    }

}