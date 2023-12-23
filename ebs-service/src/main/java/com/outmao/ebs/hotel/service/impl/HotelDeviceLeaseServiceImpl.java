package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.common.constant.HotelDeviceLeaseOrderStatus;
import com.outmao.ebs.hotel.domain.HotelDeviceLeaseOrderDomain;
import com.outmao.ebs.hotel.domain.HotelDeviceRenterDomain;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrderItem;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private HotelDeviceRenterDomain hotelDeviceRenterDomain;

    @Autowired
    private OrderService orderService;


    @Transactional()
    @Override
    public HotelDeviceLeaseOrder hotelDeviceActive(Long deviceId) {
        HotelDeviceLeaseOrder order=hotelDeviceLeaseOrderDomain.hotelDeviceActive(deviceId);
        if(order!=null){
            if(order.getStatus()==HotelDeviceLeaseOrderStatus.IsActive.getStatus()){
                //设备全部激活后订单完成
                SetOrderStatusDTO statusDTO=new SetOrderStatusDTO();
                statusDTO.setOrderNo(order.getOrderNo());
                statusDTO.setStatus(OrderStatus.FINISHED.getStatus());
                statusDTO.setStatusRemark(OrderStatus.FINISHED.getStatusRemark());
                orderService.setOrderStatus(statusDTO);
            }
        }
        return order;
    }



    @Transactional()
    @Override
    public HotelDeviceLeaseOrder hotelDeviceDeploy(HotelDeviceDeployDTO request) {

        synchronized (this) {
            HotelDeviceLeaseOrder order = hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderByOrderNo(request.getOrderNo());
            if (order == null) {
                throw new BusinessException("订单不存在");
            }
            if (order.getStatus() != HotelDeviceLeaseOrderStatus.NoDeploy.getStatus()) {
                throw new BusinessException("订单状态异常");
            }
            check(order,request);

            List<HotelDeviceLeaseOrderItem> items = hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderItemListByOrderId(order.getId());

            request.setDevices(items.stream().map(t -> t.getDeviceId()).collect(Collectors.toList()));

            List<HotelRoomDeviceDeployDTO> deploys=new ArrayList<>(items.size());
            int index=0;
            for (HotelDeviceDeployHotelDTO hotel:request.getHotels()){
                for(String roomNo:hotel.getRooms()){
                    HotelDeviceLeaseOrderItem item=items.get(index++);
                    HotelRoomDeviceDeployDTO dto=new HotelRoomDeviceDeployDTO();
                    dto.setDeviceId(item.getDeviceId());
                    dto.setHotelId(hotel.getHotelId());
                    dto.setRoomNo(roomNo);
                    deploys.add(dto);
                }
            }

            hotelDeviceService.deploy(deploys);

            setHotelDeviceLeaseOrderStatus(new SetHotelDeviceLeaseOrderStatusDTO(order.getId(), HotelDeviceLeaseOrderStatus.IsDeploy.getStatus()));

            return order;
        }
    }

    private void check(HotelDeviceLeaseOrder order ,HotelDeviceDeployDTO request){
        int rooms=0;
        for(HotelDeviceDeployHotelDTO h:request.getHotels()){
            rooms+=h.getRooms().size();
        }
        if(rooms!=order.getQuantity()){
            throw new BusinessException("房间数量必须和设备数量一致");
        }
    }

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
        leaseDTO.setYears(request.getTime().getValue());
        List<HotelDevice> devices=hotelDeviceService.lease(leaseDTO);

        //保存订单信息
        HotelDeviceLeaseOrderDTO orderDTO=new HotelDeviceLeaseOrderDTO();
        BeanUtils.copyProperties(request,orderDTO);
        orderDTO.setDevices(devices.stream().map(t->t.getId()).collect(Collectors.toList()));
        orderDTO.setQuantity(request.getQuantity());
        HotelDeviceLeaseOrder order=hotelDeviceLeaseOrderDomain.saveHotelDeviceLeaseOrder(orderDTO);

        return order;
    }

    @Override
    public HotelDeviceLeaseOrder setHotelDeviceLeaseOrderStatus(SetHotelDeviceLeaseOrderStatusDTO request) {

        return hotelDeviceLeaseOrderDomain.setHotelDeviceLeaseOrderStatus(request);
    }

    @Override
    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id) {
        return hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderVOById(id);
    }

    @Override
    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOByOrderNo(String orderNo) {
        return hotelDeviceLeaseOrderDomain.getHotelDeviceLeaseOrderVOByOrderNo(orderNo);
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
