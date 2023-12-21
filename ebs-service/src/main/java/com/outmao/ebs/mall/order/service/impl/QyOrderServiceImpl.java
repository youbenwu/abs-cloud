package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.HotelDeviceLeaseOrderVO;
import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.QyOrderService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.QyAdvertOrderVO;
import com.outmao.ebs.mall.order.vo.QyDeviceLeaseOrderVO;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.portal.service.AdvertBuyOrderService;
import com.outmao.ebs.portal.vo.AdvertVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QyOrderServiceImpl implements QyOrderService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;

    @Autowired
    private AdvertBuyOrderService advertBuyOrderService;


    @Override
    public QyDeviceLeaseOrderVO getQyDeviceLeaseOrderVOById(Long id) {
        OrderVO vo=orderService.getOrderVOById(id);
        HotelDeviceLeaseOrderVO leaseOrderVO=hotelDeviceLeaseService.getHotelDeviceLeaseOrderVOByOrderNo(vo.getOrderNo());
        QyDeviceLeaseOrderVO vo1=new QyDeviceLeaseOrderVO();
        BeanUtils.copyProperties(vo,vo1);
        vo1.setLeaseInfo(leaseOrderVO);
        return vo1;
    }


    @Override
    public Page<QyDeviceLeaseOrderVO> getQyDeviceLeaseOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        request.setType(ProductType.HOTEL_DEVICE_LEASE.getType());
        Page<OrderVO> page=orderService.getOrderVOPage(request,pageable);

        List<QyDeviceLeaseOrderVO> content=new ArrayList<>(page.getContent().size());

        page.getContent().forEach(vo->{
            HotelDeviceLeaseOrderVO leaseOrderVO=hotelDeviceLeaseService.getHotelDeviceLeaseOrderVOByOrderNo(vo.getOrderNo());
            QyDeviceLeaseOrderVO vo1=new QyDeviceLeaseOrderVO();
            BeanUtils.copyProperties(vo,vo1);
            vo1.setLeaseInfo(leaseOrderVO);
            content.add(vo1);
        });

        return new PageImpl(content,pageable,page.getTotalElements());
    }



    @Override
    public QyAdvertOrderVO getQyAdvertOrderVOById(Long id) {
        OrderVO vo=orderService.getOrderVOById(id);

        QyAdvertOrderVO vo1=new QyAdvertOrderVO();
        BeanUtils.copyProperties(vo,vo1);
        List<AdvertVO> advertVOS=advertBuyOrderService.getAdvertVOListByOrderNoIn(Arrays.asList(vo.getOrderNo()));
        if(advertVOS.size()>0){
            vo1.setAdvert(advertVOS.get(0));
        }
        return vo1;
    }

    @Override
    public Page<QyAdvertOrderVO> getQyAdvertOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        request.setType(ProductType.HOTEL_ADVERT_CHANNEL.getType());
        Page<OrderVO> page=orderService.getOrderVOPage(request,pageable);
        List<QyAdvertOrderVO> content=new ArrayList<>(page.getContent().size());
        if(page.getContent().size()>0){
            List<AdvertVO> advertVOS=advertBuyOrderService.getAdvertVOListByOrderNoIn(page.getContent().stream().map(t->t.getOrderNo()).collect(Collectors.toList()));
            Map<String,AdvertVO> advertVOMap=advertVOS.stream().collect(Collectors.toMap(t->t.getOrderNo(),t->t));
            page.getContent().forEach(t->{
                QyAdvertOrderVO vo=new QyAdvertOrderVO();
                BeanUtils.copyProperties(t,vo);
                AdvertVO advertVO=advertVOMap.get(t.getOrderNo());
                vo.setAdvert(advertVO);
                content.add(vo);
            });
        }
        return new PageImpl<>(content,pageable,page.getTotalElements());
    }




}
