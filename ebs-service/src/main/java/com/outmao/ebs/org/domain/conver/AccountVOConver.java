package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QAccount;
import com.outmao.ebs.org.vo.AccountVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AccountVOConver implements BeanConver<QAccount, AccountVO> {


    @Override
    public AccountVO fromTuple(Tuple t, QAccount e) {
        AccountVO vo=new AccountVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.org.id));
        vo.setUserId(t.get(e.user.id));
        vo.setName(t.get(e.name));
        vo.setPhone(t.get(e.phone));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAccount e) {
        return new Expression[]{
                e.id,
                e.org.id,
                e.user.id,
                e.name,
                e.phone,
                e.updateTime,
                e.createTime,
        };
    }
}
