package com.outmao.ebs.common.services.amap.vo;


import lombok.Data;

import java.util.List;

@Data
public class GeocodeResult extends AmapResult {

    private List<Geocode> geocodes;

}
