package com.outmao.ebs.user.common.data;


import com.outmao.ebs.user.vo.ContactUserVO;
import com.outmao.ebs.user.vo.SimpleUserVO;

public interface ContactUserSetter {

    Long getUserId();

    void setUser(ContactUserVO user);

}
