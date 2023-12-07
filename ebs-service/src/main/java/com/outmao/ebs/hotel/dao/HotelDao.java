package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel,Long> {


    public Hotel findByUserId(Long userId);

    public Hotel findByAreaAndName(String area,String name);

}
