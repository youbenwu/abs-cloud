package com.outmao.ebs.org.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QMenu;
import com.outmao.ebs.org.vo.MenuVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class MenuVOConver implements BeanConver<QMenu, MenuVO> {


    @Override
    public MenuVO fromTuple(Tuple t, QMenu e) {
        MenuVO vo=new MenuVO();
        vo.setId(t.get(e.id));
        vo.setParentId(t.get(e.parent.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setSort(t.get(e.sort));
        vo.setStatus(t.get(e.status));
        vo.setName(t.get(e.name));
        vo.setUrl(t.get(e.url));
        vo.setIcon(t.get(e.icon));
        vo.setPath(t.get(e.path));
        vo.setDescription(t.get(e.description));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMenu e) {
        return new Expression[]{
                e.id,
                e.parent.id,
                e.leaf,
                e.level,
                e.sort,
                e.status,
                e.name,
                e.url,
                e.icon,
                e.path,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
