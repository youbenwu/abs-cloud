package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelRoom;
import com.outmao.ebs.hotel.vo.HotelRoomVO;
import com.outmao.ebs.hotel.vo.QyHotelRoomVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface HotelRoomDomain {


    public HotelRoom saveHotelRoom(HotelRoomDTO request);

    public HotelRoom getHotelRoom(Long hotelId,String roomNo);

    public boolean existsByHotelIdAndRoomNo(Long hotelId,String roomNo);

    public void deleteHotelRoomById(Long id);

    public HotelRoom setHotelRoomStatus(SetHotelRoomStatusDTO request);

    public HotelRoomVO getHotelRoomVOById(Long id);

    public HotelRoomVO getHotelRoomVO(Long hotelId,String roomNo);

    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable);


    public List<QyHotelRoomVO> getQyHotelRoomVOList(Long hotelId);


    //托管设备
    public HotelRoom setHotelRoomDeviceStatus(SetHotelRoomDeviceStatusDTO request);
    public void deviceDeploy(List<HotelRoomDeviceDeployDTO> request);



}
