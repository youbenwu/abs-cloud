package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "HouseSkuPropertyVO", description = "房源户型规格参数")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class HouseSkuPropertyVO {


    /***
     *
     *
     *     //户型室数
     *     public static final String PROPERTY_KEY_HOUSE_ROOM_NUM="houseRoomNum";
     *
     *     //户型厅数
     *     public static final String PROPERTY_KEY_HOUSE_HALL_NUM="houseHallNum";
     *
     *     //户型卫数
     *     public static final String PROPERTY_KEY_HOUSE_BATH_NUM="houseBathNum";
     *
     *
     *     //房屋面积平方米 double
     *     public static final String PROPERTY_KEY_HOUSE_AREA="houseArea";
     *
     *
     *     //朝向：南
     *     public static final String PROPERTY_KEY_HOUSE_DIRECTION="houseDirection";
     *
     *     //楼层 房屋所在的层
     *     public static final String PROPERTY_KEY_HOUSE_FLOOR="houseFloor";
     *
     * */



    @ApiModelProperty(name = "houseRoomNum", value = "户型室数",required = true)
    private String houseRoomNum;
    @ApiModelProperty(name = "houseHallNum", value = "户型厅数",required = true)
    private String houseHallNum;
    @ApiModelProperty(name = "houseBathNum", value = "户型卫数",required = true)
    private String houseBathNum;
    @ApiModelProperty(name = "houseArea", value = "房屋面积(平方米)")
    private Integer houseArea;
    @ApiModelProperty(name = "houseDirection", value = "朝向：南")
    private String houseDirection;
    @ApiModelProperty(name = "houseFloor", value = "楼层 房屋所在的层号")
    private Integer houseFloor;


}
