package com.outmao.ebs.mall.distribution.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.distribution.entity.QQyDistributionConfig;
import com.outmao.ebs.mall.distribution.vo.QyDistributionConfigVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class QyDistributionConfigVOConver implements BeanConver<QQyDistributionConfig, QyDistributionConfigVO> {


    @Override
    public QyDistributionConfigVO fromTuple(Tuple t, QQyDistributionConfig e) {
        QyDistributionConfigVO vo=new QyDistributionConfigVO();
        vo.setId(t.get(e.id));
        vo.setStatus(t.get(e.status));
        vo.setLevelA(t.get(e.levelA));
        vo.setLevelB(t.get(e.levelB));
        vo.setLevelC(t.get(e.levelC));
        vo.setLevelD(t.get(e.levelD));

        return vo;
    }

    @Override
    public Expression<?>[] select(QQyDistributionConfig e) {
        return new Expression[]{
                e.id,
                e.status,
                e.levelA,
                e.levelB,
                e.levelC,
                e.levelD,
                e.createTime,
                e.updateTime,
        };
    }
}
