package com.outmao.ebs.jnet.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.jnet.entity.storage.Storage;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import com.outmao.ebs.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 订单
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity(name = "ZOrder")
@Table(name = "z_Order")
public class Order implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 下单客户
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 生产方
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "producerId")
    private User producer;

    /**
     * 仓库
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "storageId")
    private Storage storage;

    /**
     * 物料仓库
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouse;


    /**
     * 状态
     */
    // 订单状态，0：预下单 1：预付款 2：生产中 3：待结算 4：已完成 5：待退单 6：待清算 7：已退单 8：已撤单 9：已删除
    private int status;

    /**
     * 子状态
     */
    // 订单子状态，status=0：0：待管家接受 1：管家已拒绝 2：待管家确定参考单价 3：待管家确定单价 4:待客户确认单价 5:客户已拒绝单价 6：待客户编辑色组下单
    private int subStatus;

    /**
     *
     * 评价 0--未评 1--一般 2--满意 3--超赞
     *
     */
    private int appraiseType;

    /**
     * 图片
     */
    private String image;

    /**
     * 批号
     */
    private String lotNum;

    /**
     * 固定需求
     */
    private boolean fixed;

    /**
     * 单价
     */
    private double price;


    /**
     * 退单理由
     */
    private String quitRemark;




    /**
     * 需求说明
     */
    private String spec;

    /**
     * 订单的备注说明
     */
    private String remark;


    /**
     *
     * 生产总数
     *
     */
    private int num;

    /**
     *
     * 已经完成生产数
     *
     */
    private int doneNum;


    /**
     * 预付款
     */
    private double preSum;


    /**
     * 已付款
     */
    private double paidSum;


    /**
     * 应付款
     */
    private double sum;


    /**
     * 下单时间
     */
    private Date placeTime;

    /**
     * 交货时间
     */
    private Date deliveryTime;

    /**
     * 完成时间
     */
    private Date finishTime;


    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 新建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getProducer() {
        return producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getQuitRemark() {
        return quitRemark;
    }

    public void setQuitRemark(String quitRemark) {
        this.quitRemark = quitRemark;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
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
