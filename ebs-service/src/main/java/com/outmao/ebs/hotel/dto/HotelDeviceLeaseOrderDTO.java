package com.outmao.ebs.hotel.dto;

import lombok.Data;

import java.util.List;


@Data
public class HotelDeviceLeaseOrderDTO extends CreateHotelDeviceLeaseOrderDTO {


    /**
     *
     * 租赁设备ID列表
     *
     */
    private List<Long> devices;


}
