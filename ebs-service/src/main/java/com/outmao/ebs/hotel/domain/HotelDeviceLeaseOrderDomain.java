package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderItemListDTO;
import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceLeaseOrderDTO;
import com.outmao.ebs.hotel.dto.SetHotelDeviceLeaseOrderStatusDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrderItem;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderItemVO;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceLeaseOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HotelDeviceLeaseOrderDomain {

    public HotelDeviceLeaseOrder hotelDeviceActive(Long deviceId);

    public HotelDeviceLeaseOrder saveHotelDeviceLeaseOrder(HotelDeviceLeaseOrderDTO request);

    public HotelDeviceLeaseOrder setHotelDeviceLeaseOrderStatus(SetHotelDeviceLeaseOrderStatusDTO request);

    public HotelDeviceLeaseOrder getHotelDeviceLeaseOrderByOrderNo(String orderNo);

    public List<HotelDeviceLeaseOrderItem> getHotelDeviceLeaseOrderItemListByOrderId(Long orderId);

    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id);

    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOByOrderNo(String orderNo);

    public Page<HotelDeviceLeaseOrderVO> getHotelDeviceLeaseOrderVOPage(GetHotelDeviceLeaseOrderListDTO request, Pageable pageable);

    public Page<HotelDeviceLeaseOrderItemVO> getHotelDeviceLeaseOrderItemVOPage(GetHotelDeviceLeaseOrderItemListDTO request,Pageable pageable);

    public List<MinHotelDeviceLeaseOrderVO> getMinHotelDeviceLeaseOrderVOListByUserIdIn(Collection<Long> userIdIn);

}
