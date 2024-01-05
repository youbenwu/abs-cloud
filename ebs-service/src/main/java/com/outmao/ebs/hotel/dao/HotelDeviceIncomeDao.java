package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface HotelDeviceIncomeDao extends JpaRepository<HotelDeviceIncome,Long> {


    public boolean existsByDeviceIdAndTypeAndCreateTime(Long deviceId, int type, Date createTime);


}
