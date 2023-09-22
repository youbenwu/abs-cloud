package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QAsset;
import com.outmao.ebs.wallet.vo.AssetVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;

@Component
public class AssetVOConver implements BeanConver<QAsset, AssetVO> {
    @Override
    public AssetVO fromTuple(Tuple t, QAsset e) {
        AssetVO vo=new AssetVO();
        vo.setId(t.get(e.id));
        vo.setWalletId(t.get(e.wallet.id));
        vo.setUserId(t.get(e.user.id));
        vo.setCurrencyId(t.get(e.currency.id));
        vo.setAmount(t.get(e.amount));
        vo.setBalance(t.get(e.balance));
        vo.setFrozen(t.get(e.frozen));
        vo.setAdvance(t.get(e.advance));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAsset e) {
        return new Expression[]{
                e.id,
                e.wallet.id,
                e.user.id,
                e.currency.id,
                e.amount,
                e.balance,
                e.frozen,
                e.advance,
                e.createTime,
                e.updateTime,
        };
    }
}
