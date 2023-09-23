package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.entity.Online;

public interface OnlineDomain {

    public Online saveOnline(Long userId,boolean message);

    public Online getOnlineByUserId(Long userId);


}
