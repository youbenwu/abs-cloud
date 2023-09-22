package com.outmao.ebs.bbs.common.data;


import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.common.vo.Itemable;
import com.outmao.ebs.user.entity.User;

public interface BindingSubject extends Itemable {

    public User getUser();

    public Subject getSubject();

    public void setSubject(Subject subject);


}
