package com.outmao.ebs.user.service;

import com.outmao.ebs.user.entity.Online;

public interface OnlineService {

    public Online saveOnline(Long userId, boolean message);

    public Online getOnlineByUserId(Long userId);

}
