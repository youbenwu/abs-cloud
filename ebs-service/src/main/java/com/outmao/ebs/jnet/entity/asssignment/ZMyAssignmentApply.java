package com.outmao.ebs.jnet.entity.asssignment;

import com.outmao.ebs.common.dsl.DslVO;
import com.querydsl.core.Tuple;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
* 外发申请
*
* @author yeyi
* @date 2019-09-03 00:31:27
*/
@Entity
@Table(name="z_my_assignment_apply")
@DynamicInsert
@DynamicUpdate
public class ZMyAssignmentApply implements DslVO<QZMyAssignmentApply> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;

	@Column(name="update_time")
    private Date updateTime;

	@Column(name="create_time")
    private Date createTime;    // 申请时间

	@Column(name="is_deleted")
    private Boolean isDeleted;    // 0未删除 1已删除

	@Column(name="assignment_id")
    private Long assignmentId;    // 外发单ID(z_my_assignment.id)

	@Column(name="user_id")
    private Long userId;    // 申请者ID(ebs_user.id)

	@Column(name="selected")
    private Boolean selected;    // false未选中 true选中

	@Column(name="price", columnDefinition = "decimal(10,4)")
    private BigDecimal price;    // 报价，最多四位小数

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
    public Long getAssignmentId(){
        return this.assignmentId;
    }
    public void setAssignmentId(Long assignmentId){
        this.assignmentId=assignmentId;
    }
    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public Boolean getSelected(){
        return this.selected;
    }
    public void setSelected(Boolean selected){
        this.selected=selected;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
	@Override
	public ZMyAssignmentApply fromTuple(Tuple t, QZMyAssignmentApply e) {
		this.assignmentId = t.get(e.assignmentId);
		this.createTime = t.get(e.createTime);
		this.id = t.get(e.id);
		this.isDeleted = t.get(e.isDeleted);
		this.price = t.get(e.price);
		this.selected = t.get(e.selected);
		this.updateTime = t.get(e.updateTime);
		this.userId = t.get(e.userId);
		return this;
	}

}