package com.outmao.ebs.mall.store.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.store.entity.QStoreMember;
import com.outmao.ebs.mall.store.vo.StoreMemberVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class StoreMemberVOConver implements BeanConver<QStoreMember, StoreMemberVO> {
    @Override
    public StoreMemberVO fromTuple(Tuple t, QStoreMember e) {
        StoreMemberVO vo=new StoreMemberVO();
        vo.setId(t.get(e.id));
        vo.setStoreId(t.get(e.store.id));
        vo.setUserId(t.get(e.user.id));
        vo.setName(t.get(e.name));
        vo.setAvatar(t.get(e.avatar));
        vo.setPhone(t.get(e.phone));
        vo.setEmail(t.get(e.email));
        vo.setJob(t.get(e.job));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QStoreMember e) {
        return new Expression[]{
                e.id,
                e.store.id,
                e.user.id,
                e.name,
                e.avatar,
                e.phone,
                e.email,
                e.job,
                e.createTime,
                e.updateTime
        };
    }
}
