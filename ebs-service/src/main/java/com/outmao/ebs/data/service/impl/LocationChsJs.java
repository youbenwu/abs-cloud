package com.outmao.ebs.data.service.impl;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://4.url.cn", name = "location-chs-js")
public interface LocationChsJs {

    @RequestMapping(value="/zc/chs/js/10062/location_chs.js",method= RequestMethod.GET)
    public String get();

}
