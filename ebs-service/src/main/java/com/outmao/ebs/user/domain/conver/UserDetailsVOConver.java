package com.outmao.ebs.user.domain.conver;

import com.outmao.ebs.common.dsl.BeanConver;
import com.outmao.ebs.user.entity.QUser;
import com.outmao.ebs.user.vo.UserDetailsVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class UserDetailsVOConver  implements BeanConver<QUser, UserDetailsVO> {


    @Override
    public UserDetailsVO fromTuple(Tuple t, QUser e) {
        UserDetailsVO vo=new UserDetailsVO();
        vo.setPhone(t.get(e.details.phone));
        vo.setEmail(t.get(e.details.email));
        vo.setRealName(t.get(e.details.realName));
        vo.setEnterpriseId(t.get(e.details.enterpriseId));
        vo.setEnterpriseName(t.get(e.details.enterpriseName));
        vo.setSex(t.get(e.details.sex));
        vo.setBirthday(t.get(e.details.birthday));
        vo.setHometown(t.get(e.details.hometown));
        vo.setAddress(t.get(e.details.address));
        vo.setWeChat(t.get(e.details.weChat));
        vo.setQq(t.get(e.details.qq));
        vo.setCareer(t.get(e.details.career));
        vo.setSchool(t.get(e.details.school));
        vo.setCompany(t.get(e.details.company));
        vo.setJob(t.get(e.details.job));
        vo.setUpdateTime(t.get(e.details.updateTime));
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
        vo.setAge(t.get(e.details.age));
        vo.setHobby(t.get(e.details.hobby));
        return vo;
    }

    @Override
    public Expression<?>[] select(QUser e) {
        return new Expression[]{
                e.details.phone,
                e.details.email,
                e.details.realName,
                e.details.sex,
                e.details.birthday,
                e.details.hometown,
                e.details.address,
                e.details.weChat,
                e.details.qq,
                e.details.career,
                e.details.school,
                e.details.company,
                e.details.job,
                e.details.updateTime,
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
                e.details.age,
                e.details.hobby
        };
    }
}
