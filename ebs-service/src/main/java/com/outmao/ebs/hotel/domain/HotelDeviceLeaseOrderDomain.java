package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderItemListDTO;
import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceLeaseOrderDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderItemVO;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelDeviceLeaseOrderDomain {

    public HotelDeviceLeaseOrder saveHotelDeviceLeaseOrder(HotelDeviceLeaseOrderDTO request);

    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id);

    public Page<HotelDeviceLeaseOrderVO> getHotelDeviceLeaseOrderVOPage(GetHotelDeviceLeaseOrderListDTO request, Pageable pageable);

    public Page<HotelDeviceLeaseOrderItemVO> getHotelDeviceLeaseOrderItemVOPage(GetHotelDeviceLeaseOrderItemListDTO request,Pageable pageable);


}
