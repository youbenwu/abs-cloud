package com.outmao.ebs.wallet.domain;

import com.outmao.ebs.wallet.dto.GetAssetListDTO;
import com.outmao.ebs.wallet.entity.Asset;
import com.outmao.ebs.wallet.vo.AssetVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface AssetDomain {


    public List<Asset> saveWalletAssets(Long walletId);

    public List<AssetVO> getAssetVOListByWalletId(Long walletId);

    public List<AssetVO> getAssetVOListByWalletIdIn(Collection<Long> walletIdIn);

    public Page<AssetVO> getAssetVOPage(GetAssetListDTO request, Pageable pageable);


}
