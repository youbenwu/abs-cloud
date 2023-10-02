package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "HouseAttributesDTO", description = "保存房源参数")
@Data
public class HouseAttributesDTO {


//    @ApiModelProperty(name = "houseDistrict", value = "房子地区,前端从Address中获取传过来，用于在列表中显示 如：白云 白云智慧城")
//    private String houseDistrict;
//
//    @ApiModelProperty(name = "houseArea", value = "房屋面积,前端从SKU中获取传过来，用于在列表中显示 如：建面112-140m²")
//    private String houseArea;
//
//    @ApiModelProperty(name = "doorModel", value = "户型，前端从SKU中获取传过来，用于在列表中显示 如：1房1厅")
//    private String doorModel;

    /**
     *
     *
     * 租房信息
     *
     *
     * */

    @ApiModelProperty(name = "houseInTime", value = "入住：随时")
    private String houseInTime;

    @ApiModelProperty(name = "houseLease", value = "租期：1年以上")
    private String houseLease;

    @ApiModelProperty(name = "houseFurn", value = "配备：洗衣机、空调、衣柜、电视、冰箱、热水器、床")
    private String houseFurn;

    @ApiModelProperty(name = "deposit", value = "押金")
    private Double deposit;

    @ApiModelProperty(name = "rentPay", value = "租金付款方式 如：0--月付 1--年付")
    private Integer rentPay;

    @ApiModelProperty(name = "serviceFee", value = "服务费")
    private Double serviceFee;

    @ApiModelProperty(name = "agencyFee", value = "中介费")
    private Double agencyFee;

    /**
     *
     *
     * 二手房信息
     *
     *
     * */

    @ApiModelProperty(name = "houseLift", value = "电梯")
    private String houseLift;

    @ApiModelProperty(name = "houseDecor", value = "装修")
    private String houseDecor;

    @ApiModelProperty(name = "houseEra", value = "年代")
    private String houseEra;

    @ApiModelProperty(name = "houseUse", value = "用途")
    private String houseUse;

    @ApiModelProperty(name = "houseCommunity", value = "所属小区")
    private String houseCommunity;

    @ApiModelProperty(name = "houseCommunityId", value = "所属小区ID")
    private Long houseCommunityId;



//    @ApiModelProperty(name = "houseListed", value = "挂牌")
//    private String houseListed;
//
//    @ApiModelProperty(name = "houseOwnership", value = "权属类别")
//    private String houseOwnership;




    /**
     *
     *
     * 建筑规划
     *
     *
     * */
    @ApiModelProperty(name = "houseType", value = "建筑类型--住宅/公寓/商用")
    private String houseType;

    @ApiModelProperty(name = "housePropYear", value = "产权年限：n年")
    private Integer housePropYear;

    @ApiModelProperty(name = "houseFar", value = "容积率：n%")
    private Double houseFar;

    @ApiModelProperty(name = "houseGsr", value = "绿化率：n%")
    private Double houseGsr;

    @ApiModelProperty(name = "houseHoldNum", value = "规划用户：n")
    private Integer houseHoldNum;

    @ApiModelProperty(name = "houseFloorNum", value = "楼层状况：n层")
    private Integer houseFloorNum;


    @ApiModelProperty(name = "houseFloorArea", value = "占地面积：n m²")
    private Double houseFloorArea;

    @ApiModelProperty(name = "houseLandArea", value = "建地面积：n m²")
    private Double houseLandArea;

    @ApiModelProperty(name = "houseDev", value = "开发商")
    private String houseDev;

    @ApiModelProperty(name = "houseDevId", value = "开发商ID")
    private Long houseDevId;


    /**
     *
     *  物业信息
     *
     * */

    @ApiModelProperty(name = "propCompany", value = "物业公司名称")
    private String propCompany;

    @ApiModelProperty(name = "propFee", value = "物业费用(元/m²/月)")
    private Double propFee;

    @ApiModelProperty(name = "propPartThan", value = "车位比")
    private String propPartThan;

    @ApiModelProperty(name = "propParkNum", value = "车位数(个)")
    private Integer propParkNum;

    @ApiModelProperty(name = "propPartGNum", value = "地上车位数(个)")
    private Integer propPartGNum;

    @ApiModelProperty(name = "propPartLNum", value = "地下车位数(个)")
    private Integer propPartLNum;

    @ApiModelProperty(name = "propHeatType", value = "供暖方式")
    private String propHeatType;

    @ApiModelProperty(name = "propWaterType", value = "供水方式")
    private String propWaterType;

    @ApiModelProperty(name = "propPowerType", value = "供电方式")
    private String propPowerType;



}
