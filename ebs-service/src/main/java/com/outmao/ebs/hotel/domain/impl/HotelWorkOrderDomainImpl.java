package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelWorkOrderDao;
import com.outmao.ebs.hotel.domain.HotelWorkOrderDomain;
import com.outmao.ebs.hotel.domain.conver.HotelWorkOrderVOConver;
import com.outmao.ebs.hotel.dto.GetHotelWorkOrderListDTO;
import com.outmao.ebs.hotel.dto.HotelWorkOrderDTO;
import com.outmao.ebs.hotel.dto.SetHotelWorkOrderStatusDTO;
import com.outmao.ebs.hotel.entity.HotelWorkOrder;
import com.outmao.ebs.hotel.entity.QHotelWorkOrder;
import com.outmao.ebs.hotel.vo.HotelWorkOrderVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


@Component
public class HotelWorkOrderDomainImpl extends BaseDomain implements HotelWorkOrderDomain {


    @Autowired
    private HotelWorkOrderDao hotelWorkOrderDao;

    @Autowired
    private HotelDao hotelDao;


    private HotelWorkOrderVOConver hotelWorkOrderVOConver=new HotelWorkOrderVOConver();


    @Transactional()
    @Override
    public HotelWorkOrder saveHotelWorkOrder(HotelWorkOrderDTO request) {
        HotelWorkOrder order=request.getId()==null?null:hotelWorkOrderDao.getOne(request.getId());

        if(order==null){
            order=new HotelWorkOrder();
            order.setCreateTime(new Date());
            order.setHotel(hotelDao.getOne(request.getHotelId()));
            order.setOrgId(order.getHotel().getOrgId());
        }

        BeanUtils.copyProperties(request,order);
        order.setUpdateTime(new Date());

        order.setKeyword(getKeyword(order));

        hotelWorkOrderDao.save(order);

        return order;
    }


    private String getKeyword(HotelWorkOrder order){
        StringBuffer s=new StringBuffer();

        s.append(order.getContent());


        if(!StringUtils.isEmpty(order.getName())){
            s.append(" "+order.getName());
        }

        if(!StringUtils.isEmpty(order.getUserName())){
            s.append(" "+order.getUserName());
        }
        if(!StringUtils.isEmpty(order.getUserPhone())){
            s.append(" "+order.getUserPhone());
        }

        return s.toString();
    }


    @Transactional()
    @Override
    public void deleteHotelWorkOrderById(Long id) {
        HotelWorkOrder order=hotelWorkOrderDao.getOne(id);
        hotelWorkOrderDao.delete(order);
    }

    @Transactional()
    @Override
    public HotelWorkOrder setHotelWorkOrderStatus(SetHotelWorkOrderStatusDTO request) {
        HotelWorkOrder order=hotelWorkOrderDao.getOne(request.getId());
        BeanUtils.copyProperties(request,order);
        hotelWorkOrderDao.save(order);
        return order;
    }

    @Override
    public Page<HotelWorkOrderVO> getHotelWorkOrderVOPage(GetHotelWorkOrderListDTO request, Pageable pageable) {
        QHotelWorkOrder e=QHotelWorkOrder.hotelWorkOrder;
        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }
        Page<HotelWorkOrderVO> page=queryPage(e,p,hotelWorkOrderVOConver,pageable);
        return page;
    }


}
