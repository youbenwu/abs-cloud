package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 出库单
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_StoreSkuStockIn")
public class StoreSkuStockOut  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 门店ID
     *
     */
    @Column(updatable = false)
    private Long storeId;


    private boolean lockStock;

    private boolean lockAvailableStock;


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
    @Column(unique = true)
    private Long orderId;

    /**
     *
     * 发往
     *
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name", column=@Column(name = "to_name"))
            , @AttributeOverride(name="phone", column = @Column(name = "to_phone"))
            , @AttributeOverride(name="address", column = @Column(name = "to_address"))
    })
    private SimpleContact to;


    /**
     *
     * 物流信息
     *
     */
    @Embedded
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
    @Lob
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
