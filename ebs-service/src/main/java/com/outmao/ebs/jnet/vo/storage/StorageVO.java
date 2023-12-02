package com.outmao.ebs.jnet.vo.storage;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.storage.QStorage;
import com.outmao.ebs.jnet.entity.storage.StorageBase;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "StorageVO", description = "仓库信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class StorageVO extends StorageBase implements DslVO<QStorage> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "orderId", value = "订单ID")
    private Long orderId;

    /*
     *
     * 创建用户
     *
     * */
    @ApiModelProperty(name = "userId", value = "创建用户")
    private Long userId;

    /*
     *
     * 管理员
     *
     * */
    @ApiModelProperty(name = "managerId", value = "管理员")
    private Long managerId;


    //0--正常 1--关闭
    @ApiModelProperty(name = "status", value = "0--正常 1--关闭")
    private int status;

    /*
     *
     * 仓库名称
     *
     * */
    @ApiModelProperty(name = "name", value = "仓库名称")
    private String name;


    @ApiModelProperty(name = "styles", value = "款型列表")
    private List<StorageStyleVO> styles;


    public static Expression<?>[] select(QStorage e){
        return new Expression<?>[]{
                e.id,e.order.id,e.user.id,e.manager.id,e.name,e.status,
                e.addNum,e.num,e.progress,e.sendNum,e.stockedNum,e.stockNum,e.unSendNum,
                e.createTime,e.updateTime
        };

    }

    @Override
    public StorageVO fromTuple(Tuple t, QStorage e) {
        id=t.get(e.id);
        orderId=t.get(e.order.id);
        managerId=t.get(e.manager.id);
        name=t.get(e.name);
        status=t.get(e.status);
        setAddNum(t.get(e.addNum));
        setProgress(t.get(e.progress));
        setSendNum(t.get(e.sendNum));
        setStockedNum(t.get(e.stockedNum));
        setStockNum(t.get(e.stockNum));
        setUnSendNum(t.get(e.unSendNum));
        setCreateTime(t.get(e.createTime));
        setUpdateTime(t.get(e.updateTime));

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StorageStyleVO> getStyles() {
        return styles;
    }

    public void setStyles(List<StorageStyleVO> styles) {
        this.styles = styles;
    }


}
