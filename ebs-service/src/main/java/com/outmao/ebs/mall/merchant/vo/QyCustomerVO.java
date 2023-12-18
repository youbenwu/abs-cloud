package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel(value = "QyCustomerVO", description = "迁眼客户信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class QyCustomerVO extends MerchantCustomerVO{


    private MinHotelDeviceRenterVO renter;


}
