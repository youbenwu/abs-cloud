package com.outmao.ebs.portal.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StatsAdvertPvDTO {

    private List<Long> users;

    private Date fromTime;

    private Date toTime;

}
