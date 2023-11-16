package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.entity.AdvertOrder;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AdvertServiceImpl extends BaseService implements AdvertService {

    @Autowired
    private AdvertDomain advertDomain;

    @Autowired
    private AdvertChannelService advertChannelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SettleService settleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PayService payService;


    @Override
    public Advert saveAdvert(AdvertDTO request) {
        return advertDomain.saveAdvert(request);
    }

    @Override
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        return advertDomain.setAdvertStatus(request);
    }

    @Override
    public void deleteAdvertById(Long id) {

        advertDomain.deleteAdvertById(id);
    }

    @Override
    public Advert pv(Long id) {
        return advertDomain.pv(id);
    }

    @Override
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        if(!StringUtils.isEmpty(request.getChannelCode())){
          AdvertChannel channel= advertChannelService.getAdvertChannelByCode(request.getChannelCode());
          if(channel!=null){
              request.setChannelId(channel.getId());
          }else{
              //throw new BusinessException("广告频道不存在");

              return new PageImpl(new ArrayList<>(),pageable,0);
          }
        }
        return advertDomain.getAdvertPage(request,pageable);
    }


    @Override
    public List<Advert> getAdvertList(String channelCode, int size) {
        GetAdvertListDTO dto=new GetAdvertListDTO();
        dto.setChannelCode(channelCode);
        dto.setStatus(1);
        Page<Advert> page=getAdvertPage(dto, PageRequest.of(0,size, Sort.by(Sort.Direction.ASC,"sort")));
        return page.getContent();
    }


    @Transactional
    @Override
    public AdvertOrder saveAdvertOrder(AdvertOrderDTO request) {

        //用结算ID去下单
        ToOrderDTO toOrderDTO=new ToOrderDTO();
        toOrderDTO.setSettleId(request.getSettleId());
        ToOrderVO toOrderVO=settleService.buy(toOrderDTO);
        OrderVO order=orderService.getOrderVOByOrderNo(toOrderVO.getOrders().get(0));


        OrderPayPrepareDTO payPrepare=new OrderPayPrepareDTO();
        payPrepare.setOrderNo(order.getOrderNo());
        payPrepare.setPayChannel(PayChannel.WxPay.name());
        payPrepare.setCurrency("RMB");
        orderService.payPrepare(payPrepare);

        AdvertDTO advertDTO=new AdvertDTO();
        advertDTO.setUserId(order.getUserId());
        BeanUtils.copyProperties(request,advertDTO);
        advertDTO.setStatus(2);

        Advert advert=saveAdvert(advertDTO);


        AdvertOrder advertOrder=new AdvertOrder();
        advertOrder.setOrderNo(order.getOrderNo());
        advertOrder.setAdvertId(advert.getId());
        advertOrder.setPv(order.getQuantity()*1000);
        advertOrder.setAmount(order.getTotalAmount());

        advertDomain.saveAdvertOrder(advertOrder);

        return advertOrder;
    }


    @Override
    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request) {
        AdvertChannel channel=advertChannelService.getAdvertChannelById(request.getChannelId());
        Product product=productService.getProductById(channel.getProductId());
        ProductSku sku=product.getSkus().get(0);

        CreateSettleDTO createSettleDTO=new CreateSettleDTO();
        createSettleDTO.setUserId(SecurityUtil.currentUserId());
        createSettleDTO.setType(product.getType());
        createSettleDTO.setProducts(new ArrayList<>());
        createSettleDTO.getProducts().add(new CreateSettleProductDTO(sku.getId(),request.getQuantity()));

        SettleVO settleVO=settleService.createSettle(createSettleDTO);
        return settleVO;
    }

    @Transactional
    @Override
    public AdvertOrder setAdvertOrderStatus(SetAdvertOrderStatusDTO request) {
        AdvertOrder advertOrder=advertDomain.setAdvertOrderStatus(request);
        if(advertOrder.getStatus()== OrderStatus.SUCCESSED.getStatus()){
           advertDomain.buyPv(advertOrder.getAdvertId(),advertOrder.getPv(),advertOrder.getAmount());
        }
        advertOrder.setStatus(OrderStatus.FINISHED.getStatus());
        return advertOrder;
    }


    //每30分钟一次
    @Scheduled(cron = "0 0/30 * * * *")
    //打乱广告顺序
    public void advert_sort(){

        List<Advert> list=advertDomain.getAdvertList();

        Collections.shuffle(list);

        for (int i=0;i<list.size();i++){
            Advert advert=list.get(i);
            advertDomain.setAdvertSort(advert.getId(),i);
        }

    }


}
