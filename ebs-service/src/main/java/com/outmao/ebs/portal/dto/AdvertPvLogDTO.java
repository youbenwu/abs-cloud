package com.outmao.ebs.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertPvLogDTO {

    private Long userId;

    private Long advertId;

    private double pvPrice;



}
