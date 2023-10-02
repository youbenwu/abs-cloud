package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.order.dao.OrderLogisticsDao;
import com.outmao.ebs.mall.order.dao.OrderLogisticsStatusDao;
import com.outmao.ebs.mall.order.dao.OrderLogisticsStatusItemDao;
import com.outmao.ebs.mall.order.domain.OrderLogisticsDomain;
import com.outmao.ebs.mall.order.domain.conver.OrderLogisticsStatusVOConver;
import com.outmao.ebs.mall.order.domain.conver.OrderLogisticsVOConver;
import com.outmao.ebs.mall.order.dto.OrderLogisticsDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusItemDTO;
import com.outmao.ebs.mall.order.entity.*;
import com.outmao.ebs.mall.order.vo.OrderLogisticsStatusVO;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderLogisticsDomainImpl extends BaseDomain implements OrderLogisticsDomain {



    @Autowired
    private OrderLogisticsDao orderLogisticsDao;
    @Autowired
    private OrderLogisticsStatusDao orderLogisticsStatusDao;
    @Autowired
    private OrderLogisticsStatusItemDao orderLogisticsStatusItemDao;

    @Autowired
    private OrderLogisticsVOConver orderLogisticsVOConver;

    @Autowired
    private OrderLogisticsStatusVOConver orderLogisticsStatusVOConver;


    @Transactional
    @Override
    public OrderLogistics saveOrderLogistics(OrderLogisticsDTO request) {
        OrderLogistics logistics=orderLogisticsDao.getOne(request.getId());
        BeanUtils.copyProperties(request,logistics);
        orderLogisticsDao.save(logistics);
        return logistics;
    }

    @Transactional
    @Override
    public void deleteOrderLogisticsById(Long id) {
        orderLogisticsDao.deleteById(id);
    }

    @Override
    public OrderLogisticsVO getOrderLogisticsVOById(Long id) {
        QOrderLogistics e=QOrderLogistics.orderLogistics;
        OrderLogisticsVO vo=queryOne(e,e.id.eq(id),orderLogisticsVOConver);
        vo.setStatuses(getOrderLogisticsStatusVOListByLogisticsId(id));
        return vo;
    }

    @Override
    public List<OrderLogisticsVO> getOrderLogisticsVOListByIdIn(Collection<Long> idIn) {
        QOrderLogistics e=QOrderLogistics.orderLogistics;
        List<OrderLogisticsVO> list=queryList(e,e.id.in(idIn),orderLogisticsVOConver);
        return list;
    }

    @Transactional
    @Override
    public OrderLogisticsStatus saveOrderLogisticsStatus(OrderLogisticsStatusDTO request) {
        OrderLogisticsStatus status=request.getId()==null?null:orderLogisticsStatusDao.getOne(request.getId());
        if(status==null){
            status=new OrderLogisticsStatus();
            status.setLogistics(orderLogisticsDao.getOne(request.getLogisticsId()));
            status.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,status);
        orderLogisticsStatusDao.save(status);

        OrderLogisticsStatusItemDTO itemDTO=new OrderLogisticsStatusItemDTO();
        itemDTO.setLogisticsId(status.getLogistics().getId());
        itemDTO.setStatusId(status.getId());
        itemDTO.setContent(request.getStatusContent());
        OrderLogisticsStatusItem item=saveOrderLogisticsStatusItem(itemDTO);

        status.getLogistics().setStatus(status.getStatus());
        status.getLogistics().setStatusRemark(status.getStatusRemark());
        status.getLogistics().setStatusContent(item.getContent());

        orderLogisticsDao.save(status.getLogistics());

        return status;
    }

    @Override
    public List<OrderLogisticsStatusVO> getOrderLogisticsStatusVOListByLogisticsId(Long logisticsId) {
        QOrderLogisticsStatus e=QOrderLogisticsStatus.orderLogisticsStatus;
        List<OrderLogisticsStatusVO> list=queryList(e,e.logistics.id.eq(logisticsId),orderLogisticsStatusVOConver);
        List<OrderLogisticsStatusItem> items=getOrderLogisticsStatusItemListByLogisticsId(logisticsId);
        list.forEach(t->{
            t.setItems(items.stream().filter(i->i.getStatusId().equals(t.getId())).collect(Collectors.toList()));
        });
        return list;
    }

    @Transactional
    @Override
    public OrderLogisticsStatusItem saveOrderLogisticsStatusItem(OrderLogisticsStatusItemDTO request) {

        OrderLogisticsStatusItem item=request.getId()==null?null:orderLogisticsStatusItemDao.getOne(request.getId());

        if(item==null){
            item=new OrderLogisticsStatusItem();
            item.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,item);

        orderLogisticsStatusItemDao.save(item);

        return item;
    }


    @Override
    public List<OrderLogisticsStatusItem> getOrderLogisticsStatusItemListByLogisticsId(Long logisticsId) {
        return orderLogisticsStatusItemDao.findAllByLogisticsId(logisticsId);
    }
}
