package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.hotel.dao.HotelDeviceLeaseOrderDao;
import com.outmao.ebs.hotel.dao.HotelDeviceLeaseOrderItemDao;
import com.outmao.ebs.hotel.domain.HotelDeviceLeaseOrderDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceLeaseOrderItemVOConver;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceLeaseOrderVOConver;
import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderItemListDTO;
import com.outmao.ebs.hotel.dto.GetHotelDeviceLeaseOrderListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceLeaseOrderDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrderItem;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.entity.QHotelDeviceLeaseOrderItem;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderItemVO;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class HotelDeviceLeaseOrderDomainImpl extends BaseDomain implements HotelDeviceLeaseOrderDomain {

    @Autowired
    private HotelDeviceLeaseOrderDao hotelDeviceLeaseOrderDao;

    @Autowired
    private HotelDeviceLeaseOrderItemDao hotelDeviceLeaseOrderItemDao;


    private HotelDeviceLeaseOrderVOConver hotelDeviceLeaseOrderVOConver=new HotelDeviceLeaseOrderVOConver();
    private HotelDeviceLeaseOrderItemVOConver hotelDeviceLeaseOrderItemVOConver=new HotelDeviceLeaseOrderItemVOConver();


    @Transactional()
    @Override
    public HotelDeviceLeaseOrder saveHotelDeviceLeaseOrder(HotelDeviceLeaseOrderDTO request) {
        HotelDeviceLeaseOrder order=new HotelDeviceLeaseOrder();

        BeanUtils.copyProperties(request,order);

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        hotelDeviceLeaseOrderDao.save(order);

        if(request.getDevices()!=null){
            saveItems(order,request.getDevices());
        }

        return order;
    }


    private void saveItems(HotelDeviceLeaseOrder order, List<Long> devices){
        devices.forEach(d->{
            HotelDeviceLeaseOrderItem item=new HotelDeviceLeaseOrderItem();
            item.setOrderId(order.getId());
            item.setDeviceId(d);
            item.setCreateTime(new Date());
            hotelDeviceLeaseOrderItemDao.save(item);
        });
    }


    @Override
    public HotelDeviceLeaseOrderVO getHotelDeviceLeaseOrderVOById(Long id) {
        QHotelDeviceLeaseOrder e=QHotelDeviceLeaseOrder.hotelDeviceLeaseOrder;

        HotelDeviceLeaseOrderVO vo=queryOne(e,e.id.eq(id),hotelDeviceLeaseOrderVOConver);

        return vo;
    }

    @Override
    public Page<HotelDeviceLeaseOrderVO> getHotelDeviceLeaseOrderVOPage(GetHotelDeviceLeaseOrderListDTO request, Pageable pageable) {

        QHotelDeviceLeaseOrder e=QHotelDeviceLeaseOrder.hotelDeviceLeaseOrder;

        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }


        Page<HotelDeviceLeaseOrderVO> page=queryPage(e,p,hotelDeviceLeaseOrderVOConver,pageable);

        return page;
    }


    @Override
    public Page<HotelDeviceLeaseOrderItemVO> getHotelDeviceLeaseOrderItemVOPage(GetHotelDeviceLeaseOrderItemListDTO request, Pageable pageable) {
        QHotelDeviceLeaseOrderItem e=QHotelDeviceLeaseOrderItem.hotelDeviceLeaseOrderItem;
        Predicate p=null;
        if(request.getOrderId()!=null){
            p=e.orderId.eq(request.getOrderId()).and(p);
        }

        Page<HotelDeviceLeaseOrderItemVO> page=queryPage(e,p,hotelDeviceLeaseOrderItemVOConver,pageable);

        return page;
    }



}
