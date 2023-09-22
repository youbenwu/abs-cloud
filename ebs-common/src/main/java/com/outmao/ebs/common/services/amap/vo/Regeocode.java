package com.outmao.ebs.common.services.amap.vo;

import lombok.Data;

@Data
public class Regeocode {

    private String formatted_address;

    private AddressComponent addressComponent;

}
