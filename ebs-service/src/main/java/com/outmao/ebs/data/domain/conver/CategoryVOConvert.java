package com.outmao.ebs.data.domain.conver;


import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QCategory;
import com.outmao.ebs.data.vo.CategoryVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class CategoryVOConvert implements BeanConver<QCategory, CategoryVO> {


    @Override
    public CategoryVO fromTuple(Tuple t, QCategory e) {

        CategoryVO vo=new CategoryVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setType(t.get(e.type));
        vo.setTargetId(t.get(e.targetId));
        vo.setParentId(t.get(e.parent.id));
        vo.setLevel(t.get(e.level));
        vo.setLeaf(t.get(e.leaf));
        vo.setSort(t.get(e.sort));
        vo.setStatus(t.get(e.status));
        vo.setImage(t.get(e.image));
        vo.setTitle(t.get(e.title));
        vo.setLetter(t.get(e.letter));
        vo.setDescription(t.get(e.description));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QCategory e) {
        return new Expression[]{
                e.type,
                e.targetId,
                e.id,
                e.orgId,
                e.parent.id,
                e.level,
                e.leaf,
                e.sort,
                e.status,
                e.image,
                e.title,
                e.letter,
                e.description,
                e.createTime,
                e.updateTime
        };
    }
}
