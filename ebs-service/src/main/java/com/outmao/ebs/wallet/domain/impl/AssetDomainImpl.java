package com.outmao.ebs.wallet.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.wallet.dao.AssetDao;
import com.outmao.ebs.wallet.dao.CurrencyDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.AssetDomain;
import com.outmao.ebs.wallet.domain.conver.AssetVOConver;
import com.outmao.ebs.wallet.dto.AssetDTO;
import com.outmao.ebs.wallet.dto.GetAssetListDTO;
import com.outmao.ebs.wallet.entity.Asset;
import com.outmao.ebs.wallet.entity.QAsset;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;


@Component
public class AssetDomainImpl extends BaseDomain implements AssetDomain {


    @Autowired
    private AssetDao assetDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CurrencyDao currencyDao;


    @Autowired
    private AssetVOConver assetVOConver;



    @Transactional
    @Override
    public Asset saveAsset(AssetDTO request) {
        Asset a=new Asset();
        a.setUser(userDao.getOne(request.getUserId()));
        a.setWallet(walletDao.getOne(request.getWalletId()));
        a.setCurrency(currencyDao.getOne(request.getCurrencyId()));
        a.setCreateTime(new Date());
        a.setUpdateTime(new Date());
        assetDao.save(a);
        return a;
    }

    @Override
    public List<AssetVO> getAssetVOListByWalletId(Long walletId) {
        QAsset e=QAsset.asset;
        List<AssetVO> list=queryList(e,e.wallet.id.eq(walletId),assetVOConver);
        return list;
    }

    @Override
    public List<AssetVO> getAssetVOListByWalletIdIn(Collection<Long> walletIdIn) {
        QAsset e=QAsset.asset;
        List<AssetVO> list=queryList(e,e.wallet.id.in(walletIdIn),assetVOConver);
        return list;
    }

    @Override
    public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable) {
        QAsset e=QAsset.asset;
        Predicate p=null;
        if(!StringUtils.isEmpty(request.getCurrencyId())){
            p=e.currency.id.eq(request.getCurrencyId());
        }
        return queryPage(e,p,assetVOConver,pageable);
    }


}
