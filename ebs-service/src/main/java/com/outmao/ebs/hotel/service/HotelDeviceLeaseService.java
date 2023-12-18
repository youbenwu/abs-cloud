package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderItemVO;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HotelDeviceLeaseService {


    public HotelDeviceLeaseOrder createHotelDeviceLeaseOrder(CreateHotelDeviceLeaseOrderDTO request);

    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id);

    public Page<HotelDeviceLeaseOrderVO> getHotelDeviceLeaseOrderVOPage(GetHotelDeviceLeaseOrderListDTO request, Pageable pageable);

    public Page<HotelDeviceLeaseOrderItemVO> getHotelDeviceLeaseOrderItemVOPage(GetHotelDeviceLeaseOrderItemListDTO request, Pageable pageable);


    public HotelDeviceRenter saveHotelDeviceRenter(HotelDeviceRenterDTO request);

    public HotelDeviceRenter saveHotelDeviceRenterLease(HotelDeviceRenterLeaseDTO request);

    public HotelDeviceRenter getHotelDeviceRenterByUserId(Long userId);

    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId);

    public Page<HotelDeviceRenterVO> getHotelDeviceRenterVOPage(GetHotelDeviceRenterListDTO request, Pageable pageable);

    public List<MinHotelDeviceRenterVO> getMinHotelDeviceRenterVOListByUserIdIn(Collection<Long> userIdIn);


}
