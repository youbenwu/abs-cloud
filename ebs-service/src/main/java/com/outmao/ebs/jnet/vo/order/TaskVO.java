package com.outmao.ebs.jnet.vo.order;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.order.QTask;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel(value = "TaskVO", description = "任务")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TaskVO implements DslVO<QTask> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;


    @ApiModelProperty(name = "userId", value = "成员ID")
    private Long userId;

    @ApiModelProperty(name = "orderId", value = "ID")
    private Long orderId;

    @ApiModelProperty(name = "order", value = "订单信息")
    private OrderVO order;

    @ApiModelProperty(name = "status", value = "0:待接受 1:已拒绝 2:生产中 3:待结算 4:已完成 5:已关闭")
    //0:待接受 1:已拒绝 2:生产中 3:待结算 4:已完成 5:已关闭
    private int status;

    @ApiModelProperty(name = "type", value = "0:管理员 1:成员")
    //0:管理员 1:成员
    private int type;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

    public static Expression<?>[] select(QTask e){
        return new Expression<?>[]{
                e.id,
                e.user.id,
                e.order.id,
                e.status,e.type,e.updateTime,e.createTime
        };

    }

    @Override
    public TaskVO fromTuple(Tuple t, QTask e) {
        id=t.get(e.id);
        userId=t.get(e.user.id);
        orderId=t.get(e.order.id);
        status=t.get(e.status);
        type=t.get(e.type);
        updateTime=t.get(e.updateTime);
        createTime=t.get(e.updateTime);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderVO getOrder() {
        return order;
    }

    public void setOrder(OrderVO order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

}
