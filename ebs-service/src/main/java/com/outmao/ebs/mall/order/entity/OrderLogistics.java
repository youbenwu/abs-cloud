package com.outmao.ebs.mall.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 订单物流信息
 *
 * */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_OrderLogistics")
public class OrderLogistics  implements Serializable {

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

//    /**
//     * 订单
//     */
//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "orderId")
//    private Order  order;

    @Column(updatable = false)
    private Long orderId;

    /**
     * 订单物流状态记录
     */
    @JsonIgnore
    @OneToMany(mappedBy = "logistics",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    private List<OrderLogisticsStatus> statuses;

    /**
     *
     * 发货地址
     *
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name", column=@Column(name = "from_name"))
            , @AttributeOverride(name="phone", column = @Column(name = "from_phone"))
            , @AttributeOverride(name="address", column = @Column(name = "from_address"))
    })
    private SimpleContact from;


    /**
     *
     * 收货地址
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
     * 自定义物流状态
     *
     */
    private int status;

    /**
     *
     * 自定义物流状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * 自定义物流状态信息
     *
     */
    private String statusContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
