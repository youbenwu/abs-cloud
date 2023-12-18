package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.data.dto.GetPhotoListDTO;
import com.outmao.ebs.data.dto.PhotoDTO;
import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.service.PhotoService;
import com.outmao.ebs.data.vo.PhotoVO;
import com.outmao.ebs.hotel.domain.*;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.*;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.mall.merchant.dto.MerchantDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.UserCommission;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.org.dto.MemberDTO;
import com.outmao.ebs.org.entity.Member;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class HotelServiceImpl extends BaseService implements HotelService {

    @Autowired
    private HotelDomain hotelDomain;


    @Autowired
    private HotelRoomTypeDomain hotelRoomTypeDomain;

    @Autowired
    private HotelRoomDomain hotelRoomDomain;

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


    @Autowired
    private OrgService orgService;


    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PhotoService photoService;



    @Transactional()
    @Override
    public Hotel registerHotel(RegisterHotelDTO request) {

        if(request.getUserId()==null){
            findUserOrRegister(request);
        }

        Hotel hotel= hotelDomain.registerHotel(request);

        //给酒店创建组织
        if(hotel.getOrgId()==null){
            orgService.registerOrg(hotel);
        }

        if(hotel.getMerchantId()==null){
            findMerchantOrRegister(hotel);
        }

        return hotel;
    }

    @Override
    public Hotel saveHotel(HotelDTO request) {
        return hotelDomain.saveHotel(request);
    }


    private void findMerchantOrRegister(Hotel hotel){
        //给酒店创建商家
        Merchant merchant=merchantService.getMerchantByUserId(hotel.getUserId());
        if(merchant==null){
            MerchantDTO merchantDTO=new MerchantDTO();
            merchantDTO.setUserId(hotel.getUserId());
            merchantDTO.setName(hotel.getName());
            merchant=merchantService.saveMerchant(merchantDTO);
        }
        orgService.addOrgParent(merchant.getOrgId(),hotel.getOrgId());
        hotel.setMerchantId(merchant.getId());
        hotel.setShopId(merchant.getShopId());
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
    public Hotel getHotelByUserId(Long userId) {
        return hotelDomain.getHotelByUserId(userId);
    }

    @Override
    public Hotel setHotelStatus(SetHotelStatusDTO request) {
        return hotelDomain.setHotelStatus(request);
    }

    @Override
    public long getHotelCount() {
        return hotelDomain.getHotelCount();
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
    public QyHotelVO getQyHotelVOById(Long id) {
        return hotelDomain.getQyHotelVOById(id);
    }

    @Override
    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListDTO request, Pageable pageable) {
        return hotelDomain.getQyHotelVOPage(request,pageable);
    }

    @Override
    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListForDeployDeviceDTO request, Pageable pageable) {
        return hotelDomain.getQyHotelVOPage(request,pageable);
    }

    @Override
    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn) {
        return hotelDomain.getHotelVOListByOrgIdIn(orgIdIn);
    }

    @Override
    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime) {
        return hotelDomain.getStatsHotelCountVOListByDays(fromTime,toTime);
    }

    @Override
    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime) {
        return hotelDomain.getStatsHotelCountVOListByMonths(fromTime,toTime);
    }


    @Override
    public Photo saveHotelPhoto(HotelPhotoDTO request) {
        PhotoDTO dto=new PhotoDTO();
        dto.setTarget(new BindingItem(request.getHotelId(),"Hotel"));
        BeanUtils.copyProperties(request,dto);
        return photoService.savePhoto(dto);
    }

    @Override
    public void deleteHotelPhotoById(Long id) {
        photoService.deletePhotoById(id);
    }

    @Override
    public void deleteAllHotelPhotoByHotelId(Long hotelId) {
        photoService.deleteAllByTargetTypeAndTargetId("Hotel",hotelId);
    }

    @Override
    public Page<PhotoVO> getHotelPhotoVOPage(GetHotelPhotoListDTO request, Pageable pageable) {
        GetPhotoListDTO dto=new GetPhotoListDTO();
        dto.setTargetType("Hotel");
        dto.setTargetId(request.getHotelId());
        return photoService.getPhotoVOPage(dto,pageable);
    }

    @Override
    public HotelRoomType saveHotelRoomType(HotelRoomTypeDTO request) {
        return hotelRoomTypeDomain.saveHotelRoomType(request);
    }

    @Override
    public void deleteHotelRoomTypeById(Long id) {
        hotelRoomTypeDomain.deleteHotelRoomTypeById(id);
    }

    @Override
    public HotelRoomTypeVO getHotelRoomTypeVOById(Long id) {
        return hotelRoomTypeDomain.getHotelRoomTypeVOById(id);
    }

    @Override
    public Page<HotelRoomTypeVO> getHotelRoomTypeVOPage(GetHotelRoomTypeListDTO request, Pageable pageable) {
        return hotelRoomTypeDomain.getHotelRoomTypeVOPage(request,pageable);
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
    public HotelRoomVO getHotelRoomVO(Long hotelId, String roomNo) {
        return hotelRoomDomain.getHotelRoomVO(hotelId,roomNo);
    }



    @Override
    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable) {
        return hotelRoomDomain.getHotelRoomVOPage(request,pageable);
    }

    @Override
    public List<QyHotelRoomVO> getQyHotelRoomVOList(Long hotelId) {
        return hotelRoomDomain.getQyHotelRoomVOList(hotelId);
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
    public void deleteHotelCustomerById(Long id) {
        hotelCustomerDomain.deleteHotelCustomerById(id);
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
    public HotelCustomerVO getHotelCustomerVOByHotelIdAndPhone(Long hotelId, String phone) {
        return hotelCustomerDomain.getHotelCustomerVOByHotelIdAndPhone(hotelId,phone);
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



    /**
     *
     * 广告计算佣金
     *
     * **/
    // 秒 分 时 天 月 周几~
    // 0 * * * * 0-7  每个月的每天每时每分每秒周一到周七都会执行

    /**
     * 30 15 10 * * ? 每天10点15分30 执行
     *
     * 30 0/5 10,18 * * ? 每天10时18时每个五分钟执行
     * 0 15 10 ? * 1-6 每个月的周一到周六10.15分钟执行一次
     */

    //每分钟一次
    //@Scheduled(cron = "0 0/1 * * * *")
    //每小时一次
    //@Scheduled(cron = "0 0 0/1 * * *")
    public void commission(){

//        List<HotelDeviceOwner> owners=hotelDeviceOwnerDomain.getHotelDeviceOwnerList();
//
//        owners.forEach(owner->{
//            commission(owner);
//        });
//
//
//        List<MerchantPartner> partners= merchantPartnerService.getMerchantPartnerList();
//
//        partners.forEach(partner->{
//            commission(partner);
//        });


    }



//    //计算机主广告收益
//    private void commission(HotelDeviceOwner owner){
//        if(owner.getCommissionId()==null)
//            return;
//        UserCommission commission=userCommissionService.getUserCommissionById(owner.getCommissionId());
//        List<HotelDevice> devices=hotelDeviceDomain.getHotelDeviceListByOwnerId(owner.getUserId());
//        commission(commission,devices,0.35);
//
//    }
//
//    //计算推广员广告收益
//    private void commission(MerchantPartner partner){
//        if(partner.getCommissionId()==null)
//            return;
//        UserCommission commission=userCommissionService.getUserCommissionById(partner.getCommissionId());
//        List<HotelDevice> devices=hotelDeviceDomain.getHotelDeviceListByPartnerId(partner.getId());
//        commission(commission,devices,0.05);
//    }


    private void commission(UserCommission commission,List<HotelDevice> devices,double rate){

//        if(commission==null)
//            return;
//        if(devices.isEmpty())
//            return;
//
//        Date fromTime=commission.getStatsTime();
//        Date toTime=new Date();
//        if(fromTime==null){
//            fromTime=DateUtil.beforeDays(360);
//        }
//
//        StatsAdvertPvDTO dto=new StatsAdvertPvDTO();
//        dto.setUsers(devices.stream().map(t->t.getUserId()).collect(Collectors.toList()));
//        dto.setFromTime(fromTime);
//        dto.setToTime(toTime);
//        StatsAdvertPvVO vo=advertPvLogService.getStatsAdvertPvVO(dto);
//
//        System.out.println("统计广告收益：commissionId="+commission.getId()+"\n收益="+vo.getPvAmount()*rate);
//
//        if(vo.getPvAmount()==0)
//            return;
//
//
//        UserCommissionRecordDTO recordDTO=new UserCommissionRecordDTO();
//        recordDTO.setCommissionId(commission.getId());
//        recordDTO.setAmount(vo.getPvAmount()*rate);
//        recordDTO.setType(0);
//        recordDTO.setStatsTime(toTime);
//        userCommissionService.saveUserCommissionRecord(recordDTO);

    }



}
