package com.outmao.ebs.user.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.UserVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class UserVOConver implements BeanConver<QUser, UserVO> {


    @Override
    public UserVO fromTuple(Tuple t, QUser e) {
        UserVO vo=new UserVO();
        vo.setId(t.get(e.id));
        vo.setWalletId(t.get(e.walletId));
        vo.setSubjectId(t.get(e.subjectId));
        vo.setStatus(t.get(e.status));
        vo.setType(t.get(e.type));
        vo.setUsername(t.get(e.username));
        vo.setNickname(t.get(e.nickname));
        vo.setAvatar(t.get(e.avatar));
        vo.setArea(t.get(e.area));
        vo.setAreaCode(t.get(e.areaCode));
        vo.setVerified(t.get(e.verified));
        vo.setEntVerified(t.get(e.entVerified));
        vo.setCredits(t.get(e.credits));
        vo.setLevel(t.get(e.level));
        vo.setVip(t.get(e.vip));
        vo.setQrCode(t.get(e.qrCode));
        vo.setUrl(t.get(e.url));
        vo.setImei(t.get(e.imei));
        vo.setOnline(t.get(e.online));
        vo.setRegisterTime(t.get(e.registerTime));
        vo.setLoginTime(t.get(e.loginTime));

        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression[]{
                e.id,
                e.walletId,
                e.subjectId,
                e.status,
                e.type,
                e.username,
                e.nickname,
                e.avatar,
                e.area,
                e.areaCode,
                e.imei,
                e.verified,
                e.entVerified,
                e.credits,
                e.level,
                e.vip,
                e.url,
                e.qrCode,
                e.online,
                e.registerTime,
                e.loginTime,
        };
    }
}
