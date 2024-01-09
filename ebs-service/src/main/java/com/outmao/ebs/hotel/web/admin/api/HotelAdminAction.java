package com.outmao.ebs.hotel.web.admin.api;



import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.vo.PhotoVO;
import com.outmao.ebs.hotel.common.constant.HotelRoomStatus;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.service.AccountService;
import com.outmao.ebs.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AccessPermissionGroup(title="酒店管理",url="/hotel",name="",children = {

        @AccessPermissionParent(title = "酒店信息管理",url = "/hotel/hotel",name = "",children = {
                @AccessPermission(title = "保存酒店信息",url = "/hotel/hotel",name = "save"),
                @AccessPermission(title = "删除酒店信息",url = "/hotel/hotel",name = "delete"),
                @AccessPermission(title = "读取酒店信息",url = "/hotel/hotel",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店房型管理",url = "/hotel/room/type",name = "",children = {
                @AccessPermission(title = "保存酒店房型",url = "/hotel/room/type",name = "save"),
                @AccessPermission(title = "删除酒店房型",url = "/hotel/room/type",name = "delete"),
                @AccessPermission(title = "读取酒店房型",url = "/hotel/room/type",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店房间管理",url = "/hotel/room",name = "",children = {
                @AccessPermission(title = "保存酒店房间",url = "/hotel/room",name = "save"),
                @AccessPermission(title = "删除酒店房间",url = "/hotel/room",name = "delete"),
                @AccessPermission(title = "读取酒店房间",url = "/hotel/room",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店设备管理",url = "/hotel/device",name = "",children = {
                @AccessPermission(title = "保存酒店设备",url = "/hotel/device",name = "save"),
                @AccessPermission(title = "删除酒店设备",url = "/hotel/device",name = "delete"),
                @AccessPermission(title = "读取酒店设备",url = "/hotel/device",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店服务管理",url = "/hotel/workOrder",name = "",children = {
                @AccessPermission(title = "保存酒店服务",url = "/hotel/workOrder",name = "save"),
                @AccessPermission(title = "删除酒店服务",url = "/hotel/workOrder",name = "delete"),
                @AccessPermission(title = "读取酒店服务",url = "/hotel/workOrder",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店客户管理",url = "/hotel/customer",name = "",children = {
                @AccessPermission(title = "保存酒店客户",url = "/hotel/customer",name = "save"),
                @AccessPermission(title = "删除酒店客户",url = "/hotel/customer",name = "delete"),
                @AccessPermission(title = "读取酒店客户",url = "/hotel/customer",name = "read"),
        }),
        @AccessPermissionParent(title = "酒店入住管理",url = "/hotel/customer/stay",name = "",children = {
                @AccessPermission(title = "保存入住记录",url = "/hotel/customer/stay",name = "save"),
                @AccessPermission(title = "删除入住记录",url = "/hotel/customer/stay",name = "delete"),
                @AccessPermission(title = "读取入住记录",url = "/hotel/customer/stay",name = "read"),
        }),
        @AccessPermissionParent(title = "机主信息管理",url = "/hotel/device/owner",name = "",children = {
                @AccessPermission(title = "保存机主信息",url = "/hotel/device/owner",name = "save"),
                @AccessPermission(title = "删除机主信息",url = "/hotel/device/owner",name = "delete"),
                @AccessPermission(title = "读取机主信息",url = "/hotel/device/owner",name = "read"),
        }),

})


@Api(value = "account-hotel", tags = "后台-酒店")
@RestController
@RequestMapping("/api/admin/hotel")
public class HotelAdminAction {

	@Autowired
    private HotelService hotelService;

    @Autowired
	private AccountService accountService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "注册酒店", notes = "注册酒店")
    @PostMapping("/register")
    public void registerHotel(@RequestBody RegisterHotelDTO request) {
        hotelService.registerHotel(request);
    }

    @PreAuthorize("hasPermission('/hotel/hotel','save')")
    @ApiOperation(value = "修改酒店信息", notes = "修改酒店信息")
    @PostMapping("/save")
    public void saveHotel(@RequestBody HotelDTO request) {
         hotelService.saveHotel(request);
    }


    @PreAuthorize("hasPermission(null,'/hotel/hotel','save')")
    @ApiOperation(value = "设置酒店状态", notes = "设置酒店状态")
    @PostMapping("/setStatus")
    public void setHotelStatus(SetHotelStatusDTO request) {
         hotelService.setHotelStatus(request);
    }


    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/count")
    public long getHotelCount(){
        return hotelService.getHotelCount();
    }


    @PostAuthorize("hasPermission(returnObject.orgId,'/hotel/hotel','read')")
    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/get")
    public HotelVO getHotelVOById(Long id) {
        return hotelService.getHotelVOById(id);
    }


    @PreAuthorize("hasPermission(#orgId,'/hotel/hotel','read')")
    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/getByOrg")
    public HotelVO getHotelVOByOrgId(Long orgId) {
        return hotelService.getHotelVOByOrgId(orgId);
    }


    @PostAuthorize("hasPermission(null,'/hotel/hotel','read')")
    @ApiOperation(value = "获取酒店信息列表", notes = "获取酒店信息列表")
    @PostMapping("/page")
    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable) {
        return hotelService.getHotelVOPage(request,pageable);
    }

    @ApiOperation(value = "获取登陆用户管理的酒店信息列表", notes = "获取登陆用户管理的酒店信息列表")
    @PostMapping("/list")
    public List<HotelVO> getHotelVOList() {
        return hotelService.getHotelVOListByOrgIdIn(SecurityUtil.currentUser().getMembers().stream().map(t->t.getOrgId()).collect(Collectors.toList()));
    }


    @ApiOperation(value = "酒店增量统计按天", notes = "酒店增量统计按天")
    @PostMapping("/addStatsDay")
    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime) {
        return hotelService.getStatsHotelCountVOListByDays(fromTime,toTime);
    }

    @ApiOperation(value = "酒店增量统计按月", notes = "酒店增量统计按月")
    @PostMapping("/addStatsMonth")
    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime) {
        return hotelService.getStatsHotelCountVOListByMonths(fromTime,toTime);
    }

    @ApiOperation(value = "保存酒店相册", notes = "保存酒店相册")
    @PostMapping("/photo/save")
    public void saveHotelPhoto(HotelPhotoDTO request){
        hotelService.saveHotelPhoto(request);
    }

    @ApiOperation(value = "删除酒店相册", notes = "删除酒店相册")
    @PostMapping("/photo/delete")
    public void deleteHotelPhotoById(Long id){
        hotelService.deleteHotelPhotoById(id);
    }


    @ApiOperation(value = "删除酒店相册", notes = "删除酒店相册")
    @PostMapping("/photo/deleteAll")
    public void deleteAllHotelPhotoByHotelId(Long hotelId){
        hotelService.deleteAllHotelPhotoByHotelId(hotelId);
    }


    @ApiOperation(value = "获取酒店相册图片列表", notes = "获取酒店相册图片列表")
    @PostMapping("/photo/page")
    public Page<PhotoVO> getHotelPhotoVOPage(GetHotelPhotoListDTO request, Pageable pageable){
        return hotelService.getHotelPhotoVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/hotel/room/type','save')")
    @ApiOperation(value = "保存酒店房型", notes = "保存酒店房型")
    @PostMapping("/room/type/save")
    public void saveHotelRoomType(HotelRoomTypeDTO request) {
        hotelService.saveHotelRoomType(request);
    }


    @PreAuthorize("hasPermission('/hotel/room/type','delete')")
    @ApiOperation(value = "删除酒店房型", notes = "删除酒店房型")
    @PostMapping("/room/type/delete")
    public void deleteHotelRoomTypeById(Long id) {
        hotelService.deleteHotelRoomTypeById(id);
    }

    @PreAuthorize("hasPermission('/hotel/room/type','read')")
    @ApiOperation(value = "获取酒店房型", notes = "获取酒店房型")
    @PostMapping("/room/type/get")
    public HotelRoomTypeVO getHotelRoomTypeVOById(Long id) {
        return hotelService.getHotelRoomTypeVOById(id);
    }

    @PreAuthorize("hasPermission('/hotel/room/type','read')")
    @ApiOperation(value = "获取酒店房型", notes = "获取酒店房型")
    @PostMapping("/room/type/page")
    public Page<HotelRoomTypeVO> getHotelRoomTypeVOPage(GetHotelRoomTypeListDTO request, Pageable pageable) {
        return hotelService.getHotelRoomTypeVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/hotel/room','save')")
    @ApiOperation(value = "保存酒店房间", notes = "保存酒店房间")
    @PostMapping("/room/save")
    public void saveHotelRoom(HotelRoomDTO request){
        hotelService.saveHotelRoom(request);
    }

    @PreAuthorize("hasPermission('/hotel/room','delete')")
    @ApiOperation(value = "删除酒店房间", notes = "删除酒店房间")
    @PostMapping("/room/delete")
    public void deleteHotelRoomById(Long id) {
        hotelService.deleteHotelRoomById(id);
    }

    @PreAuthorize("hasPermission('/hotel/room','save')")
    @ApiOperation(value = "设置酒店房间状态", notes = "设置酒店房间状态")
    @PostMapping("/room/setStatus")
    public void setHotelRoomStatus(SetHotelRoomStatusDTO request){
        HotelRoomVO room=hotelService.getHotelRoomVOById(request.getId());
        if(room.getStatus()== HotelRoomStatus.Stay.getStatus()){
            throw new BusinessException("有客状态不能修改为维修");
        }
        if(request.getStatus()== HotelRoomStatus.Stay.getStatus()){
            throw new BusinessException("请在入住管理里设置状态");
        }

        hotelService.setHotelRoomStatus(request);
    }

    @PreAuthorize("hasPermission('/hotel/room','read')")
    @ApiOperation(value = "获取酒店房间", notes = "获取酒店房间")
    @PostMapping("/room/get")
    public HotelRoomVO getHotelRoomVOById(Long id){
        return hotelService.getHotelRoomVOById(id);
    }


    @PreAuthorize("hasPermission('/hotel/room','read')")
    @ApiOperation(value = "获取酒店房间", notes = "获取酒店房间")
    @PostMapping("/room/getByRoomNo")
    public HotelRoomVO getHotelRoomVO(Long hotelId,String roomNo){
        return hotelService.getHotelRoomVO(hotelId,roomNo);
    }



    @PreAuthorize("hasPermission('/hotel/room','read')")
    @ApiOperation(value = "获取酒店房间列表", notes = "获取酒店房间列表")
    @PostMapping("/room/page")
    public Page<HotelRoomVO> getHotelRoomVOPage(GetHotelRoomListDTO request, Pageable pageable){
        return  hotelService.getHotelRoomVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/hotel/workOrder','save')")
    @ApiOperation(value = "保存酒店服务", notes = "保存酒店服务")
    @PostMapping("/workOrder/save")
    public void saveHotelWorkOrder(HotelWorkOrderDTO request){
         hotelService.saveHotelWorkOrder(request);
    }


    @PreAuthorize("hasPermission('/hotel/workOrder','delete')")
    @ApiOperation(value = "删除酒店服务", notes = "删除酒店服务")
    @PostMapping("/workOrder/delete")
    public void deleteHotelWorkOrderById(Long id){
        hotelService.deleteHotelWorkOrderById(id);
    }


    @PreAuthorize("hasPermission('/hotel/workOrder','save')")
    @ApiOperation(value = "设置酒店服务状态", notes = "设置酒店服务状态")
    @PostMapping("/workOrder/setStatus")
    public void setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request){
        hotelService.setHotelWorkOrderStatus(request);
    }


    @PreAuthorize("hasPermission('/hotel/workOrder','read')")
    @ApiOperation(value = "获取酒店服务列表", notes = "获取酒店服务列表")
    @PostMapping("/workOrder/page")
    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable){
        return hotelService.getHotelWorkOrderVOPage(request,pageable);
    }


    @PreAuthorize("hasPermission('/hotel/customer','save')")
    @ApiOperation(value = "保存酒店客户", notes = "保存酒店客户v")
    @PostMapping("/customer/save")
    public void saveHotelCustomer(HotelCustomerDTO request){
         hotelService.saveHotelCustomer(request);
    }


    @PreAuthorize("hasPermission('/hotel/customer','delete')")
    @ApiOperation(value = "删除酒店客户", notes = "删除酒店客户")
    @PostMapping("/customer/delete")
    public void deleteHotelCustomerById(Long id) {
        hotelService.deleteHotelCustomerById(id);
    }

    @PreAuthorize("hasPermission('/hotel/customer','read')")
    @ApiOperation(value = "获取酒店客户", notes = "获取酒店客户")
    @PostMapping("/customer/getByPhone")
    public HotelCustomerVO getHotelCustomerVOByHotelIdAndPhone(Long hotelId, String phone) {
        return hotelService.getHotelCustomerVOByHotelIdAndPhone(hotelId,phone);
    }

    @PreAuthorize("hasPermission('/hotel/customer','read')")
    @ApiOperation(value = "获取酒店客户列表", notes = "获取酒店客户列表")
    @PostMapping("/customer/page")
    public Page<HotelCustomerVO> getHotelCustomerVOPage(GetHotelCustomerListDTO request, Pageable pageable){
        return hotelService.getHotelCustomerVOPage(request,pageable);
    }

    /***
     *
     * 登记入住
     *
     */
    @PreAuthorize("hasPermission('/hotel/customer/stay','save')")
    @ApiOperation(value = "登记入住", notes = "登记入住")
    @PostMapping("/customer/stay/save")
    public void saveHotelCustomerStay(HotelCustomerStayDTO request){
         hotelService.saveHotelCustomerStay(request);
    }

    /***
     *
     * 设置入住状态
     *
     */
    @PreAuthorize("hasPermission('/hotel/customer/stay','save')")
    @ApiOperation(value = "设置入住状态", notes = "设置入住状态")
    @PostMapping("/customer/stay/setStatus")
    public void setHotelCustomerStayStatus(SetHotelCustomerStayStatusDTO request){
         hotelService.setHotelCustomerStayStatus(request);
    }

    @PreAuthorize("hasPermission('/hotel/customer/stay','read')")
    @ApiOperation(value = "获取入住列表", notes = "获取入住列表")
    @PostMapping("/customer/stay/page")
    public Page<HotelCustomerStayVO> getHotelCustomerStayVOPage(GetHotelCustomerStayListDTO request,Pageable pageable){
        return hotelService.getHotelCustomerStayVOPage(request,pageable);
    }





}
