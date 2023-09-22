package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.domain.*;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.*;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.org.common.annotation.BindingOrg;
import com.outmao.ebs.org.dto.MemberDTO;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;


@Service
public class HotelServiceImpl extends BaseService implements HotelService {

    @Autowired
    private HotelDomain hotelDomain;


    @Autowired
    private HotelRoomDomain hotelRoomDomain;


    @Autowired
    private HotelDeviceDomain hotelDeviceDomain;


    @Autowired
    private HotelWorkOrderDomain hotelWorkOrderDomain;

    @Autowired
    private HotelCustomerDomain hotelCustomerDomain;

    @Autowired
    private HotelCustomerStayDomain hotelCustomerStayDomain;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;



    @Transactional()
    @BindingOrg
    @Override
    public Hotel registerHotel(RegisterHotelDTO request) {
        if(request.getUserId()==null){
            findUserOrRegister(request);
        }
        return hotelDomain.registerHotel(request);
    }

    @Override
    public Hotel saveHotel(HotelDTO request) {
        return hotelDomain.saveHotel(request);
    }


    private void findUserOrRegister(RegisterHotelDTO request){
        Assert.notNull(request.getContact(),"联系人不能为空");
        Assert.notNull(request.getContact().getPhone(),"联系人电话不能为空");
        Assert.notNull(request.getContact().getName(),"联系人姓名不能为空");


        User user=userService.getUserByUsername(request.getContact().getPhone());

        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getContact().getPhone());
            registerDTO.setCredentials(request.getPassword());
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",request.getContact().getName());

