package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.portal.vo.AdvertVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "QyAdvertOrderVO", description = "迁眼投放广告订单信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class QyAdvertOrderVO extends OrderVO {

    @ApiModelProperty(name = "advert", value = "广告信息")
    private AdvertVO advert;


}
