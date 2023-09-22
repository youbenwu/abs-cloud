package com.outmao.ebs.common.services.amap.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressComponent {

    private String country;

    private String province;

    private Object city;

    @JsonProperty("citycode")
    private Object cityCode;

    private Object district;

    private Object township;

    @JsonProperty("towncode")
    private Object townCode;



}
