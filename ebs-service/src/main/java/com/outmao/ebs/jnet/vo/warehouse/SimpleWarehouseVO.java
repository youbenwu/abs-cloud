package com.outmao.ebs.jnet.vo.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.warehouse.QWarehouse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "SimpleWarehouseVO", description = "仓库信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class SimpleWarehouseVO implements DslVO<QWarehouse> {

    /*
     *
     * ID
     *
     * */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;


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
    @ApiModelProperty(name = "managerId", value = "管理员ID")
    private Long managerId;

    @ApiModelProperty(name = "status", value = "0--正常，1--关闭")
    private int status;

    /*
     *
     * 仓库名称
     *
     * */
    @ApiModelProperty(name = "name", value = "仓库名称")
    private String name;

    /*
     *
     * 仓号
     *
     * */
    @ApiModelProperty(name = "warehouseNo", value = "仓号")
    private String warehouseNo;


    /*
     *
     * 仓库主题图片
     *
     * */
    @ApiModelProperty(name = "themeImage", value = "仓库主题图片")
    private String themeImage;


    /*
     *
     * 仓库主题背景色
     *
     * */
    @ApiModelProperty(name = "themeColor", value = "仓库主题背景色")
    private String themeColor;


    /*
     *
     * 成员数
     *
     * */
    @ApiModelProperty(name = "members", value = "成员数")
    private int members;

    /*
     *
     * 成员ID
     *
     * */
    @ApiModelProperty(name = "memberIds", value = "成员ID")
    private String memberIds;

    /*
     *
     * 成员头像
     *
     * */
    @ApiModelProperty(name = "memberAvatars", value = "成员头像")
    private String memberAvatars;


    /*
     *
     * 库存总成本
     *
     * */
    @ApiModelProperty(name = "totalAmount", value = "库存采购总成本")
    private double totalAmount;


    /*
     *
     * 库存价值
     *
     * */
    @ApiModelProperty(name = "amount", value = "库存价值")
    private double amount;

    /*
     *
     * 总采购库存量之和、无视单位
     *
     * */
    @ApiModelProperty(name = "totalQuantity", value = "总采购库存量之和、无视单位")
    private double totalQuantity;

    /*
     *
     * 当前库存量之和、无视单位
     *
     * */
    @ApiModelProperty(name = "quantity", value = "当前库存量之和、无视单位")
    private double quantity;


    /*
     *
     * 库存剩余百分比
     *
     * */
    @ApiModelProperty(name = "quantityPer", value = "库存剩余百分比")
    private double quantityPer;


    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;



    public static Expression<?>[] select(QWarehouse e){
        return new Expression<?>[]{
                e.id,e.user.id,e.manager.id,e.status,e.name,e.warehouseNo,e.themeImage,e.themeColor,
                e.members,e.memberIds,e.memberAvatars,e.totalAmount,e.amount,
                e.totalQuantity,e.quantity,e.quantityPer,e.createTime,e.updateTime
        };

    }

    @Override
    public SimpleWarehouseVO fromTuple(Tuple t, QWarehouse e) {
        id=t.get(e.id);
        userId=t.get(e.user.id);
        managerId=t.get(e.manager.id);
        status=t.get(e.status);
        name=t.get(e.name);
        warehouseNo=t.get(e.warehouseNo);
        themeColor=t.get(e.themeColor);
        themeImage=t.get(e.themeImage);
        members=t.get(e.members);
        memberIds=t.get(e.memberIds);
        memberAvatars=t.get(e.memberAvatars);
        totalAmount=t.get(e.totalAmount);
        amount=t.get(e.amount);
        totalQuantity=t.get(e.totalQuantity);
        quantity=t.get(e.quantity);
        quantityPer=t.get(e.quantityPer);
        createTime=t.get(e.createTime);
        updateTime=t.get(e.updateTime);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getMemberAvatars() {
        return memberAvatars;
    }

    public void setMemberAvatars(String memberAvatars) {
        this.memberAvatars = memberAvatars;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantityPer() {
        return quantityPer;
    }

    public void setQuantityPer(double quantityPer) {
        this.quantityPer = quantityPer;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
