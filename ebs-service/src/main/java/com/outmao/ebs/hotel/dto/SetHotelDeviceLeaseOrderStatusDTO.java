package com.outmao.ebs.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetHotelDeviceLeaseOrderStatusDTO {


    private Long id;

    private String orderNo;

    private int status;

    public SetHotelDeviceLeaseOrderStatusDTO(Long id,int status){
        this.id=id;
        this.status=status;
    }

    public SetHotelDeviceLeaseOrderStatusDTO(String orderNo,int status){
        this.orderNo=orderNo;
        this.status=status;
    }

}
