package com.outmao.ebs.wallet.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.wallet.entity.QWallet;
import com.outmao.ebs.wallet.vo.WalletVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class WalletVOConver implements BeanConver<QWallet, WalletVO> {
    @Override
    public WalletVO fromTuple(Tuple t, QWallet e) {
        WalletVO vo=new WalletVO();
        vo.setId(t.get(e.id));
        vo.setUserId(t.get(e.user.id));
        vo.setType(t.get(e.type));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setBankAccountId(t.get(e.bankAccountId));
        vo.setAccount(t.get(e.account));
        vo.setRealName(t.get(e.realName));
        vo.setPassword(t.get(e.password));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QWallet e) {
        return new Expression[]{
                e.id,
                e.user.id,
                e.type,
                e.status,
                e.statusRemark,
                e.bankAccountId,
                e.account,
                e.realName,
                e.password,
                e.createTime,
                e.updateTime,
        };
    }
}
