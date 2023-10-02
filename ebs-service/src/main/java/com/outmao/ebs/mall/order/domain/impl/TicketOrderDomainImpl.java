package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.dao.TicketOrderDao;
import com.outmao.ebs.mall.order.domain.TicketOrderDomain;
import com.outmao.ebs.mall.order.dto.GetTicketOrderListDTO;
import com.outmao.ebs.mall.order.dto.SetTicketOrderOutStatusDTO;
import com.outmao.ebs.mall.order.dto.SetTicketOrderStatusDTO;
import com.outmao.ebs.mall.order.dto.TicketOrderDTO;
import com.outmao.ebs.mall.order.entity.QTicketOrder;
import com.outmao.ebs.mall.order.entity.TicketOrder;
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
public class TicketOrderDomainImpl extends BaseDomain implements TicketOrderDomain {



    @Autowired
    private TicketOrderDao ticketOrderDao;



    @Transactional
    @Override
    public TicketOrder saveTicketOrder(TicketOrderDTO request) {

        TicketOrder order=new TicketOrder();


        BeanUtils.copyProperties(request,order);

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());



        ticketOrderDao.save(order);


        return order;
    }


    @Override
    public TicketOrder getTicketOrderByOrderNo(String orderNo) {
        return ticketOrderDao.findByOrderNo(orderNo);
    }



    @Transactional
    @Override
    public TicketOrder setTicketOrderStatus(SetTicketOrderStatusDTO request) {

        TicketOrder order=ticketOrderDao.findByIdForUpdate(request.getId());

        if(request.getStatus()==request.getStatus()){
            return order;
        }

        if(order.getStatus()== OrderStatus.WAIT_PAY.getStatus()){
            //支付成功 或者 关闭订单
            if(request.getStatus()!=OrderStatus.SUCCESSED.getStatus() &&
                    request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){

            if(request.getStatus()!=OrderStatus.DELIVERED.getStatus()&&
                    request.getStatus()!=OrderStatus.FINISHED.getStatus()&&
                    request.getStatus()!=OrderStatus.CLOSED.getStatus()
            ){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.DELIVERED.getStatus()){
            if(request.getStatus()!=OrderStatus.FINISHED.getStatus()&&
                    request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.FINISHED.getStatus()
                ||order.getStatus()==OrderStatus.CLOSED.getStatus()){
            throw new BusinessException("不允许的状态");
        }

        BeanUtils.copyProperties(request,order);

        ticketOrderDao.save(order);

        return order;
    }



    @Transactional
    @Override
    public TicketOrder setTicketOrderOutStatus(SetTicketOrderOutStatusDTO request) {
        //外部订单状态（待支付 = 1 ,出票中 = 2,已出票 = 3 ,出票失败 = 4 ,退订中 = 5 ,退订失败 = 6 ,已退订 = 7 , 已取消 = 99）
        TicketOrder order=ticketOrderDao.findByIdForUpdate(request.getId());

        order.setOutStatus(request.getOutStatus());

        ticketOrderDao.save(order);

        return order;
    }




    @Override
    public Page<TicketOrder> getTicketOrderPage(GetTicketOrderListDTO request, Pageable pageable) {

        QTicketOrder e=QTicketOrder.ticketOrder;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getStartTime()!=null&&request.getEndTime()!=null){
            p=e.createTime.between(request.getStartTime(),request.getEndTime()).and(p);
        }

        Page<TicketOrder> page=ticketOrderDao.findAll(p,pageable);


        return page;
    }



}
