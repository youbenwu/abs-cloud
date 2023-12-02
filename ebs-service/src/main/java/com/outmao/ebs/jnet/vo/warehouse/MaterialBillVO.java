package com.outmao.ebs.jnet.vo.warehouse;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.warehouse.QMaterialBill;
import com.outmao.ebs.user.domain.conver.ContactUserVOConver;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "MaterialBillVO", description = "物料单")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MaterialBillVO implements DslVO<QMaterialBill> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /*
     *
     * 主仓库
     *
     * */
    @ApiModelProperty(name = "warehouseId", value = "主仓库编号")
    private Long warehouseId;


    /*
     *
     * 物料单创建用户
     *
     * */
    @ApiModelProperty(name = "user", value = "物料单创建用户")
    private ContactUserVO user;


    private Long toUserId;
    /*
     *
     * 物料单接收用户
     *
     * */
    @ApiModelProperty(name = "toUser", value = "物料单接收用户")
    private ContactUserVO toUser;


    @ApiModelProperty(name = "name", value = "物料单名称")
    private String name;


    /*
     *
     * 物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单
     *
     * */
    @ApiModelProperty(name = "type", value = "物料单类型 0--成员转发单 1--采购单 2--外发单 3--回收单")
    private int type;

    /*
     *
     * 物料单状态 0--新创建未选择接收者 1--已发出、等待确认 2--对方已经确认 3--已修改、已确认的可修改 4--已取消
     *
     * */
    @ApiModelProperty(name = "status", value = "物料单状态 0--新创建未选择接收者 1--已发出、等待确认 2--对方已经确认 3--已修改、已确认的可修改 4--已取消")
    private int status;


    /*
     *
     * 二维码
     *
     * */
    @ApiModelProperty(name = "qrcode", value = "qrcode")
    private String qrcode;

    /*
     *
     * 金额
     *
     * */
    @ApiModelProperty(name = "amount", value = "amount")
    private double amount;


    /*
     *
     * 物料单创建时间
     *
     * */
    @ApiModelProperty(name = "createTime", value = "物料单创建时间")
    private Date createTime;

    /*
     *
     * 物料单更新时间
     *
     * */
    @ApiModelProperty(name = "updateTime", value = "物料单更新时间")
    private Date updateTime;

    @ApiModelProperty(name = "materials", value = "物料列表")
    private List<MaterialBillMaterialVO> materials;



    public static Expression<?>[] select(QMaterialBill e){
        return ArrayUtil.merge(
                new ContactUserVOConver().select(e.user),
                new Expression<?>[]{
                        e.id,e.warehouse.id,e.toUser.id,e.name,e.type,e.status,
                        e.qrcode,e.amount,e.createTime,e.updateTime
                }
        );

    }

    @Override
    public MaterialBillVO fromTuple(Tuple t, QMaterialBill e) {
        id=t.get(e.id);
        warehouseId=t.get(e.warehouse.id);
        toUserId=t.get(e.toUser.id);
        name=t.get(e.name);
        type=t.get(e.type);
        status=t.get(e.status);
        qrcode=t.get(e.qrcode);
        amount=t.get(e.amount);
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

    public ContactUserVO getUser() {
        return user;
    }

    public void setUser(ContactUserVO user) {
        this.user = user;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public ContactUserVO getToUser() {
        return toUser;
    }

    public void setToUser(ContactUserVO toUser) {
        this.toUser = toUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public List<MaterialBillMaterialVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialBillMaterialVO> materials) {
        this.materials = materials;
    }


}
