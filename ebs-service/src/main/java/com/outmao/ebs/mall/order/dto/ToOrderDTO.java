package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToOrderDTO {

    private Long settleId;

    private List<ToOrderShopDTO> shops;

    public ToOrderShopDTO getShopByShopId(Long shopId){
        if(shops==null){
            return null;
        }
        for(ToOrderShopDTO s:shops){
            if(s.getShopId().equals(shopId))
                return s;
        }
        return null;
    }

}
