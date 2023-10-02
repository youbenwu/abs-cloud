package com.outmao.ebs.mall.store.vo;

import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import lombok.Data;

import java.util.Date;

@Data
public class StoreSkuStockOutVO {


    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 门店ID
     *
     */
    private Long storeId;


    /**
     *
     * 状态
     * 0--末确认 1--待出库 2--已出库 3--已取消
     *
     */
    private int status;

    /**
     *
     * 状态
     *
     */
    private String statusRemark;

    /**
     *
     * 订单ID
     *
     */
    private Long orderId;

    /**
     *
     * 发往
     *
     */

    private SimpleContact to;


    /**
     *
     * 物流信息
     *
     */
    private LogisticsInfo logisticsInfo;


    /**
     *
     * 出库数量
     *
     */
    private long stock;


    /**
     *
     * 明细
     *
     */
    private String details;

    /**
     *
     * 备注
     *
     */
    private String remark;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;

}
