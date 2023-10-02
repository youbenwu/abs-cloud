package com.outmao.ebs.mall.product.dto;


import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "HouseCommunityDTO", description = "保存小区信息参数")
@Data
public class HouseCommunityDTO {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "name", value = "小区名称")
    private String name;

    @ApiModelProperty(name = "address", value = "地址")
    private Address address;

    @ApiModelProperty(name = "businessCircle", value = "商圈")
    private String businessCircle;

    @ApiModelProperty(name = "houseType", value = "房屋用途")
    private String houseType;
    @ApiModelProperty(name = "houseBuildType", value = "建筑类型: 高层")
    private String houseBuildType;
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

    @ApiModelProperty(name = "housePropCompany", value = "物业公司名称")
    private String housePropCompany;
    @ApiModelProperty(name = "housePropFee", value = "物业费用(元/m²/月)")
    private Double housePropFee;
    @ApiModelProperty(name = "housePartThan", value = "车位比")
    private String housePartThan;
    @ApiModelProperty(name = "houseParkNum", value = "车位数(个)")
    private Integer houseParkNum;
    @ApiModelProperty(name = "housePartGNum", value = "地上车位数(个)")
    private Integer housePartGNum;
    @ApiModelProperty(name = "housePartLNum", value = "地下车位数(个)")
    private Integer housePartLNum;
    @ApiModelProperty(name = "houseHeatType", value = "供暖方式")
    private String houseHeatType;
    @ApiModelProperty(name = "houseWaterType", value = "供水方式")
    private String houseWaterType;
    @ApiModelProperty(name = "housePowerType", value = "供电方式")
    private String housePowerType;
    @ApiModelProperty(name = "trafficInfo", value = "交通信息")
    private String trafficInfo;


}
