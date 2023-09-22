package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel,Long> {



}
