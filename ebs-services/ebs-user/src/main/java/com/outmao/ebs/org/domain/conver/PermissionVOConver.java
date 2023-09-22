package com.outmao.ebs.org.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.org.entity.QPermission;
import com.outmao.ebs.org.vo.PermissionVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class PermissionVOConver implements BeanConver<QPermission, PermissionVO> {


    @Override
    public PermissionVO fromTuple(Tuple t, QPermission e) {
        PermissionVO vo=new PermissionVO();
        vo.setDescription(t.get(e.description));
        vo.setId(t.get(e.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setName(t.get(e.name));
        vo.setUrl(t.get(e.url));
        vo.setSort(t.get(e.sort));
        vo.setParentId(t.get(e.parent.id));
        vo.setTitle(t.get(e.title));
        return vo;
    }

    @Override
    public Expression<?>[] select(QPermission e) {
        return new Expression[]{
                e.description,
                e.id,
                e.leaf,
                e.level,
                e.name,
                e.parent.id,
                e.title,
                e.url,
                e.sort,
        };
    }
}
