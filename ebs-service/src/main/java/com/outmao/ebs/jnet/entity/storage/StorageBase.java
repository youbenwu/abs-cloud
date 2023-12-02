package com.outmao.ebs.jnet.entity.storage;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class StorageBase {

    //总数量
    @ApiModelProperty(name = "num", value = "总数量")
    private int num;

    //待确认数量
    @ApiModelProperty(name = "addNum", value = "待确认数量")
    private int addNum;

    //待分配数量
    @ApiModelProperty(name = "unSendNum", value = "待分配数量")
    private int unSendNum;

    //分配中数量
    @ApiModelProperty(name = "sendNum", value = "分配中数量")
    private int sendNum;


    //入库中数量
    @ApiModelProperty(name = "stockNum", value = "入库中数量")
    private int stockNum;

    //己入库数量
    @ApiModelProperty(name = "stockedNum", value = "己入库数量")
    private int stockedNum;

    // 交货中
    @ApiModelProperty(name = "deliveryNum", value = "交货中")
    private int deliveryNum;

    //己交货数量
    @ApiModelProperty(name = "deliveredNum", value = "己交货数量")
    private int deliveredNum;

    //进度
    @ApiModelProperty(name = "progress", value = "进度")
    private double progress;


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


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public int getUnSendNum() {
        return unSendNum;
    }

    public void setUnSendNum(int unSendNum) {
        this.unSendNum = unSendNum;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getStockedNum() {
        return stockedNum;
    }

    public void setStockedNum(int stockedNum) {
        this.stockedNum = stockedNum;
    }

    public int getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(int deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public int getDeliveredNum() {
        return deliveredNum;
    }

    public void setDeliveredNum(int deliveredNum) {
        this.deliveredNum = deliveredNum;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
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
