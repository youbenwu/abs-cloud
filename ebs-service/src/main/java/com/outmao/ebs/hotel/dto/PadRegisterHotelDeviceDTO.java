package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.Address;
import com.outmao.ebs.common.vo.BaseDTO;
import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "PadRegisterHotelDeviceDTO", description = "激活设备信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PadRegisterHotelDeviceDTO extends BaseDTO {


    @ApiModelProperty(name = "userId", value = "登陆用户ID")
    private Long userId;

    @ApiModelProperty(name = "hotelName", value = "酒店名称")
    private String hotelName;

    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;


    /**
     * 设备号
     */
    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;


    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "name", value = "维护人姓名")
    private String name;

    @ApiModelProperty(name = "phone", value = "电话号码")
    private String phone;


    @ApiModelProperty(name = "address", value = "酒店信息")
    private Address address;

}
