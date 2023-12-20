package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.hotel.common.annotation.SetSimpleHotel;
import com.outmao.ebs.hotel.common.constant.HotelRoomDeviceStatus;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import com.outmao.ebs.org.dto.MemberDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.common.constant.UserType;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class HotelDeviceServiceImpl extends BaseService implements HotelDeviceService {

    @Autowired
    private HotelDeviceDomain hotelDeviceDomain;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;

    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(HotelDeviceDTO request) {

        HotelDevice device= hotelDeviceDomain.saveHotelDevice(request);

        if(device.getUserId()==null){
            findOrRegisterDeviceUser(device);
        }

        //设置房间投放设备状态
        hotelService.setHotelRoomDeviceStatus(
                new SetHotelRoomDeviceStatusDTO(
                        device.getHotelId(),
                        device.getRoomNo(),
                        HotelRoomDeviceStatus.HasDevice.getStatus())
        );


        hotelDeviceLeaseService.hotelDeviceActive(device.getId());


        return device;
    }

    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(PadRegisterHotelDeviceDTO request) {
        Hotel hotel=hotelService.getHotelByUserId(request.getUserId());
        if(hotel==null){
            RegisterHotelDTO hotelDTO=new RegisterHotelDTO();
            hotelDTO.setUserId(request.getUserId());
            hotelDTO.setName(request.getHotelName());
            Contact contact=new Contact();
            contact.setName(request.getName());
            contact.setPhone(request.getPhone());
            contact.setAddress(request.getAddress());
            hotelDTO.setContact(contact);
            hotel=hotelService.registerHotel(hotelDTO);
        }
        HotelDeviceDTO deviceDTO=new HotelDeviceDTO();
        deviceDTO.setDeviceNo(request.getDeviceNo());
        deviceDTO.setRoomNo(request.getRoomNo());
        deviceDTO.setHotelId(hotel.getId());
        return saveHotelDevice(deviceDTO);
    }


    private void findOrRegisterDeviceUser(HotelDevice request){
        User user=userService.getUserByUsername(request.getDeviceNo());
        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getDeviceNo());
            registerDTO.setCredentials("123456");
            registerDTO.setOauth(Oauth.USERNAME.getName());
            registerDTO.setType(UserType.QyHotelDevice.getType());
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

    @Transactional()
    @Override
    public void deleteHotelDeviceById(Long id) {
        HotelDevice device=hotelDeviceDomain.getHotelDeviceById(id);
        //设置房间投放设备状态
        if(device.getStatus()!=0&&hotelService.existsHotelRoomByHotelIdAndRoomNo(device.getHotelId(),device.getRoomNo())) {
            hotelService.setHotelRoomDeviceStatus(
                    new SetHotelRoomDeviceStatusDTO(
                            device.getHotelId(),
                            device.getRoomNo(),
                            HotelRoomDeviceStatus.NoDevice.getStatus())
            );
        }
        //删除设备
        hotelDeviceDomain.deleteHotelDeviceById(id);
    }


    @Override
    public long getHotelDeviceCount() {
        return hotelDeviceDomain.getHotelDeviceCount();
    }


    @Override
    public long getHotelDeviceCountByPartnerId(Long partnerId) {
        return hotelDeviceDomain.getHotelDeviceCountByPartnerId(partnerId);
    }

    @Override
    public long getHotelDeviceCountByLeaseRenterId(Long leaseRenterId) {
        return hotelDeviceDomain.getHotelDeviceCountByLeaseRenterId(leaseRenterId);
    }

    @Override
    public HotelDevice getHotelDeviceByUserId(Long userId) {
        return hotelDeviceDomain.getHotelDeviceByUserId(userId);
    }

    @SetSimpleHotel
    @Override
    public HotelDeviceVO getHotelDeviceVOById(Long id) {
        return hotelDeviceDomain.getHotelDeviceVOById(id);
    }

    @SetSimpleHotel
    @Override
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo) {
        return hotelDeviceDomain.getHotelDeviceVOByDeviceNo(deviceNo);
    }

    @Override
    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request) {
        return hotelDeviceDomain.getHotelDeviceVOList(request);
    }

    @SetSimpleHotel
    @Override
    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable) {
        return hotelDeviceDomain.getHotelDeviceVOPage(request,pageable);
    }

    @Override
    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size) {
        return hotelDeviceDomain.getStatsHotelDeviceCityVOList(size);
    }

    @Override
    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size) {
        return hotelDeviceDomain.getStatsHotelDeviceProvinceVOList(size);
    }

    @Override
    public List<HotelDevice> buy(HotelDeviceBuyDTO request) {
        return hotelDeviceDomain.buy(request);
    }

    @Override
    public List<HotelDevice> lease(HotelDeviceLeaseDTO request) {
        return hotelDeviceDomain.lease(request);
    }

    @Transactional()
    @Override
    public List<HotelDevice> deploy(HotelDeviceDeployDTO request) {

        return hotelDeviceDomain.deploy(request);
    }


}


