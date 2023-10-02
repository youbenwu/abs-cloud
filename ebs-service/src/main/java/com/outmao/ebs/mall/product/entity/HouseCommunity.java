package com.outmao.ebs.mall.product.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 小区信息
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@ApiModel(value = "HouseCommunity", description = "小区信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "ebs_HouseCommunity",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "district" }) })
public class HouseCommunity implements Serializable {

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



    @ApiModelProperty(name = "name", value = "小区名称",required = true)
    @Column(nullable = false)
    private String name;


    @ApiModelProperty(name = "marks", value = "小区特色，逗号隔开")
    private String marks;


    @ApiModelProperty(name = "propType", value = "物业类型")
    private String propType;


    @ApiModelProperty(name = "ownerType", value = "权属类型")
    private String ownerType;


//
//    @ApiModelProperty(name = "houseType", value = "房屋用途")
//    private String houseType;



    @ApiModelProperty(name = "houseFloorNum", value = "楼层状况：n层")
    private Integer houseFloorNum;

    @ApiModelProperty(name = "houseFar", value = "容积率：n%")
    private Double houseFar;

    @ApiModelProperty(name = "houseGsr", value = "绿化率：n%")
    private Double houseGsr;

    @ApiModelProperty(name = "houseHoldNum", value = "规划用户：1876")
    private Integer houseHoldNum;

    @ApiModelProperty(name = "houseFloorArea", value = "占地面积：100000m²")
    private Double houseFloorArea;

    @ApiModelProperty(name = "houseLandArea", value = "建地面积：260000m²")
    private Double houseLandArea;

    @ApiModelProperty(name = "houseDev", value = "开发商：广州市xxxx房地产开发有限公司")
    private String houseDev;

    @ApiModelProperty(name = "address", value = "地址")
    @Embedded
    private Address address;

    @ApiModelProperty(name = "trafficInfo", value = "交通信息")
    private String trafficInfo;

    @ApiModelProperty(name = "businessCircle", value = "商圈")
    private String businessCircle;



    @ApiModelProperty(name = "housePropCompany", value = "物业公司名称")
    private String propCompany;

    @ApiModelProperty(name = "housePropFee", value = "物业费用(元/m²/月)")
    private Double propFee;

    @ApiModelProperty(name = "housePartThan", value = "车位比")
    private String propPartThan;

    @ApiModelProperty(name = "houseParkNum", value = "车位数(个)")
    private Integer propParkNum;

    @ApiModelProperty(name = "housePartGNum", value = "地上车位数(个)")
    private Integer propPartGNum;

    @ApiModelProperty(name = "housePartLNum", value = "地下车位数(个)")
    private Integer propPartLNum;

    @ApiModelProperty(name = "houseHeatType", value = "供暖方式")
    private String propHeatType;

    @ApiModelProperty(name = "houseWaterType", value = "供水方式")
    private String propWaterType;

    @ApiModelProperty(name = "housePowerType", value = "供电方式")
    private String propPowerType;


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
