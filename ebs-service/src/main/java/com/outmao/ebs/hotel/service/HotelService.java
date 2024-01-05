package com.outmao.ebs.hotel.service;

import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.vo.PhotoVO;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.*;
import com.outmao.ebs.hotel.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface HotelService {

    public Hotel registerHotel(RegisterHotelDTO request);

    public Hotel saveHotel(HotelDTO request);

    public Hotel getHotelByUserId(Long userId);

    public Hotel setHotelStatus(SetHotelStatusDTO request);

    public long getHotelCount();

    public HotelVO getHotelVOById(Long id);


    public HotelVO getHotelVOByOrgId(Long orgId);

    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable);



    public QyHotelVO getQyHotelVOById(Long id);

    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListDTO request, Pageable pageable);

    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListForDeployDeviceDTO request, Pageable pageable);

    public List<HotelVO> getHotelVOListByIdIn(Collection<Long> idIn);

    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn);


    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime);

    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime);


    public Photo saveHotelPhoto(HotelPhotoDTO request);

    public void deleteHotelPhotoById(Long id);

    public void deleteAllHotelPhotoByHotelId(Long hotelId);

    public Page<PhotoVO> getHotelPhotoVOPage(GetHotelPhotoListDTO request,Pageable pageable);

    public HotelRoomType saveHotelRoomType(HotelRoomTypeDTO request);

    public void deleteHotelRoomTypeById(Long id);

    public HotelRoomTypeVO getHotelRoomTypeVOById(Long id);

    public Page<HotelRoomTypeVO> getHotelRoomTypeVOPage(GetHotelRoomTypeListDTO request, Pageable pageable);


    public HotelRoom saveHotelRoom(HotelRoomDTO request);

    public void deleteHotelRoomById(Long id);

    public boolean existsHotelRoomByHotelIdAndRoomNo(Long hotelId,String roomNo);

    public HotelRoom setHotelRoomStatus(SetHotelRoomStatusDTO request);

    public HotelRoom setHotelRoomDeviceStatus(SetHotelRoomDeviceStatusDTO request);

    public HotelRoomVO getHotelRoomVOById(Long id);

    public HotelRoomVO getHotelRoomVO(Long hotelId,String roomNo);

    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable);

    public List<QyHotelRoomVO> getQyHotelRoomVOList(Long hotelId);

    public HotelWorkOrder saveHotelWorkOrder(HotelWorkOrderDTO request);


    public void deleteHotelWorkOrderById(Long id);


    public HotelWorkOrder setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request);


    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable);



    public HotelCustomer saveHotelCustomer(HotelCustomerDTO request);

    public void deleteHotelCustomerById(Long id);

    public HotelCustomerVO getHotelCustomerVOById(Long id);

    public HotelCustomerVO getHotelCustomerVO(Long hotelId,Long userId);

    public HotelCustomerVO getHotelCustomerVOByHotelIdAndPhone(Long hotelId,String phone);

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


    //托管设备
    public void deviceDeploy(List<HotelRoomDeviceDeployDTO> request);


    public HotelServiceStaffVO getHotelServiceStaffVOByHotelId(Long hotelId);



}
