package com.outmao.ebs.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.DateUtil;
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
import com.outmao.ebs.portal.domain.AdvertBuyDisplayOrderDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertBuyDisplay;
import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.service.AdvertBuyDisplayOrderService;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.security.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class AdvertBuyDisplayOrderServiceImpl extends BaseService implements AdvertBuyDisplayOrderService {


    @Autowired
    private AdvertBuyDisplayOrderDomain advertBuyDisplayOrderDomain;

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

    @Transactional
    @Override
    public AdvertBuyDisplayOrder setAdvertBuyDisplayOrderStatus(SetAdvertBuyDisplayOrderStatusDTO request) {
        AdvertBuyDisplayOrder order=advertBuyDisplayOrderDomain.setAdvertBuyDisplayOrderStatus(request);
        if(order.getStatus()== AdvertBuyDisplayOrderStatus.Up.getStatus()){
            //投放广告
            AdvertBuyDisplay buyDisplay=new AdvertBuyDisplay();
            buyDisplay.setAmount(order.getAmount());
            buyDisplay.setPrice(order.getPrice());
            buyDisplay.setStartTime(order.getStartTime());
            buyDisplay.setEndTime(order.getEndTime());
            buyDisplay.setScreens(order.getScreens());
            advertService.buyDisplay(order.getAdvertId(),buyDisplay);

            //订单完成，修改广告状态为正常
            SetAdvertStatusDTO statusDTO=new SetAdvertStatusDTO();
            statusDTO.setId(order.getAdvertId());
            statusDTO.setStatus(Status.NORMAL.getStatus());
            advertService.setAdvertStatus(statusDTO);

        }else if(order.getStatus()== AdvertBuyDisplayOrderStatus.Cancel.getStatus()){
            //订单取消，修改广告状态
            SetAdvertStatusDTO statusDTO=new SetAdvertStatusDTO();
            statusDTO.setId(order.getAdvertId());
            statusDTO.setStatus(Status.ORDER_CANCEL.getStatus());
            advertService.setAdvertStatus(statusDTO);
        }
        return order;
    }

    @Override
    public AdvertBuyDisplayOrder saveAdvertBuyDisplayOrder(AdvertBuyDisplayOrderDTO request) {
        return advertBuyDisplayOrderDomain.saveAdvertBuyDisplayOrder(request);
    }

    @Transactional
    @Override
    public AdvertBuyDisplayOrder saveAdvertOrder(AdvertOrderDTO request) {

        //保存广告信息
        AdvertDTO advertDTO=new AdvertDTO();
        advertDTO.setUserId(SecurityUtil.currentUserId());
        BeanUtils.copyProperties(request,advertDTO);
        advertDTO.setStatus(Status.ORDER_WAIT.getStatus());
        Advert advert=advertService.saveAdvert(advertDTO);

        AdvertVO vo=advertService.getAdvertVOById(advert.getId());

        String data= JSON.toJSONStringWithDateFormat(vo,"yyyy-MM-dd");


        //用结算ID去下单
        ToOrderDTO toOrderDTO=new ToOrderDTO();
        toOrderDTO.setSettleId(request.getSettleId());
        toOrderDTO.setData(data);
        ToOrderVO toOrderVO=settleService.buy(toOrderDTO);

        OrderVO order=orderService.getOrderVOByOrderNo(toOrderVO.getOrders().get(0));


        AdvertBuyDisplayOrderDTO displayOrderDTO =new AdvertBuyDisplayOrderDTO();

        displayOrderDTO.setOrderNo(order.getOrderNo());
        displayOrderDTO.setAmount(order.getTotalAmount());
        displayOrderDTO.setPrice(order.getTotalAmount()/order.getQuantity()/1000);
        displayOrderDTO.setScreens(request.getQuantity()*1000);
        displayOrderDTO.setAdvertId(advert.getId());
        displayOrderDTO.setStartTime(advert.getStartTime());
        displayOrderDTO.setEndTime(advert.getEndTime());

        AdvertBuyDisplayOrder displayOrder=saveAdvertBuyDisplayOrder(displayOrderDTO);

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

        int days=1;

        if(request.getStartTime()!=null&&request.getEndTime()!=null){
            days= DateUtil.daysBetween(request.getStartTime(),request.getEndTime());
        }

        if(days<7){
            throw new BusinessException("广告投放时间最少为7天");
        }

        int quantity=request.getQuantity()*days;

        CreateSettleDTO createSettleDTO=new CreateSettleDTO();
        createSettleDTO.setUserId(SecurityUtil.currentUserId());
        createSettleDTO.setType(product.getType());
        createSettleDTO.setProducts(new ArrayList<>());
        createSettleDTO.getProducts().add(new CreateSettleProductDTO(sku.getId(),quantity));

        SettleVO settleVO=settleService.createSettle(createSettleDTO);

        return settleVO;

    }




}
