package com.outmao.ebs.mall.merchant.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.mall.merchant.entity.QMerchant;
import com.outmao.ebs.mall.merchant.vo.MerchantVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Component;


@Component
public class MerchantVOConver implements BeanConver<QMerchant, MerchantVO> {



    @Override
    public MerchantVO fromTuple(Tuple t, QMerchant e) {

        MerchantVO vo=new MerchantVO();
        vo.setId(t.get(e.id));
        vo.setOrgId(t.get(e.orgId));
        vo.setStatus(t.get(e.status));
        vo.setStatusRemark(t.get(e.statusRemark));
        vo.setUserId(t.get(e.user.id));
        vo.setShopId(t.get(e.shopId));
        vo.setType(t.get(e.type));
        vo.setEnterpriseId(t.get(e.enterpriseId));
        vo.setName(t.get(e.name));
        vo.setIntro(t.get(e.intro));
        //vo.setContact(t.get(e.contact));
        vo.setUrl(t.get(e.url));
        vo.setQrCode(t.get(e.qrCode));
        vo.setCreateTime(t.get(e.createTime));
        vo.setUpdateTime(t.get(e.updateTime));
        return vo;
    }

    @Override
    public Expression<?>[] select(QMerchant e) {
        return new Expression[]{
                e.id,
                e.orgId,
                e.status,
                e.statusRemark,
                e.user.id,
                e.shopId,
                e.type,
                e.enterpriseId,
                e.name,
                e.intro,
                //e.contact,
                e.url,
                e.qrCode,
                e.createTime,
                e.updateTime
        };
    }
}
