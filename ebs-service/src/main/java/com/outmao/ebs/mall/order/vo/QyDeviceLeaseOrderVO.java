package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "QyDeviceLeaseOrderVO", description = "迁眼设备租赁订单信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class QyDeviceLeaseOrderVO extends OrderVO {

    private HotelDeviceLeaseOrderVO leaseInfo;


}
