package com.outmao.ebs.hotel.domain;


import com.outmao.ebs.hotel.dto.GetHotelCustomerListDTO;
import com.outmao.ebs.hotel.dto.HotelCustomerDTO;
import com.outmao.ebs.hotel.entity.HotelCustomer;
import com.outmao.ebs.hotel.vo.HotelCustomerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelCustomerDomain {


    public HotelCustomer saveHotelCustomer(HotelCustomerDTO request);

    public HotelCustomer getHotelCustomerByHotelIdAndPhone(Long hotelId,String phone);

    public HotelCustomerVO getHotelCustomerVOById(Long id);

    public HotelCustomerVO getHotelCustomerVO(Long hotelId,Long userId);

    public Page<HotelCustomerVO> getHotelCustomerVOPage(GetHotelCustomerListDTO request, Pageable pageable);






}
