package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.exception.IdempotentException;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.vo.Between;
import com.outmao.ebs.common.vo.SimpleContact;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.mall.merchant.dao.MerchantCustomerDao;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.common.constant.OrderSubStatus;
import com.outmao.ebs.mall.order.common.util.OrderProductLeaseUtil;
import com.outmao.ebs.mall.order.common.util.OrderStatusUtil;
import com.outmao.ebs.mall.order.domain.OrderContractDomain;
import com.outmao.ebs.mall.order.domain.OrderLogisticsDomain;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
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
import com.outmao.ebs.user.common.annotation.SetContactUser;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.entity.User;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
    private MerchantDao merchantDao;

    @Autowired
    private MerchantCustomerDao merchantCustomerDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;





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
        MerchantCustomer customer=merchantCustomerDao.findByMerchantIdAndUserId(shop.getMerchantId(),user.getId());


        order.setShopId(shop.getId());
        order.setUserId(user.getId());
        order.setSellerId(shop.getUserId());
        order.setOrgId(shop.getOrgId());
        order.setMerchantId(shop.getMerchantId());
        order.setUseStoreStock(shop.isUseStoreStock());

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


        Map<Long,ProductVO> products =getProductVOMap(request.getProducts());


        saveOrderProductList(order,request.getProducts(),products);

        //计算佣金
        saveOrderCommission(order,products);

        //订单地址
        setAddress(order,request.getAddress());

        //设置物流信息
        setOrderLogistics(order);

        saveOrderContractList(order,request.getContracts());

        order.setDescription(getDescription(order));

        order.setKeyword(getKeyword(order));

        return order;
    }

    private void saveOrderCommission(Order order,Map<Long,ProductVO> pmap){
        Merchant merchant=merchantDao.getOne(order.getMerchantId());
        if(!merchant.isDistribution())
            return;

        double totalAmount=0;
        //佣金比率
        double productCommissionRate=merchant.getProductCommissionRate();

        for (OrderProduct p:order.getProducts()){
            ProductVO vo=pmap.get(p.getSkuId());
            if(vo==null)
                continue;
            if(!vo.isDistribution())
                continue;

            double rate=productCommissionRate;
            double amount=0;

            if(vo.getCommissionType()==0){
                amount=vo.getCommissionAmount();
            }else{
                rate=vo.getCommissionRate();
            }

            if(amount==0){
                amount=p.getAmount()*rate;
            }

            totalAmount+=amount;
            p.setCommissionAmount(amount);
        }
        order.setCommissionAmount(totalAmount);
    }


    private String getDescription(Order order){
        StringBuffer sb=new StringBuffer("");
        order.getProducts().forEach(t->{
            sb.append(t.getProductTitle()+" ");
        });
        return sb.toString().trim();
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderDao.findByOrderNo(orderNo);
    }

    private void setAddress(Order order, OrderAddressDTO data){
        if(data==null)
            return;
        OrderAddress address=new OrderAddress();
        BeanUtils.copyProperties(data,address);
        address.setOrderId(order.getId());
        orderAddressDao.save(address);
        order.setAddressId(address.getId());
    }


    private Map<Long,ProductVO> getProductVOMap(List<OrderProductDTO> data){
        Map<Long,ProductVO> map=new HashMap<>();
        data.forEach(p->{
            ProductVO product=productDomain.getProductVO(p.getProductId(),p.getSkuId());
            map.put(p.getSkuId(),product);
        });
        return map;
    }

    private void saveOrderProductList(Order order,List<OrderProductDTO> data,Map<Long,ProductVO> pmap){
        boolean noDelivery=true;
        boolean sellerFinish=true;
        List<OrderProduct> products=new ArrayList<>();
        for (OrderProductDTO dto:data){
            OrderProduct p=new OrderProduct();
            p.setOrder(order);
            BeanUtils.copyProperties(dto,p);

            ProductVO product=pmap.get(dto.getSkuId());
            if(product!=null){
                //保存商品快照
                ProductSnapshot snapshot=productSnapshotDomain.saveProductSnapshot(product,order.getId());
                p.setSnapshotId(snapshot.getId());
                p.setProductType(product.getType());

                p.setSellerFinish(product.isSellerFinish());
                if(!p.isSellerFinish()){
                    sellerFinish=false;
                }

                //是否需要发货
                p.setNoDelivery(product.isNoDelivery());
                if(!p.isNoDelivery()){
                    noDelivery=false;
                }

                //保存租赁信息
                if(product.getLease()!=null&&product.getLease().isLease()){
                    p.setLease(OrderProductLeaseUtil.skuNameToLease(p.getSkuName()));
                    if(p.getLease().isLease()){
                        order.setLease(true);
                    }
                }

            }
            products.add(p);
        }
        orderProductDao.saveAll(products);
        order.setSellerFinish(sellerFinish);
        order.setNoDelivery(noDelivery);
        order.setProducts(products);
        if(products.size()==1){
            order.setType(products.get(0).getProductType());
        }



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

    private void setOrderStatus(Order order,SetOrderStatusDTO request) {

        security.hasPermission(order.getOrgId(),null);

        if(order.getStatus()==request.getStatus()){
            throw new IdempotentException(order);
        }

        if(order.getStatus()==OrderStatus.WAIT_PAY.getStatus()){
            //待支付--》支付成功 交易关闭
            if(request.getStatus()!=OrderStatus.SUCCESSED.getStatus()
                    &&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            //支付成功--》商家发货 交易完成 交易关闭
            if(request.getStatus()!=OrderStatus.DELIVERED.getStatus()
                    &&request.getStatus()!=OrderStatus.FINISHED.getStatus()
                    &&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.DELIVERED.getStatus()){
            //商家发货--》 交易完成 交易关闭
            if(request.getStatus()!=OrderStatus.FINISHED.getStatus()
                    &&request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }else if(order.getStatus()==OrderStatus.FINISHED.getStatus()){
            //交易完成--》  交易关闭
            if(request.getStatus()!=OrderStatus.CLOSED.getStatus()){
                throw new BusinessException("不允许的状态");
            }
        }

        BeanUtils.copyProperties(request,order,"orderNo");

        if(!order.isOut()&&order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            //计算预计发货时间
            int m=0;
            List<TimeSpan> timeSpans=productDao.findAllExpectDeliveryTimeSpanByIdIn(order.getProducts().stream().map(t->t.getProductId()).collect(Collectors.toList()));
            for (TimeSpan t:timeSpans){
                int c=t.getMinutes();
                if(c>m)
                    m=c;
            }
            if(m>0){
                order.setExpectDeliveryTime(DateUtil.addMinutes(new Date(),m));
            }
        }

        if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            //租赁处理
            if(order.isLease()){
                leaseStart(order);
            }

        }

        if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            order.setSuccessTime(new Date());
        }else if(order.getStatus()==OrderStatus.DELIVERED.getStatus()){
            order.setDeliveryTime(new Date());
        }else if(order.getStatus()==OrderStatus.FINISHED.getStatus()){
            order.setFinishTime(new Date());
        }else if(order.getStatus()==OrderStatus.CLOSED.getStatus()){
            order.setCloseTime(new Date());
        }
        order.setUpdateTime(new Date());


    }


    @Transactional
    @Override
    public Order setOrderStatus(SetOrderStatusDTO request) {

        Order order=orderDao.findByOrderNo(request.getOrderNo());

        setOrderStatus(order,request);

        orderDao.save(order);

        return order;
    }


    @Transactional
    @Override
    public Order closeOrder(CloseOrderDTO request) {
        Order order=orderDao.findByOrderNo(request.getOrderNo());

        if(order.getStatus()!=OrderStatus.WAIT_PAY.getStatus()){
            log.error("关闭订单失败 \n 订单号:{} \n 订单状态:{} {} \n 失败原因：只有待支付状态订单才能关闭"
                    ,order.getOrderNo(),order.getStatus(),order.getStatusRemark());
            throw new BusinessException("订单状态异常");
        }

        OrderStatus status=OrderStatus.CLOSED;
        OrderSubStatus subStatus= OrderStatusUtil.getSubStatus(request.getSubStatus());

        if(!OrderStatusUtil.isSub(status,subStatus)){
            throw new BusinessException("订单状态异常");
        }

        SetOrderStatusDTO statusDTO=new SetOrderStatusDTO();
        statusDTO.setOrderNo(order.getOrderNo());
        statusDTO.setStatus(status.getStatus());
        statusDTO.setStatusRemark(status.getStatusRemark());
        statusDTO.setSubStatus(subStatus.getStatus());
        statusDTO.setSubStatusRemark(subStatus.getStatusRemark());

        setOrderStatus(order,statusDTO);

        orderDao.save(order);

        return order;
    }



    private void leaseStart(Order order){
        order.getProducts().forEach(p->{
            if(p.getLease()!=null&&p.getLease().isLease()){
                Between<Date> between=p.getLease().getDateBetween(new Date());
                p.getLease().setStartTime(between.getFrom());
                p.getLease().setEndTime(between.getTo());
            }
        });
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

    @SetContactUser
    @SetSimpleShop
    @Override
    public OrderVO getOrderVOById(Long id) {
        QOrder e=QOrder.order;

        OrderVO vo=queryOne(e,e.id.eq(id),orderVOConver);

        if(vo==null)
            return null;

        loadData(vo);

        return vo;
    }

    private void loadData(OrderVO vo){
        vo.setProducts(getOrderProductVOListByOrderId(vo.getId()));

        vo.setAddress(getOrderAddressVOById(vo.getAddressId()));

        vo.setLogistics(orderLogisticsDomain.getOrderLogisticsVOById(vo.getLogisticsId()));

        vo.setContracts(orderContractDomain.getOrderContractVOListByOrderId(vo.getId()));
    }

    @SetContactUser
    @SetSimpleShop
    @Override
    public OrderVO getOrderVOByOrderNo(String orderNo) {
        QOrder e=QOrder.order;

        OrderVO vo=queryOne(e,e.orderNo.eq(orderNo),orderVOConver);

        if(vo==null)
            return null;

        loadData(vo);

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

    @SetContactUser
    @SetSimpleShop
    @Override
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {

        QOrder e=QOrder.order;

        Page<OrderVO> page=queryPage(e,getPredicate(request),orderVOConver,pageable);

        getOrderProductVOList(page.getContent());
        getOrderLogisticsVOList(page.getContent());
        getOrderAddressVOList(page.getContent());

        return page;
    }

    private Predicate getPredicate(GetOrderListDTO request){

        QOrder e=QOrder.order;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        if(request.getShopId()!=null){
            p=e.shopId.eq(request.getShopId()).and(p);
        }

        if(request.getHotelId()!=null){
            p=e.hotelId.eq(request.getHotelId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
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
        return p;
    }

    private Predicate getPredicate(GetStatsOrderStatusListDTO request){

        QOrder e=QOrder.order;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        if(request.getShopId()!=null){
            p=e.shopId.eq(request.getShopId()).and(p);
        }

        if(request.getHotelId()!=null){
            p=e.hotelId.eq(request.getHotelId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getBrokerId()!=null){
            p=e.brokerId.eq(request.getBrokerId()).and(p);
        }

        if(request.getPartnerId()!=null){
            p=e.partnerId.eq(request.getPartnerId()).and(p);
        }


        return p;
    }

    @Override
    public List<StatsOrderStatusVO> getStatsOrderStatusVOList(GetStatsOrderStatusListDTO request) {
        QOrder e=QOrder.order;
        Predicate p=getPredicate(request);
        List<Tuple> tuples= QF.select(e.count(),e.totalAmount.sum(),e.status).from(e).where(p).groupBy(e.status).fetch();
        List<StatsOrderStatusVO> list=new ArrayList<>(tuples.size());
        for (Tuple t:tuples){
            StatsOrderStatusVO vo=new StatsOrderStatusVO();
            vo.setStatus(t.get(e.status));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.totalAmount.sum()));
            list.add(vo);
        }
        return list;
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
