package com.outmao.ebs.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdvertPvVO {

    private Long id;

    private int status;

    private int type;

    private long pv;

    private Long totalPv;

}


