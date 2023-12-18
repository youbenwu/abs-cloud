package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.data.vo.PhotoVO;
import com.outmao.ebs.hotel.dto.GetHotelListDTO;
import com.outmao.ebs.hotel.dto.GetHotelListForDeployDeviceDTO;
import com.outmao.ebs.hotel.dto.GetHotelPhotoListDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Api(value = "hotel", tags = "酒店")
@RestController
@RequestMapping("/api/hotel")
public class HotelAction {

	@Autowired
    private HotelService hotelService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "注册酒店", notes = "注册酒店")
    @PostMapping("/register")
    public void registerHotel(@RequestBody RegisterHotelDTO request) {
        hotelService.registerHotel(request);
        try{
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/get")
    public HotelVO getHotelVOById(Long id) {
        return hotelService.getHotelVOById(id);
    }


    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/getByOrg")
    public HotelVO getHotelVOByOrgId(Long orgId) {
        return hotelService.getHotelVOByOrgId(orgId);
    }


    @ApiOperation(value = "管理员获取酒店信息列表", notes = "管理员获取酒店信息列表")
    @PostMapping("/list")
    public List<HotelVO> getHotelVOList() {
        return hotelService.getHotelVOListByOrgIdIn(SecurityUtil.currentUser().getMembers().stream().map(t->t.getOrgId()).collect(Collectors.toList()));
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "查找酒店列表", notes = "查找酒店列表")
    @PostMapping("/page")
    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable) {
        request.setStatus(0);
        return hotelService.getHotelVOPage(request,pageable);
    }

    @ApiOperation(value = "获取酒店相册图片列表", notes = "获取酒店相册图片列表")
    @PostMapping("/photo/page")
    public Page<PhotoVO> getHotelPhotoVOPage(GetHotelPhotoListDTO request, Pageable pageable){
        return hotelService.getHotelPhotoVOPage(request,pageable);
    }


    @ApiOperation(value = "迁眼--获取酒店信息", notes = "迁眼--获取酒店信息")
    @PostMapping("/qy/get")
    public QyHotelVO getQyHotelVOById(Long id){
        return hotelService.getQyHotelVOById(id);
    }


    @ApiOperation(value = "迁眼--获取酒店列表", notes = "迁眼--获取酒店列表")
    @PostMapping("/qy/page")
    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListDTO request, Pageable pageable){
        return hotelService.getQyHotelVOPage(request,pageable);
    }


    @ApiOperation(value = "迁眼设备托管--获取酒店列表", notes = "迁眼设备托管--获取酒店列表")
    @PostMapping("/qy_deploy_device/page")
    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListForDeployDeviceDTO request, Pageable pageable){
        return hotelService.getQyHotelVOPage(request,pageable);
    }

    @ApiOperation(value = "迁眼--获取房间列表", notes = "迁眼--获取房间列表")
    @PostMapping("/room/qy/list")
    public List<QyHotelRoomVO> getQyHotelRoomVOList(Long hotelId){
        return hotelService.getQyHotelRoomVOList(hotelId);
    }





}
