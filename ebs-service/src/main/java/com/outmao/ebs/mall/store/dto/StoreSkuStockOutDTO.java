package com.outmao.ebs.mall.store.dto;


import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import lombok.Data;
import java.util.List;

@Data
public class StoreSkuStockOutDTO {


    /**
     *
     * 门店ID
     *
     */
    private Long storeId;

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
     * 明细
     *
     */
    private List<StoreSkuStockOutItemDTO> items;


    /**
     *
     * 备注
     *
     */
    private String remark;

}
