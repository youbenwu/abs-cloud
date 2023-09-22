package com.outmao.ebs.wallet.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.wallet.dao.AssetDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.AssetDomain;
import com.outmao.ebs.wallet.domain.CurrencyDomain;
import com.outmao.ebs.wallet.domain.conver.AssetVOConver;
import com.outmao.ebs.wallet.dto.GetAssetListDTO;
import com.outmao.ebs.wallet.entity.Asset;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.QAsset;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class AssetDomainImpl extends BaseDomain implements AssetDomain {


    @Autowired
    private AssetDao assetDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CurrencyDomain currencyDomain;

    @Autowired
    private AssetVOConver assetVOConver;



    @Transactional
    @Override
    public List<Asset> saveWalletAssets(Long walletId) {

        Wallet wallet=walletDao.getOne(walletId);
        List<Asset> assets=wallet.getAssets();
        if(assets==null){
            assets=new ArrayList<>();
        }
        Map<String,Asset> assetMap=assets.stream().collect(Collectors.toMap(t->t.getCurrency().getId(), t->t));

        List<Currency> currencies= currencyDomain.getCurrencyList();

        for(Currency t:currencies){
            if(!assetMap.containsKey(t.getId())){
                Asset a=new Asset();
                a.setUser(wallet.getUser());
                a.setWallet(wallet);
                a.setCurrency(t);
                a.setCreateTime(new Date());
                a.setUpdateTime(new Date());
                assetDao.save(a);
                assets.add(a);
            }
        }

        return assets;
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
        if(request.getCurrencyId()!=null&&request.getCurrencyId().length()>0){
            p=e.currency.id.eq(request.getCurrencyId());
        }
        return queryPage(e,p,assetVOConver,pageable);
    }


}
