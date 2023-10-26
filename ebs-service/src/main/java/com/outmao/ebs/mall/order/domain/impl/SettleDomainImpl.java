package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.order.dao.SettleDao;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.SettleDomain;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.entity.Settle;
import com.outmao.ebs.mall.order.vo.*;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.user.dao.UserAddressDao;
import com.outmao.ebs.user.entity.UserAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class SettleDomainImpl extends BaseDomain implements SettleDomain {

    @Autowired
    private SettleDao settleDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private OrderDomain orderDomain;

    @Transactional
    @Override
    public SettleVO saveSettle(SettleDTO request) {

        SettleVO vo=new SettleVO();

        BeanUtils.copyProperties(request,vo);

        setAddress(vo,request.getAddressId());

        setSettleShopVOList(vo,request.getShops());


        Settle settle=request.getId()==null?null:settleDao.getOne(request.getId());
        if(settle==null){
            settle=new Settle();
            settle.setCreateTime(new Date());
        }
        settle.setUpdateTime(new Date());
        settleDao.save(settle);

        vo.setId(settle.getId());
        String json= JsonUtil.toJson(vo);
        settle.setData(json);



        return vo;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        settleDao.deleteById(id);
    }

    private void setAddress(SettleVO settle, Long addressId){
        if(addressId==null)
            return;
        UserAddress address=userAddressDao.getOne(addressId);
        SettleAddressVO vo=new SettleAddressVO();
        BeanUtils.copyProperties(address,vo);
        settle.setAddress(vo);
    }


    private void setSettleShopVOList(SettleVO settle,List<SettleShopDTO> shops){
        List<SettleShopVO> list=new ArrayList<>(shops.size());
        for(SettleShopDTO dto:shops){
            SettleShopVO vo=new SettleShopVO();
            BeanUtils.copyProperties(dto,vo);

            Shop shop=shopDao.getOne(vo.getShopId());
            vo.setShopName(shop.getTitle());

            List<SettleProductVO> products=getSettleProductVOList(dto.getProducts());
            vo.setProducts(products);

            double amount=0;
            int quantity=0;
            for(SettleProductVO p:products){
                amount+=p.getAmount();
                quantity+=p.getQuantity();
            }
            vo.setAmount(amount);
            vo.setQuantity(quantity);
            vo.setFreight(0);
            vo.setTotalAmount(vo.getFreight()+vo.getAmount());

            list.add(vo);
        }
        settle.setShops(list);

        int quantity=0;
        double freight=0;
        double amount=0;
        double totalAmount=0;

        for(SettleShopVO shop:list){
            quantity+=shop.getQuantity();
            freight+=shop.getFreight();
            amount+=shop.getAmount();
            totalAmount+=shop.getTotalAmount();
        }

        settle.setQuantity(quantity);
        settle.setFreight(freight);
        settle.setAmount(amount);
        settle.setTotalAmount(totalAmount);

    }

    private List<SettleProductVO> getSettleProductVOList(List<SettleProductDTO> products){
        List<SettleProductVO> list=new ArrayList<>(products.size());
        for(SettleProductDTO dto:products){
            SettleProductVO vo=new SettleProductVO();
            BeanUtils.copyProperties(dto,vo);

            ProductSku sku=productSkuDao.getOne(vo.getSkuId());
            vo.setSkuName(sku.getName());
            vo.setPrice(sku.getPrice());
            vo.setAmount(sku.getPrice()*vo.getQuantity());
            vo.setProductId(sku.getProduct().getId());
            vo.setProductTitle(sku.getProduct().getTitle());
            vo.setProductImage(sku.getProduct().getImage());
            if(sku.getProduct().getVolume()!=null){
                vo.setVolume(sku.getProduct().getVolume()*vo.getQuantity());
            }
            if(sku.getProduct().getWeight()!=null){
                vo.setWeight(sku.getProduct().getWeight()*vo.getQuantity());
            }

            list.add(vo);
        }
        return list;
    }

    @Transactional
    @Override
    public SettleVO createSettle(CreateSettleDTO request) {
        SettleDTO dto=new SettleDTO();
        BeanUtils.copyProperties(request,dto);

        Map<Long,SettleShopDTO> shops=new HashMap<>();


        for(CreateSettleProductDTO t:request.getProducts()){
            SettleProductDTO p=new SettleProductDTO();
            BeanUtils.copyProperties(t,p);
            ProductSku sku=productSkuDao.getOne(p.getSkuId());
            SettleShopDTO shop=shops.get(sku.getProduct().getShopId());
            if(shop==null){
                shop=new SettleShopDTO();
                shop.setShopId(sku.getProduct().getShopId());
                shop.setProducts(new ArrayList<>());
                shops.put(shop.getShopId(),shop);
            }
            shop.getProducts().add(p);
        }

        dto.setShops(shops.values().stream().collect(Collectors.toList()));
        return saveSettle(dto);
    }

    @Transactional
    @Override
    public SettleVO updateSettle(UpdateSettleDTO request) {

        SettleVO vo=getSettleById(request.getId());

        SettleDTO dto=getSettleDTO(vo);

        if(request.getAddressId()!=null){
            dto.setAddressId(request.getAddressId());
        }

        if(StringUtil.isNotEmpty(request.getPayChannel())){
            dto.setPayChannel(request.getPayChannel());
        }

        //BeanUtils.copyProperties(request,dto);

        return saveSettle(dto);
    }

    @Transactional
    @Override
    public SettleVO resettle(Long id) {
        UpdateSettleDTO dto=new UpdateSettleDTO();
        dto.setId(id);
        return updateSettle(dto);
    }

    @Override
    public SettleVO checkSettleChanged(Long id) {

        SettleVO old=getSettleById(id);

        SettleDTO dto=getSettleDTO(old);

        SettleVO vo=saveSettle(dto);

        if(old.getTotalAmount()!=vo.getTotalAmount()){
            throw new BusinessException("订单金额改变,请重新提交");
        }

        return vo;
    }

    private SettleDTO getSettleDTO(SettleVO vo){
        SettleDTO dto=new SettleDTO();
        BeanUtils.copyProperties(vo,dto,"shops");
        if(vo.getAddress()!=null){
            dto.setAddressId(vo.getAddress().getId());
        }
        dto.setShops(new ArrayList<>());
        for(SettleShopVO t:vo.getShops()){
            SettleShopDTO s=new SettleShopDTO();
            BeanUtils.copyProperties(t,s,"products");
            s.setProducts(new ArrayList<>());
            for(SettleProductVO t2:t.getProducts()){
                SettleProductDTO p=new SettleProductDTO();
                BeanUtils.copyProperties(t2,p);
                s.getProducts().add(p);
            }
            dto.getShops().add(s);
        }

        return dto;
    }


    @Override
    public SettleVO getSettleById(Long id) {

        Settle settle=settleDao.getOne(id);

        System.out.println(settle.getData());

        SettleVO vo=JsonUtil.fromJson(settle.getData(),SettleVO.class);

        return vo;
    }


    @Transactional
    @Override
    public ToOrderVO buy(ToOrderDTO request) {

        SettleVO settle=checkSettleChanged(request.getSettleId());

        List<Order> orders=new ArrayList<>(settle.getShops().size());
        for (SettleShopVO shop:settle.getShops()){
            OrderDTO dto=getOrderDTO(settle,shop,request.getShopByShopId(shop.getShopId()));
            Order order=orderDomain.saveOrder(dto);
            orders.add(order);
        }
        ToOrderVO vo=new ToOrderVO();
        vo.setOrders(orders.stream().map(t->t.getId()).collect(Collectors.toList()));
        vo.setPayChannel(settle.getPayChannel());
        vo.setTotalAmount(settle.getTotalAmount());

        return vo;

    }


    private OrderDTO getOrderDTO(SettleVO settle,SettleShopVO shop,ToOrderShopDTO request){

        OrderDTO dto=new OrderDTO();

        BeanUtils.copyProperties(settle,dto,"address");

        if(settle.getAddress()!=null){
            OrderAddressDTO address=new OrderAddressDTO();
            BeanUtils.copyProperties(settle.getAddress(),address);
            dto.setAddress(address);
        }

        BeanUtils.copyProperties(shop,dto,"products");
        dto.setProducts(new ArrayList<>());
        for(SettleProductVO vo:shop.getProducts()){
            OrderProductDTO p=new OrderProductDTO();
            BeanUtils.copyProperties(vo,p);
            dto.getProducts().add(p);
        }


        if(request!=null){
            BeanUtils.copyProperties(request,dto,"products");
            dto.getProducts().forEach(p->{
                ToOrderProductDTO t=request.getProductBySkuId(p.getSkuId());
                if(t!=null){
                    BeanUtils.copyProperties(t,p);
                }
            });
        }

        return dto;
    }



}
