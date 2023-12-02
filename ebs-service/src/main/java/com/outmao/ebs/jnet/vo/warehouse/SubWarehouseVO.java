package com.outmao.ebs.jnet.vo.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.warehouse.QSubWarehouse;
import com.outmao.ebs.user.domain.conver.ContactUserVOConver;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "SubWarehouseVO", description = "子仓库详细信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class SubWarehouseVO implements DslVO<QSubWarehouse> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "warehouseId", value = "主仓库编号")
    private Long warehouseId;

    @ApiModelProperty(name = "warehouse", value = "主仓库信息")
    private WarehouseVO warehouse;

    @ApiModelProperty(name = "user", value = "用户")
    private ContactUserVO user;

    /*
     *
     * 成员类型 0--普通成员 1--管理员
     *
     * */
    @ApiModelProperty(name = "memberType", value = "成员类型 0--普通成员 1--管理员")
    private int memberType;

    /*
     *
     * 仓库之间的会话状态
     * 0--正常 1--收到物料单 2--发了物料单
     *
     * */
    @ApiModelProperty(name = "sessionStatus", value = "仓库之间的会话状态 0--正常 1--收到物料单 2--发了物料单")
    private int sessionStatus;


    /*
     *
     * 当前处理的物料单
     *
     * */
    @ApiModelProperty(name = "billId", value = "当前处理的物料单")
    private Long billId;


    /*
     *
     * 库存价值
     *
     * */
    @ApiModelProperty(name = "amount", value = "库存价值")
    private double amount;

    /*
     *
     * 当前库存量之和、无视单位
     *
     * */
    @ApiModelProperty(name = "quantity", value = "当前库存量之和、无视单位")
    private double quantity;


    /*
     *
     * 库存占主仓百分比
     *
     * */
    @ApiModelProperty(name = "quantityPer", value = "库存占主仓百分比")
    private double quantityPer;


    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "时间")
    private Date createTime;

    /*
     *
     * 时间
     *
     * */
    @ApiModelProperty(name = "updateTime", value = "时间")
    private Date updateTime;

    @ApiModelProperty(name = "materials", value = "物料列表")
    private List<SubWarehouseMaterialVO> materials;


    @ApiModelProperty(name = "subs", value = "最近有业务来往的成员子仓库列表")
    private List<SubWarehouseVO> subs;

    public static Expression<?>[] select(QSubWarehouse e){
        return ArrayUtil.merge(
                new ContactUserVOConver().select(e.user),
                new Expression<?>[]{
                        e.id,e.warehouse.id,e.memberType,
                        e.amount,e.quantity,e.quantityPer,e.createTime,e.updateTime,
                }
        );

    }

    @Override
    public SubWarehouseVO fromTuple(Tuple t, QSubWarehouse e) {

        id=t.get(e.id);
        warehouseId=t.get(e.warehouse.id);
        memberType=t.get(e.memberType);

        amount=t.get(e.amount);
        quantity=t.get(e.quantity);
        quantityPer=t.get(e.quantityPer);
        createTime=t.get(e.createTime);
        updateTime=t.get(e.updateTime);

        user=new ContactUserVOConver().fromTuple(t,e.user);

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public WarehouseVO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseVO warehouse) {
        this.warehouse = warehouse;
    }

    public ContactUserVO getUser() {
        return user;
    }

    public void setUser(ContactUserVO user) {
        this.user = user;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public int getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(int sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public List<SubWarehouseMaterialVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<SubWarehouseMaterialVO> materials) {
        this.materials = materials;
    }

    public List<SubWarehouseVO> getSubs() {
        return subs;
    }

    public void setSubs(List<SubWarehouseVO> subs) {
        this.subs = subs;
    }
}
