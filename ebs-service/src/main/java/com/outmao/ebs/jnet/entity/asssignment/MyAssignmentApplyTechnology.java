package com.outmao.ebs.jnet.entity.asssignment;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* 外发申请与工艺关联
*
* @author yeyi
* @date 2019-09-03 23:02:59
*/
@Entity
@Table(name="z_my_assignment_apply_technology")
@DynamicInsert
@DynamicUpdate
public class MyAssignmentApplyTechnology{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="create_time")
    private Date createTime;    // 申请时间

	@Column(name="assignment_apply_id")
    private Long assignmentApplyId;    // 外发申请(z_my_assignment_apply.id)

	@Column(name="technology_id")
    private Long technologyId;    // 工艺ID(z_production_technology.id)

	@Column(name="technology_name")
    private String technologyName;    // 工艺名（冗余）

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Long getAssignmentApplyId(){
        return this.assignmentApplyId;
    }
    public void setAssignmentApplyId(Long assignmentApplyId){
        this.assignmentApplyId=assignmentApplyId;
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