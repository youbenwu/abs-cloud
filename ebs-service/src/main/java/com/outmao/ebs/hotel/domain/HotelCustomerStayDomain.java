package com.outmao.ebs.hotel.domain;


import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelCustomerStay;
import com.outmao.ebs.hotel.vo.HotelCustomerStayVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelCustomerStayDomain {




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
