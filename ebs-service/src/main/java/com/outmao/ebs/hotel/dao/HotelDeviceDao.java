package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDeviceDao extends JpaRepository<HotelDevice,Long> {

    public HotelDevice findByDeviceNo(String deviceNo);

}
