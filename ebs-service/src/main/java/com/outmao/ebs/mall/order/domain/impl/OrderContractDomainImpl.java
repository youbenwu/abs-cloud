package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.order.dao.OrderContractDao;
import com.outmao.ebs.mall.order.dao.OrderDao;
import com.outmao.ebs.mall.order.domain.OrderContractDomain;
import com.outmao.ebs.mall.order.domain.conver.OrderContractVOConver;
import com.outmao.ebs.mall.order.dto.GetOrderContractListDTO;
import com.outmao.ebs.mall.order.dto.OrderContractDTO;
import com.outmao.ebs.mall.order.entity.OrderContract;
import com.outmao.ebs.mall.order.entity.QOrderContract;
import com.outmao.ebs.mall.order.vo.OrderContractVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class OrderContractDomainImpl extends BaseDomain implements OrderContractDomain {

    @Autowired
    private OrderContractDao orderContractDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderContractVOConver orderContractVOConver;

    @Transactional
    @Override
    public OrderContract saveOrderContract(OrderContractDTO request) {
        OrderContract contract=request.getId()==null?null:orderContractDao.getOne(request.getId());
        if(contract==null){
            contract=new OrderContract();
            contract.setCreateTime(new Date());
            contract.setOrder(orderDao.getOne(request.getOrderId()));
        }
        BeanUtils.copyProperties(request,contract);
        contract.setUpdateTime(new Date());
        orderContractDao.save(contract);
        return contract;
    }

    @Transactional
    @Override
    public void deleteOrderContractById(Long id) {
        orderContractDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteOrderContractListByOrderId(Long orderId) {
        orderContractDao.deleteAllByOrderId(orderId);
    }

    @Override
    public OrderContractVO getOrderContractVOById(Long id) {
        QOrderContract e=QOrderContract.orderContract;
        return queryOne(e,e.id.eq(id),orderContractVOConver);
    }

    @Override
    public List<OrderContractVO> getOrderContractVOListByOrderId(Long orderId) {
        QOrderContract e=QOrderContract.orderContract;
        return queryList(e,e.order.id.eq(orderId),orderContractVOConver);
    }

    @Override
    public Page<OrderContractVO> getOrderContractVOPage(GetOrderContractListDTO request, Pageable pageable) {
        QOrderContract e=QOrderContract.orderContract;
        Predicate p=null;
        if(request.getOrderId()!=null){
            p=e.order.id.eq(request.getOrderId());
        }
        return queryPage(e,p,orderContractVOConver,pageable);
    }


}
