package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import lombok.Data;

@Data
public class GetHotelRoomListDTO extends BaseDTO {


    private Long hotelId;

    private String keyword;

    private Integer status;

}
