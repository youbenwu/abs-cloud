package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "HouseSkuPropertyDTO", description = "保存房源户型规格参数")
@Data
public class HouseSkuPropertyDTO {


    @ApiModelProperty(name = "houseRoomNum", value = "户型室数",required = true)
    private int houseRoomNum;

    @ApiModelProperty(name = "houseHallNum", value = "户型厅数",required = true)
    private int houseHallNum;

    @ApiModelProperty(name = "houseBathNum", value = "户型卫数",required = true)
    private int houseBathNum;

    @ApiModelProperty(name = "houseArea", value = "房屋面积(平方米)")
    private Integer houseArea;

    @ApiModelProperty(name = "houseDirection", value = "朝向：南")
    private String houseDirection;

    @ApiModelProperty(name = "houseFloor", value = "楼层 房屋所在的层号")
    private Integer houseFloor;



}
