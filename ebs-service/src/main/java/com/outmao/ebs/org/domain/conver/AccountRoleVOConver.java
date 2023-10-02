package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QAccountRole;
import com.outmao.ebs.org.vo.AccountRoleVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class AccountRoleVOConver implements BeanConver<QAccountRole, AccountRoleVO> {

    private RoleVOConver roleVOConver=new RoleVOConver();


    @Override
    public AccountRoleVO fromTuple(Tuple t, QAccountRole e) {
        AccountRoleVO vo=new AccountRoleVO();
        vo.setId(t.get(e.id));
        vo.setAccountId(t.get(e.account.id));
        vo.setRoleId(t.get(e.role.id));
        vo.setCreateTime(t.get(e.createTime));
        vo.setRole(roleVOConver.fromTuple(t,e.role));
        return vo;
    }

    @Override
    public Expression<?>[] select(QAccountRole e) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression<?>[]{
                        e.id,
                        e.account.id,
                        e.role.id,
                        e.createTime,
                },
                roleVOConver.select(e.role),
        });
    }


}
