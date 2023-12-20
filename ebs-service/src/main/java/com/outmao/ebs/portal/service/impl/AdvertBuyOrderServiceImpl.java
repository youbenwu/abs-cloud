package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.mall.order.dto.CreateSettleDTO;
import com.outmao.ebs.mall.order.dto.CreateSettleProductDTO;
import com.outmao.ebs.mall.order.dto.ToOrderDTO;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.portal.common.constant.AdvertBuyDisplayOrderStatus;
import com.outmao.ebs.portal.common.constant.AdvertBuyOrderStatus;
import com.outmao.ebs.portal.domain.AdvertBuyOrderDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.*;
import com.outmao.ebs.portal.service.AdvertBuyOrderService;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.security.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvertBuyOrderServiceImpl extends BaseService implements AdvertBuyOrderService {

    @Autowired
    private AdvertBuyOrderDomain advertBuyOrderDomain;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private AdvertChannelService advertChannelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SettleService settleService;

    @Autowired
    private ProductService productService;

    @Override
    public AdvertBuyOrder saveAdvertBuyOrder(AdvertBuyOrderDTO request) {
        AdvertBuyOrder order=advertBuyOrderDomain.saveAdvertBuyOrder(request);
        return order;
    }

    @Override
    public AdvertBuyOrder setAdvertBuyOrderStatus(SetAdvertBuyOrderStatusDTO request) {
        AdvertBuyOrder order=advertBuyOrderDomain.setAdvertBuyOrderStatus(request);
        if(order.getStatus()== AdvertBuyOrderStatus.Finish.getStatus()){
            //投放广告
            AdvertBuy buy=new AdvertBuy();

            buy.setAmount(order.getAmount());
            buy.setPrice(order.getPrice());
            advertService.buy(order.getAdvertId(),buy);

            //订单完成，修改广告状态为正常
            SetAdvertStatusDTO statusDTO=new SetAdvertStatusDTO();
            statusDTO.setId(order.getAdvertId());
            statusDTO.setStatus(Status.NORMAL.getStatus());
            advertService.setAdvertStatus(statusDTO);

        }else if(order.getStatus()== AdvertBuyOrderStatus.Cancel.getStatus()){
            //订单取消，修改广告状态
            SetAdvertStatusDTO statusDTO=new SetAdvertStatusDTO();
            statusDTO.setId(order.getAdvertId());
            statusDTO.setStatus(Status.ORDER_CANCEL.getStatus());
            advertService.setAdvertStatus(statusDTO);
        }
        return order;
    }

    @Override
    public AdvertBuyOrder saveAdvertOrder(AdvertOrderDTO request) {

        //保存广告信息
        AdvertDTO advertDTO=new AdvertDTO();
        advertDTO.setUserId(SecurityUtil.currentUserId());
        BeanUtils.copyProperties(request,advertDTO);
        advertDTO.setStatus(Status.ORDER_WAIT.getStatus());
        Advert advert=advertService.saveAdvert(advertDTO);


        //用结算ID去下单
        ToOrderDTO toOrderDTO=new ToOrderDTO();
        toOrderDTO.setSettleId(request.getSettleId());
        ToOrderVO toOrderVO=settleService.buy(toOrderDTO);

        OrderVO order=orderService.getOrderVOByOrderNo(toOrderVO.getOrders().get(0));

        AdvertBuyOrderDTO displayOrderDTO =new AdvertBuyOrderDTO();

        displayOrderDTO.setOrderNo(order.getOrderNo());
        displayOrderDTO.setAmount(order.getTotalAmount());
        displayOrderDTO.setPrice(order.getTotalAmount()/order.getQuantity()/1000);
        displayOrderDTO.setPv(request.getQuantity()*1000);
        displayOrderDTO.setAdvertId(advert.getId());

        AdvertBuyOrder displayOrder=saveAdvertBuyOrder(displayOrderDTO);

        return displayOrder;
    }

    @Override
    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request) {
        AdvertChannel channel=advertChannelService.getAdvertChannelById(request.getChannelId());

        Product product=productService.getProductById(channel.getProductId());


        //0--图文 1--图文视频 2--图文视频链接 3--图文视频二维码 4--图文视频链接二维码 5--图文链接 6--图文二维码
        String[] skunames=new String[]{
                "1000PV图文",
                "1000PV图文视频",
                "1000PV图文视频链接",
                "1000PV图文视频二维码",
                "1000PV图文视频链接二维码",
                "1000PV图文链接",
                "1000PV图文二维码",
        };

        String skuname=skunames[request.getContentType()];

        ProductSku sku=null;

        for(ProductSku u:product.getSkus()){
            if(u.getName().equals(skuname)){
                sku=u;
                break;
            }
        }

        if(sku==null){
            sku=product.getSkus().get(0);
        }

        CreateSettleDTO createSettleDTO=new CreateSettleDTO();
        createSettleDTO.setUserId(SecurityUtil.currentUserId());
        createSettleDTO.setType(product.getType());
        createSettleDTO.setProducts(new ArrayList<>());
        createSettleDTO.getProducts().add(new CreateSettleProductDTO(sku.getId(),request.getQuantity()));

        SettleVO settleVO=settleService.createSettle(createSettleDTO);

        return settleVO;
    }



}
