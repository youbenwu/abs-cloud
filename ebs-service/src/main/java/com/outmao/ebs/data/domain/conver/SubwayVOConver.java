package com.outmao.ebs.data.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.data.entity.QSubway;
import com.outmao.ebs.data.vo.SubwayVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class SubwayVOConver implements BeanConver<QSubway, SubwayVO> {
    @Override
    public SubwayVO fromTuple(Tuple t, QSubway e) {
        SubwayVO vo=new SubwayVO();

        vo.setId(t.get(e.id));
        vo.setLeaf(t.get(e.leaf));
        vo.setLevel(t.get(e.level));
        vo.setParentId(t.get(e.parent.id));
        vo.setLongitude(t.get(e.longitude));
        vo.setLatitude(t.get(e.latitude));
        vo.setName(t.get(e.name));
        vo.setCountry(t.get(e.country));
        vo.setProvince(t.get(e.province));
        vo.setCity(t.get(e.city));
        vo.setCityCode(t.get(e.cityCode));
        vo.setDistrict(t.get(e.district));
        vo.setTownship(t.get(e.township));
        vo.setTownCode(t.get(e.townCode));

        return vo;
    }

    @Override
    public Expression<?>[] select(QSubway e) {
        return new Expression[]{
                e.id,
                e.leaf,
                e.level,
                e.parent.id,
                e.longitude,
                e.latitude,
                e.name,
                e.country,
                e.province,
                e.city,
                e.cityCode,
                e.district,
                e.township,
                e.townCode,
                e.createTime
        };
    }
}
