package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.common.util.ArrayUtil;
import com.outmao.ebs.org.entity.QRoleMenu;
import com.outmao.ebs.org.vo.RoleMenuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class RoleMenuVOConver implements BeanConver<QRoleMenu, RoleMenuVO> {


    private MenuVOConver menuVOConver=new MenuVOConver();

    @Override
    public RoleMenuVO fromTuple(Tuple t, QRoleMenu e) {
        RoleMenuVO vo=new RoleMenuVO();
        vo.setId(t.get(e.id));
        vo.setRoleId(t.get(e.role.id));
        vo.setCreateTime(t.get(e.createTime));
        vo.setMenuId(t.get(e.menu.id));
        vo.setMenu(menuVOConver.fromTuple(t,e.menu));
        return vo;
    }

    @Override
    public Expression<?>[] select(QRoleMenu e) {
        return ArrayUtil.merge(new Expression<?>[][]{
                new Expression<?>[]{
                        e.id,
                        e.role.id,
                        e.createTime
                },
                menuVOConver.select(e.menu)
        });
    }
}
