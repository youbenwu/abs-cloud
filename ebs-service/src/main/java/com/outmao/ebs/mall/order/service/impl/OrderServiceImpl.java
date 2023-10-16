package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.SimpleContact;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.dto.OrderProductDTO;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.entity.OrderAddress;
import com.outmao.ebs.mall.order.entity.OrderProduct;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.product.dto.ProductSkuStockOutDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.store.common.constant.StoreSkuStockOutStatus;
import com.outmao.ebs.mall.store.domain.StoreSkuDomain;
import com.outmao.ebs.mall.store.dto.SetStoreSkuStockOutStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutItemDTO;
import com.outmao.ebs.mall.store.service.StoreService;
import com.outmao.ebs.mall.store.service.StoreSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    @Autowired
    private OrderDomain orderDomain;

    @Autowired
    private OrderStatsDomain orderStatsDomain;


    @Autowired
    private StoreSkuService storeSkuService;

    @Autowired
    private ProductService productService;


    @Transactional
    @Override
    public Order saveOrder(OrderDTO request) {

        Order order= orderDomain.saveOrder(request);

        updateStore(order);

        return order;
    }

    private void updateStore(Order order){
        if(order.isOut())
            return;

        if(!order.isUseStoreStock()){
            List<ProductSkuStockOutDTO> list=new ArrayList<>(order.getProducts().size());
            order.getProducts().forEach(t->{
                ProductSkuStockOutDTO outDTO=new ProductSkuStockOutDTO();
                outDTO.setSkuId(t.getSkuId());
                outDTO.setQuantity(t.getQuantity());
                list.add(outDTO);
            });
            productService.skuStockOut(list);
            return;
        }


        StoreSkuStockOutDTO dto=new StoreSkuStockOutDTO();
        dto.setOrderId(order.getId());
        dto.setStoreId(order.getFromStoreId());

        List<StoreSkuStockOutItemDTO> items=new ArrayList<>(order.getProducts().size());

        order.getProducts().forEach(t->{
            StoreSkuStockOutItemDTO item=new StoreSkuStockOutItemDTO();
            item.setSkuId(t.getSkuId());
            item.setStock(t.getQuantity());
        });

        dto.setItems(items);

        storeSkuService.saveStoreSkuStockOut(dto);

    }

    @Override
    public Order setOrderStatus(SetOrderStatusDTO request) {

        Order order= orderDomain.setOrderStatus(request);

        if(order.getStatus()== OrderStatus.DELIVERED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.WAIT_OUT.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.WAIT_OUT.getStatusRemark());
            storeSkuService.setStoreSkuStockOutStatus(dto);
        }else if(order.getStatus()==OrderStatus.CLOSED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.CLOSED.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.CLOSED.getStatusRemark());
            storeSkuService.setStoreSkuStockOutStatus(dto);
        }
        return order;
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDomain.deleteOrderById(id);
    }

    @Override
    public OrderVO getOrderVOById(Long id) {
        return orderDomain.getOrderVOById(id);
    }

    @Override
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        return orderDomain.getOrderVOPage(request,pageable);
    }

    @Override
    public long getOrderCount() {
        return orderDomain.getOrderCount();
    }

    @Override
    public double getOrderAmount() {
        return orderDomain.getOrderAmount();
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime) {
        return orderStatsDomain.getStatsOrderVOListByDays(fromTime,toTime);
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn) {
        return orderStatsDomain.getStatsOrderVOListByUserIdIn(userIdIn);
    }


}
