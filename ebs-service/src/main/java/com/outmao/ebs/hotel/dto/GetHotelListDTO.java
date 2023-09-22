package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import lombok.Data;

@Data
public class GetHotelListDTO extends BaseDTO {

    private String keyword;

    private Integer status;

}