            user=userService.registerUser(registerDTO);
        }

        request.setUserId(user.getId());

    }

    @Override
    public Hotel setHotelStatus(SetHotelStatusDTO request) {
        return hotelDomain.setHotelStatus(request);
    }

    @Override
    public HotelVO getHotelVOById(Long id) {
        return hotelDomain.getHotelVOById(id);
    }

    @Override
    public HotelVO getHotelVOByOrgId(Long orgId) {
        return hotelDomain.getHotelVOByOrgId(orgId);
    }

    @Override
    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable) {
        return hotelDomain.getHotelVOPage(request,pageable);
    }

    @Override
    public HotelRoom saveHotelRoom(HotelRoomDTO request) {
        return hotelRoomDomain.saveHotelRoom(request);
    }


    @Override
    public void deleteHotelRoomById(Long id) {
        hotelRoomDomain.deleteHotelRoomById(id);
    }

    @Override
    public HotelRoom setHotelRoomStatus(SetHotelRoomStatusDTO request) {
        return hotelRoomDomain.setHotelRoomStatus(request);
    }

    @Override
    public HotelRoomVO getHotelRoomVOById(Long id) {
        return hotelRoomDomain.getHotelRoomVOById(id);
    }

    @Override
    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable) {
        return hotelRoomDomain.getHotelRoomVOPage(request,pageable);
    }

    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(HotelDeviceDTO request) {

        HotelDevice device= hotelDeviceDomain.saveHotelDevice(request);

        if(request.getId()==null){
           findOrRegisterDeviceUser(device);
        }


        return device;
    }


    private void findOrRegisterDeviceUser(HotelDevice request){
        User user=userService.getUserByUsername(request.getDeviceNo());
        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getDeviceNo());
            registerDTO.setCredentials("123456");
            registerDTO.setOauth(Oauth.USERNAME.getName());
            registerDTO.setType(2);
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",request.getName());

            user=userService.registerUser(registerDTO);

            MemberDTO memberDTO=new MemberDTO();
            memberDTO.setUserId(user.getId());
            memberDTO.setOrgId(request.getOrgId());
            memberDTO.setName(user.getNickname());

            memberService.saveMember(memberDTO);
        }

        request.setUserId(user.getId());


    }


    @Override
    public void deleteHotelDeviceById(Long id) {
         hotelDeviceDomain.deleteHotelDeviceById(id);
    }

    @Override
    public HotelDeviceVO getHotelDeviceVOById(Long id) {
        return hotelDeviceDomain.getHotelDeviceVOById(id);
    }

    @Override
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo) {
        return hotelDeviceDomain.getHotelDeviceVOByDeviceNo(deviceNo);
    }

    @Override
    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable) {
        return hotelDeviceDomain.getHotelDeviceVOPage(request,pageable);
    }


    @Override
    public HotelWorkOrder saveHotelWorkOrder(HotelWorkOrderDTO request) {
        return hotelWorkOrderDomain.saveHotelWorkOrder(request);
    }

    @Override
    public void deleteHotelWorkOrderById(Long id) {
        hotelWorkOrderDomain.deleteHotelWorkOrderById(id);
    }

    @Override
    public HotelWorkOrder setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request) {
        return hotelWorkOrderDomain.setHotelWorkOrderStatus(request);
    }

    @Override
    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable) {
        return hotelWorkOrderDomain.getHotelWorkOrderVOPage(request,pageable);
    }


    @Transactional()
    @Override
    public HotelCustomer saveHotelCustomer(HotelCustomerDTO request) {
        if(request.getId()==null){
            if(request.getUserId()==null) {
                findOrRegisterCustomerUser(request);
            }
        }
        HotelCustomer customer= hotelCustomerDomain.saveHotelCustomer(request);

        if(request.getId()==null){
            findOrRegisterCustomerMember(customer);
        }

        return customer;

    }

    private void findOrRegisterCustomerMember(HotelCustomer customer){
        Member member=memberService.getMember(customer.getOrgId(),customer.getUserId());
        if(member==null){
            MemberDTO memberDTO=new MemberDTO();
            memberDTO.setUserId(customer.getUserId());
            memberDTO.setOrgId(customer.getOrgId());
            memberDTO.setName(customer.getName());
            memberDTO.setPhone(customer.getPhone());
            memberService.saveMember(memberDTO);
        }
    }

    private void findOrRegisterCustomerUser(HotelCustomerDTO request){
        User user=userService.getUserByUsername(request.getPhone());
        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getPhone());
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",request.getName());
            user=userService.registerUser(registerDTO);
        }
        request.setUserId(user.getId());
    }




    @Override
    public HotelCustomerVO getHotelCustomerVOById(Long id) {
        return hotelCustomerDomain.getHotelCustomerVOById(id);
    }

    @Override
    public HotelCustomerVO getHotelCustomerVO(Long hotelId, Long userId) {
        return hotelCustomerDomain.getHotelCustomerVO(hotelId,userId);
    }

    @Override
    public Page<HotelCustomerVO> getHotelCustomerVOPage(GetHotelCustomerListDTO request, Pageable pageable) {
        return hotelCustomerDomain.getHotelCustomerVOPage(request,pageable);
    }

    @Transactional()
    @Override
    public HotelCustomerStay saveHotelCustomerStay(HotelCustomerStayDTO request) {
        if(request.getCustomerId()==null){
            findOrRegisterCustomerUser(request);
        }
        return hotelCustomerStayDomain.saveHotelCustomerStay(request);
    }

    private void findOrRegisterCustomerUser(HotelCustomerStayDTO request){
        HotelCustomer customer=hotelCustomerDomain.getHotelCustomerByHotelIdAndPhone(request.getHotelId(),request.getPhone());
        if(customer==null){
            HotelCustomerDTO customerDTO=new HotelCustomerDTO();
            customerDTO.setName(request.getName());
            customerDTO.setPhone(request.getPhone());
            customerDTO.setHotelId(request.getHotelId());
            customer= saveHotelCustomer(customerDTO);
        }
        request.setCustomerId(customer.getId());
    }

    @Override
    public HotelCustomerStay setHotelCustomerStayStatus(SetHotelCustomerStayStatusDTO request) {
        return hotelCustomerStayDomain.setHotelCustomerStayStatus(request);
    }

    @Override
    public Page<HotelCustomerStayVO> getHotelCustomerStayVOPage(GetHotelCustomerStayListDTO request, Pageable pageable) {
        return hotelCustomerStayDomain.getHotelCustomerStayVOPage(request,pageable);
    }
}
