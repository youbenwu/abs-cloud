package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.SimpleContact;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.domain.OrderContractDomain;
import com.outmao.ebs.mall.order.domain.OrderLogisticsDomain;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.shop.common.annotation.SetSimpleShop;
import com.outmao.ebs.mall.order.dao.*;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.conver.*;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.*;
import com.outmao.ebs.mall.order.vo.*;
import com.outmao.ebs.mall.product.domain.ProductDomain;
import com.outmao.ebs.mall.product.domain.ProductSnapshotDomain;
import com.outmao.ebs.mall.product.entity.ProductSnapshot;
import com.outmao.ebs.mall.product.vo.ProductVO;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.store.common.constant.StoreSkuStockOutStatus;
import com.outmao.ebs.mall.store.domain.StoreSkuDomain;
import com.outmao.ebs.mall.store.dto.SetStoreSkuStockOutStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutDTO;
import com.outmao.ebs.mall.store.dto.StoreSkuStockOutItemDTO;
import com.outmao.ebs.mall.store.entity.StoreSkuStockOut;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.entity.User;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class OrderDomainImpl extends BaseDomain implements OrderDomain {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogisticsDao orderLogisticsDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private OrderAddressDao orderAddressDao;

    @Autowired
    private OrderLogisticsDomain orderLogisticsDomain;

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private ProductDomain productDomain;

    @Autowired
    private ProductSnapshotDomain productSnapshotDomain;

    @Autowired
    private OrderContractDomain orderContractDomain;


    @Autowired
    private MerchantCustomerDomain merchantCustomerDomain;


    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;


    @Autowired
    private StoreSkuDomain storeSkuDomain;


    @Autowired
    private OrderAddressVOConver orderAddressVOConver;
    @Autowired
    private OrderVOConver orderVOConver;
    @Autowired
    private OrderProductVOConver orderProductVOConver;


    @Transactional
    @Override
    public Order saveOrder(OrderDTO request) {


        Order order=new Order();

        //设置关联
        Shop shop=shopDao.getOne(request.getShopId());
        User user=userDao.getOne(request.getUserId());

        order.setShop(shop);
        order.setUser(user);

        order.setSellerId(shop.getId());
        order.setOrgId(shop.getOrgId());
        order.setMerchantId(shop.getMerchantId());

        MerchantCustomer customer=merchantCustomerDomain.getMerchantCustomer(shop.getMerchantId(),user.getId());
        if(customer!=null){
            order.setCustomerId(customer.getId());
            order.setBrokerId(customer.getBroker()!=null?customer.getBroker().getId():null);
            order.setPartnerId(customer.getPartner()!=null?customer.getPartner().getId():null);
            if(customer.getPartner()!=null&&customer.getPartner().getParent()!=null){
                order.setPartnerParentId(customer.getPartner().getParent().getId());
            }
        }

        //设置订单号
        order.setOrderNo(OrderNoUtil.generateOrderNo());



        BeanUtils.copyProperties(request,order,"address","products");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderDao.save(order);

        //订单地址
        setAddress(order,request.getAddress());

        //设置物流信息
        setOrderLogistics(order);

        saveOrderProductList(order,request.getProducts());

        saveOrderContractList(order,request.getContracts());

        order.setKeyword(getKeyword(order));

        //更新库存
        updateStore(order);

        return order;
    }

    private void updateStore(Order order){

        if(!order.getShop().isUseStoreStock()){
            for(OrderProduct p:order.getProducts()){
                Product product=productDao.findByIdForUpdate(p.getProductId());
                ProductSku sku=productSkuDao.findByIdForUpdate(p.getSkuId());
                if(sku.getStock()<p.getQuantity()){
                    throw new BusinessException("库存不足");
                }
                product.setStock(product.getStock()-p.getQuantity());
                sku.setStock(sku.getStock()-p.getQuantity());
            }
            return ;
        }

        StoreSkuStockOutDTO dto=new StoreSkuStockOutDTO();
        dto.setOrderId(order.getId());
        dto.setStoreId(order.getFromStoreId());
        if(order.getAddressId()!=null) {
            OrderAddress address=orderAddressDao.getOne(order.getAddressId());
            SimpleContact contact=new SimpleContact();
            contact.setName(address.getName());
            contact.setPhone(address.getPhone());
            contact.setAddress(address.toFullAddress());
            dto.setTo(contact);
        }

        List<StoreSkuStockOutItemDTO> items=new ArrayList<>(order.getProducts().size());

        order.getProducts().forEach(t->{
            StoreSkuStockOutItemDTO item=new StoreSkuStockOutItemDTO();
            item.setSkuId(t.getSkuId());
            item.setStock(t.getQuantity());
        });

        dto.setItems(items);

        storeSkuDomain.saveStoreSkuStockOut(dto);

    }

    private void setAddress(Order order,OrderAddressDTO data){
        if(data==null)
            return;
        OrderAddress address=new OrderAddress();
        BeanUtils.copyProperties(data,address);
        address.setOrderId(order.getId());
        orderAddressDao.save(address);
        order.setAddressId(address.getId());
    }

    private void saveOrderProductList(Order order,List<OrderProductDTO> data){
        List<OrderProduct> products=new ArrayList<>();
        for (OrderProductDTO t:data){
            OrderProduct p=new OrderProduct();
            p.setOrder(order);
            BeanUtils.copyProperties(t,p);
            products.add(p);
        }
        saveSnapshotList(products);
        orderProductDao.saveAll(products);
        order.setProducts(products);
    }

    private void setOrderLogistics(Order order){
        OrderLogistics logistics=new OrderLogistics();
        if(order.getAddressId()!=null){
            OrderAddress address=orderAddressDao.getOne(order.getAddressId());
            SimpleContact to=new SimpleContact();
            to.setName(address.getName());
            to.setPhone(address.getPhone());
            to.setAddress(address.toFullAddress());
            logistics.setTo(to);
        }
        logistics.setCreateTime(new Date());
        logistics.setUpdateTime(new Date());
        logistics.setOrderId(order.getId());
        orderLogisticsDao.save(logistics);
        order.setLogisticsId(logistics.getId());
    }


    private void saveOrderContractList(Order order,List<OrderContractDTO> list){
        if(list==null||list.isEmpty())
            return;
        list.forEach(t->{
            t.setOrderId(order.getId());
            orderContractDomain.saveOrderContract(t);
        });
    }


    private void saveSnapshotList(List<OrderProduct> products){
        products.forEach(t->{
            ProductVO vo=productDomain.getProductVO(t.getProductId(),t.getSkuId());
            ProductSnapshot snapshot=productSnapshotDomain.saveProductSnapshot(vo,t.getOrder().getId());
            t.setSnapshotId(snapshot.getId());
        });
    }

    private String getKeyword(Order m){
        StringBuffer s=new StringBuffer();
        if(m.getRemark()!=null){
            s.append(" "+m.getRemark());
        }
        if(m.getAddressId()!=null){
            OrderAddress address=orderAddressDao.getOne(m.getAddressId());
            s.append(" "+address.getName());
            s.append(" "+address.getPhone());
            s.append(" "+address.getFullAddress());
        }

        if(m.getProducts()!=null){
            m.getProducts().forEach(t->{
                s.append(" "+t.getProductTitle());
            });
        }

        return s.toString();
    }

    @Transactional
    @Override
    public Order setOrderStatus(SetOrderStatusDTO request) {
        Order order=orderDao.findByIdForUpdate(request.getId());

        if(order.getStatus()==request.getStatus()){
            return order;
        }

        if(order.getStatus()==OrderStatus.WAIT_PAY.getStatus()){
            if(request.getStatus()!=OrderStatus.SUCCESSED.getStatus()&&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            if(request.getStatus()!=OrderStatus.DELIVERED.getStatus()&&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.DELIVERED.getStatus()){
            if(request.getStatus()!=OrderStatus.FINISHED.getStatus()&&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.FINISHED.getStatus()){
            if(request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }

        BeanUtils.copyProperties(request,order);

        orderDao.save(order);

        if(order.getStatus()==OrderStatus.DELIVERED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.WAIT_OUT.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.WAIT_OUT.getStatusRemark());
            storeSkuDomain.setStoreSkuStockOutStatus(dto);
        }else if(order.getStatus()==OrderStatus.CLOSED.getStatus()){
            SetStoreSkuStockOutStatusDTO dto=new SetStoreSkuStockOutStatusDTO();
            dto.setOrderId(order.getId());
            dto.setStatus(StoreSkuStockOutStatus.CLOSED.getStatus());
            dto.setStatusRemark(StoreSkuStockOutStatus.CLOSED.getStatusRemark());
            storeSkuDomain.setStoreSkuStockOutStatus(dto);
        }

        return order;
    }


    @Transactional
    @Override
    public void deleteOrderById(Long id) {
        Order order=orderDao.findByIdForUpdate(id);
        if(order.getStatus()!= OrderStatus.WAIT_PAY.getStatus()){
            throw new BusinessException("不允许删除");
        }
        orderLogisticsDomain.deleteOrderLogisticsById(order.getLogisticsId());
        orderAddressDao.deleteAllByOrderId(id);
        orderProductDao.deleteAllByOrderId(id);
        orderContractDomain.deleteOrderContractListByOrderId(id);
        orderDao.deleteById(id);
    }

    @SetSimpleUser
    @SetSimpleShop
    @Override
    public OrderVO getOrderVOById(Long id) {
        QOrder e=QOrder.order;

        OrderVO vo=queryOne(e,e.id.eq(id),orderVOConver);

        if(vo==null)
            return null;

        vo.setProducts(getOrderProductVOListByOrderId(id));

        vo.setAddress(getOrderAddressVOById(vo.getAddressId()));

        vo.setLogistics(orderLogisticsDomain.getOrderLogisticsVOById(vo.getLogisticsId()));

        vo.setContracts(orderContractDomain.getOrderContractVOListByOrderId(id));

        return vo;
    }


    private List<OrderProductVO> getOrderProductVOListByOrderId(Long orderId){
        QOrderProduct e=QOrderProduct.orderProduct;
       return  queryList(e,e.order.id.eq(orderId),orderProductVOConver);
    }


    private OrderAddressVO getOrderAddressVOById(Long id){
        if(id==null)
            return null;
        QOrderAddress e=QOrderAddress.orderAddress;
        OrderAddressVO vo=queryOne(e,e.id.eq(id),orderAddressVOConver);
        return vo;
    }

    @SetSimpleUser
    @SetSimpleShop
    @Override
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {

        QOrder e=QOrder.order;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getShopId()!=null){
            p=e.shop.id.eq(request.getShopId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId()).and(p);
        }

        if(request.getBrokerId()!=null){
            p=e.brokerId.eq(request.getBrokerId()).and(p);
        }

        if(request.getPartnerId()!=null){
            p=e.partnerId.eq(request.getPartnerId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<OrderVO> page=queryPage(e,p,orderVOConver,pageable);

        getOrderProductVOList(page.getContent());
        getOrderLogisticsVOList(page.getContent());
        getOrderAddressVOList(page.getContent());

        return page;
    }

    private void getOrderProductVOList(List<OrderVO> list){
        QOrderProduct e=QOrderProduct.orderProduct;
        List<OrderProductVO> data=queryList(e,e.order.id.in(list.stream().map(t->t.getId()).collect(Collectors.toList())),orderProductVOConver);

        Map<Long,OrderVO> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        data.forEach(t->{
            OrderVO vo=listMap.get(t.getOrderId());
            if(vo.getProducts()==null){
                vo.setProducts(new ArrayList<>());
            }
            vo.getProducts().add(t);
        });

    }


    private void getOrderAddressVOList(List<OrderVO> list){
        QOrderAddress e=QOrderAddress.orderAddress;
        List<OrderAddressVO> data=queryList(e,e.id.in(list.stream().filter(t->t.getAddressId()!=null).map(t->t.getAddressId()).collect(Collectors.toList())),orderAddressVOConver);
        Map<Long,OrderAddressVO> dataMap=data.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        list.forEach(t->{
            if(t.getAddressId()!=null) {
                t.setAddress(dataMap.get(t.getAddressId()));
            }
        });

    }

    private void getOrderLogisticsVOList(List<OrderVO> list){
        List<OrderLogisticsVO> data=orderLogisticsDomain.getOrderLogisticsVOListByIdIn(list.stream().map(t->t.getLogisticsId()).collect(Collectors.toList()));
        Map<Long,OrderLogisticsVO> dataMap=data.stream().collect(Collectors.toMap(t->t.getId(),t->t));
        list.forEach(t->{
            t.setLogistics(dataMap.get(t.getLogisticsId()));
        });

    }



    @Override
    public long getOrderCount() {
        return orderDao.count();
    }

    @Override
    public double getOrderAmount() {
        Double amount= orderDao.sumTotalAmount();
        if(amount==null)
            amount=0.0;
        return amount;
    }


}
