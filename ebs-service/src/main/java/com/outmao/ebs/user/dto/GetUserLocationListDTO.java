package com.outmao.ebs.user.dto;


import lombok.Data;

import java.util.Date;


@Data
public class GetUserLocationListDTO {

    private Long userId;

    private Date startTime;

    private Date endTime;

}
