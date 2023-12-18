package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.HotelDeviceLeaseOrderDomain;
import com.outmao.ebs.hotel.domain.HotelDeviceRenterDomain;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HotelDeviceLeaseServiceImpl extends BaseService implements HotelDeviceLeaseService {

    @Autowired
    private HotelDeviceLeaseOrderDomain hotelDeviceLeaseOrderDomain;

    @Autowired
    private HotelDeviceDomain hotelDeviceDomain;

    @Autowired
    private HotelDeviceRenterDomain hotelDeviceRenterDomain;


    @Transactional()
    @Override
    public HotelDeviceLeaseOrder createHotelDeviceLeaseOrder(CreateHotelDeviceLeaseOrderDTO request) {

        //保存租户信息
        HotelDeviceRenterLeaseDTO renterLeaseDTO=new HotelDeviceRenterLeaseDTO();
        BeanUtils.copyProperties(request,renterLeaseDTO);
        hotelDeviceRenterDomain.saveHotelDeviceRenterLease(renterLeaseDTO);

        //租赁设备
        HotelDeviceLeaseDTO leaseDTO=new HotelDeviceLeaseDTO();
        BeanUtils.copyProperties(request,leaseDTO);
        List<HotelDevice> devices=hotelDeviceDomain.lease(leaseDTO);

        //保存订单信息
        HotelDeviceLeaseOrderDTO orderDTO=new HotelDeviceLeaseOrderDTO();
        BeanUtils.copyProperties(request,orderDTO);
        orderDTO.setDevices(devices.stream().map(t->t.getId()).collect(Collectors.toList()));
        orderDTO.setQuantity((int)hotelDeviceDomain.getHotelDeviceCountByLeaseRenterId(request.getUserId()));
        HotelDeviceLeaseOrder order=hotelDeviceLeaseOrderDomain.saveHotelDeviceLeaseOrder(orderDTO);

        return order;
    }


    @Override
    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id) {
        return hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderVOById(id);
    }

    @Override
    public Page<HotelDeviceLeaseOrderVO> getHotelDeviceLeaseOrderVOPage(GetHotelDeviceLeaseOrderListDTO request, Pageable pageable) {
        return hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderVOPage(request,pageable);
    }

    @Override
    public Page<HotelDeviceLeaseOrderItemVO> getHotelDeviceLeaseOrderItemVOPage(GetHotelDeviceLeaseOrderItemListDTO request, Pageable pageable) {
        return hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderItemVOPage(request,pageable);
    }


    @Override
    public HotelDeviceRenter saveHotelDeviceRenter(HotelDeviceRenterDTO request) {
        return hotelDeviceRenterDomain.saveHotelDeviceRenter(request);
    }

    @Override
    public HotelDeviceRenter saveHotelDeviceRenterLease(HotelDeviceRenterLeaseDTO request) {
        return hotelDeviceRenterDomain.saveHotelDeviceRenterLease(request);
    }

    @Override
    public HotelDeviceRenter getHotelDeviceRenterByUserId(Long userId) {
        return hotelDeviceRenterDomain.getHotelDeviceRenterByUserId(userId);
    }

    @Override
    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId) {
        return hotelDeviceRenterDomain.getHotelDeviceRenterVOByUserId(userId);
    }

    @Override
    public Page<HotelDeviceRenterVO> getHotelDeviceRenterVOPage(GetHotelDeviceRenterListDTO request, Pageable pageable) {
        return hotelDeviceRenterDomain.getHotelDeviceRenterVOPage(request,pageable);
    }

    @Override
    public List<MinHotelDeviceRenterVO> getMinHotelDeviceRenterVOListByUserIdIn(Collection<Long> userIdIn) {

        List<MinHotelDeviceRenterVO> list=hotelDeviceRenterDomain.getMinHotelDeviceRenterVOListByUserIdIn(userIdIn);

        if(list.size()>0){
            Map<Long,MinHotelDeviceRenterVO> listMap=list.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));
            List<MinHotelDeviceLeaseOrderVO> vos=hotelDeviceLeaseOrderDomain.getMinHotelDeviceLeaseOrderVOListByUserIdIn(listMap.keySet());
            for (MinHotelDeviceLeaseOrderVO vo:vos){
                MinHotelDeviceRenterVO r=listMap.get(vo.getUserId());
                if(r!=null){
                    if(vo.getStartTime()!=null&&vo.getEndTime()!=null){
                        r.setStartTime(vo.getStartTime());
                        r.setEndTime(vo.getEndTime());
                    }
                }
            }
        }

        return list;
    }
}
