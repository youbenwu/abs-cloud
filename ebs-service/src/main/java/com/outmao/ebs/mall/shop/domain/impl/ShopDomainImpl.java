package com.outmao.ebs.mall.shop.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.bbs.common.annotation.BindingSubjectId;
import com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd;
import com.outmao.ebs.bbs.common.annotation.SubjectItemFilter;
import com.outmao.ebs.mall.merchant.dao.MerchantDao;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.domain.ShopDomain;
import com.outmao.ebs.mall.shop.domain.conver.ShopVOConver;
import com.outmao.ebs.mall.shop.domain.conver.SimpleShopVOConver;
import com.outmao.ebs.mall.shop.dto.GetShopListDTO;
import com.outmao.ebs.mall.shop.dto.SetShopStatusDTO;
import com.outmao.ebs.mall.shop.dto.ShopDTO;
import com.outmao.ebs.mall.shop.entity.QShop;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import com.outmao.ebs.mall.shop.vo.SimpleShopVO;
import com.outmao.ebs.qrCode.dto.ActivateQrCodeDTO;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.entity.QrCode;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class ShopDomainImpl extends BaseDomain implements ShopDomain {


    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private QrCodeService qrcodeService;

    private ShopVOConver shopVOConver=new ShopVOConver();

    private SimpleShopVOConver simpleShopVOConver=new SimpleShopVOConver();


    @BindingSubjectId
    @Transactional
    @Override
    public Shop saveShop(ShopDTO request) {
        Shop shop=request.getId()==null?null:shopDao.getOne(request.getId());

        if(shop==null){
            Merchant merchant=merchantDao.getOne(request.getMerchantId());
            shop=new Shop();
            shop.setCreateTime(new Date());
            shop.setOrgId(merchant.getOrgId());
            shop.setMerchantId(merchant.getId());
            shop.setUserId(merchant.getUser().getId());
            shop.setCreateTime(new Date());
        }

        security.hasPermission(shop.getOrgId(),shop.getUserId());

        shop.setUpdateTime(new Date());

        BeanUtils.copyProperties(request,shop);

        Long hasId=shopDao.findIdByTitle(request.getTitle());
        if(hasId!=null&&!hasId.equals(shop.getId())){
            throw new BusinessException("店名已经被使用");
        }

        shopDao.save(shop);

        if(shop.getUrl()==null){
            String url=config.getBaseUrl()+"/shop?id="+shop.getId();
            QrCode qrCode=qrcodeService.activateQrCode(new ActivateQrCodeDTO(url));
            shop.setUrl(url);
            shop.setQrCode(qrCode.getPath());
        }

        return shop;
    }

    @Transactional
    @Override
    public Shop setShopStatus(SetShopStatusDTO request) {
        Shop shop=shopDao.getOne(request.getId());
        security.hasPermission(shop.getOrgId(),shop.getUserId());
        shop.setStatus(request.getStatus());
        shop.setStatusRemark(request.getStatusRemark());
        shopDao.save(shop);
        return shop;
    }

    @Override
    public Shop getShopByOrgId(Long orgId) {
        return shopDao.findByOrgId(orgId);
    }

    @SubjectBrowsesAdd
    @SubjectItemFilter
    @Override
    public ShopVO getShopVOById(Long id) {
        QShop e=QShop.shop;

        return queryOne(e,e.id.eq(id),shopVOConver);
    }

    @SubjectBrowsesAdd
    @SubjectItemFilter
    @Override
    public ShopVO getShopVOByUserId(Long userId) {
        QShop e=QShop.shop;

        return queryOne(e,e.userId.eq(userId),shopVOConver);
    }

    @SubjectItemFilter
    @Override
    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable) {
        QShop e=QShop.shop;

        Predicate p=null;

        if(request.getKeyword()!=null){
            p=e.title.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<ShopVO> page=queryPage(e,p,shopVOConver,pageable);

        return page;
    }

    @Override
    public List<SimpleShopVO> getSimpleShopVOListByIdIn(Collection<Long> idIn) {
        QShop e=QShop.shop;
        return queryList(e,e.id.in(idIn),simpleShopVOConver);
    }



}

