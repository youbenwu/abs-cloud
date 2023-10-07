package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.*;
import com.outmao.ebs.hotel.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HotelService {

    public Hotel registerHotel(RegisterHotelDTO request);

    public Hotel saveHotel(HotelDTO request);

    public Hotel setHotelStatus(SetHotelStatusDTO request);

    public HotelVO getHotelVOById(Long id);

    public HotelVO getHotelVOByOrgId(Long orgId);

    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable);

    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn);


    public HotelRoom saveHotelRoom(HotelRoomDTO request);

    public void deleteHotelRoomById(Long id);

    public HotelRoom setHotelRoomStatus(SetHotelRoomStatusDTO request);

    public HotelRoomVO getHotelRoomVOById(Long id);

    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable);


    public HotelDevice saveHotelDevice(HotelDeviceDTO request);

    public void deleteHotelDeviceById(Long id);

    public HotelDeviceVO getHotelDeviceVOById(Long id);

    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo);

    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable);


    public HotelWorkOrder saveHotelWorkOrder(HotelWorkOrderDTO request);


    public void deleteHotelWorkOrderById(Long id);


    public HotelWorkOrder setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request);


    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable);



    public HotelCustomer saveHotelCustomer(HotelCustomerDTO request);

    public HotelCustomerVO getHotelCustomerVOById(Long id);

    public HotelCustomerVO getHotelCustomerVO(Long hotelId,Long userId);

    public Page<HotelCustomerVO> getHotelCustomerVOPage(GetHotelCustomerListDTO request, Pageable pageable);


    /***
     *
     * 登记入住
     *
     */
    public HotelCustomerStay saveHotelCustomerStay(HotelCustomerStayDTO request);

    /***
     *
     * 设置入住状态
     *
     */
    public HotelCustomerStay setHotelCustomerStayStatus(SetHotelCustomerStayStatusDTO request);


    public Page<HotelCustomerStayVO> getHotelCustomerStayVOPage(GetHotelCustomerStayListDTO request,Pageable pageable);


}
