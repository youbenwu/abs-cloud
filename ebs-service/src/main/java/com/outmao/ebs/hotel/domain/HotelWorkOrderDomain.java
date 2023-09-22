package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelWorkOrderListDTO;
import com.outmao.ebs.hotel.dto.HotelWorkOrderDTO;
import com.outmao.ebs.hotel.dto.SetHotelWorkOrderStatusDTO;
import com.outmao.ebs.hotel.entity.HotelWorkOrder;
import com.outmao.ebs.hotel.vo.HotelWorkOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelWorkOrderDomain {


    public HotelWorkOrder saveHotelWorkOrder(HotelWorkOrderDTO request);


    public void deleteHotelWorkOrderById(Long id);


    public HotelWorkOrder setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request);


    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable);




}
