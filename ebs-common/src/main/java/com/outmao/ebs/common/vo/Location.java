package com.outmao.ebs.common.vo;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Location {

    private Double latitude;// 纬度
    private Double longitude;

}
