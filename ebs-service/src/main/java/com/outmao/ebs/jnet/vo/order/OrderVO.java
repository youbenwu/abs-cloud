package com.outmao.ebs.jnet.vo.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.jnet.entity.order.QOrder;
import com.outmao.ebs.user.domain.conver.ContactUserVOConver;
import com.outmao.ebs.user.vo.ContactUserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel(value = "OrderVO", description = "订单信息")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class OrderVO implements DslVO<QOrder> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "userId", value = "下单用户")
    private Long userId;

    @ApiModelProperty(name = "user", value = "下单用户")
    private ContactUserVO user;

    @ApiModelProperty(name = "producerId", value = "生产方ID")
    private Long producerId;

    @ApiModelProperty(name = "storageId", value = "仓库ID")
    private Long storageId;

    @ApiModelProperty(name = "warehouseId", value = "物料仓ID")
    private Long warehouseId;

    /**
     * 状态
     */
    // 订单状态，0：预下单 1：预付款 2：生产中 3：待结算 4：已完成 5：待退单 6：待清算 7：已退单 8：已撤单 9：已删除
    @ApiModelProperty(name = "status", value = "订单状态，0：预下单 1：预付款 2：生产中 3：待结算 4：已完成 5：待退单 6：待清算 7：已退单 8：已撤单 9：已删除")
    private int status;

    /**
     * 子状态
     */
    // 订单子状态，status=0：0：待管家接受 1：管家已拒绝 2：待管家确定参考单价 3：待管家确定单价 4:待客户确认单价 5:客户已拒绝单价 6：待客户编辑色组下单
    @ApiModelProperty(name = "subStatus", value = "订单子状态，status=0：0：待管家接受 1：管家已拒绝 2：待管家确定参考单价 3：待管家确定单价 4:待客户确认单价 5:客户已拒绝单价 6：待客户编辑色组下单\n")
    private int subStatus;

    /**
     *
     * 评价 0--未评 1--一般 2--满意 3--超赞
     *
     */
    @ApiModelProperty(name = "appraiseType", value = "评价 0--未评 1--一般 2--满意 3--超赞")
    private int appraiseType;

    /**
     * 图片
     */
    @ApiModelProperty(name = "image", value = "图片")
    private String image;

    /**
     * 批号
     */
    @ApiModelProperty(name = "lotNum", value = "批号")
    private String lotNum;

    /**
     * 固定需求
     */
    @ApiModelProperty(name = "fixed", value = "固定需求")
    private boolean fixed;

    /**
     * 单价
     */
    @ApiModelProperty(name = "price", value = "单价")
    private double price;


    /**
     * 退单理由
     */
    @ApiModelProperty(name = "quitRemark", value = "退单理由")
    private String quitRemark;



    /**
     * 需求说明
     */
    @ApiModelProperty(name = "spec", value = "需求说明")
    private String spec;

    /**
     * 订单的备注说明
     */
    @ApiModelProperty(name = "remark", value = "订单的备注说明")
    private String remark;

    /**
     *
     * 生产总数
     *
     */
    @ApiModelProperty(name = "num", value = "生产总数")
    private int num;

    /**
     *
     * 已经完成生产数
     *
     */
    @ApiModelProperty(name = "doneNum", value = "已经完成生产数")
    private int doneNum;


    /**
     * 预付款
     */
    @ApiModelProperty(name = "preSum", value = "预付款")
    private double preSum;


    /**
     * 已付款
     */
    @ApiModelProperty(name = "paidSum", value = "已付款")
    private double paidSum;


    /**
     * 应付款
     */
    @ApiModelProperty(name = "sum", value = "应付款")
    private double sum;


    /**
     * 下单时间
     */
    @ApiModelProperty(name = "placeTime", value = "下单时间")
    private Date placeTime;

    /**
     * 交货时间
     */
    @ApiModelProperty(name = "deliveryTime", value = "交货时间")
    private Date deliveryTime;

    /**
     * 完成时间
     */
    @ApiModelProperty(name = "finishTime", value = "完成时间")
    private Date finishTime;


    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


    /**
     * 新建时间
     */
    @ApiModelProperty(name = "createTime", value = "新建时间")
    private Date createTime;


    public static Expression<?>[] select(QOrder e){
        return ArrayUtil.merge(
                new ContactUserVOConver().select(e.user),
                new Expression<?>[]{
                        e.id,
                        e.producer.id,
                        e.storage.id,
                        e.warehouse.id,
                        e.status,e.subStatus,
                        e.appraiseType,
                        e.image,e.lotNum,
                        e.fixed,e.price,e.quitRemark,
                        e.spec,e.remark,e.preSum,e.paidSum,e.sum,
                        e.placeTime,e.deliveryTime,e.finishTime,e.updateTime,e.createTime

                }
        );

    }



    @Override
    public OrderVO fromTuple(Tuple t, QOrder e) {
        user=new ContactUserVOConver().fromTuple(t,e.user);
        userId=user.getId();
        id=t.get(e.id);
        producerId=t.get(e.producer.id);
        storageId=t.get(e.storage.id);
        warehouseId=t.get(e.warehouse.id);
        status=t.get(e.status);
        subStatus=t.get(e.subStatus);
        appraiseType=t.get(e.appraiseType);
        image=t.get(e.image);
        lotNum=t.get(e.lotNum);
        fixed=t.get(e.fixed);
        price=t.get(e.price);
        quitRemark=t.get(e.quitRemark);
        spec=t.get(e.spec);
        remark=t.get(e.remark);
        preSum=t.get(e.preSum);
        paidSum=t.get(e.paidSum);
        sum=t.get(e.sum);
        placeTime=t.get(e.placeTime);
        deliveryTime=t.get(e.deliveryTime);
        finishTime=t.get(e.finishTime);
        updateTime=t.get(e.updateTime);
        createTime=t.get(e.createTime);

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

    public ContactUserVO getUser() {
        return user;
    }

    public void setUser(ContactUserVO user) {
        this.user = user;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(int subStatus) {
        this.subStatus = subStatus;
    }

    public int getAppraiseType() {
        return appraiseType;
    }

    public void setAppraiseType(int appraiseType) {
        this.appraiseType = appraiseType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(int doneNum) {
        this.doneNum = doneNum;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuitRemark() {
        return quitRemark;
    }

    public void setQuitRemark(String quitRemark) {
        this.quitRemark = quitRemark;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getPreSum() {
        return preSum;
    }

    public void setPreSum(double preSum) {
        this.preSum = preSum;
    }

    public double getPaidSum() {
        return paidSum;
    }

    public void setPaidSum(double paidSum) {
        this.paidSum = paidSum;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Date placeTime) {
        this.placeTime = placeTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
