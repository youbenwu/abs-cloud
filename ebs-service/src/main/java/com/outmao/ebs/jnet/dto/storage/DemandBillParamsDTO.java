package com.outmao.ebs.jnet.dto.storage;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "DemandBillParamsDTO", description = "订单加单")
public class DemandBillParamsDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "orderId", value = "订单ID")
    private Long orderId;

    @ApiModelProperty(name = "storageId", value = "仓库ID")
    private Long storageId;

    @ApiModelProperty(name = "styles", value = "款形列表")
    private List<DemandBillStyleParamsDTO> styles;

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

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public List<DemandBillStyleParamsDTO> getStyles() {
        return styles;
    }

    public void setStyles(List<DemandBillStyleParamsDTO> styles) {
        this.styles = styles;
    }
}
