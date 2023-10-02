package com.outmao.ebs.org.common.data;

import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.common.vo.Itemable;

public interface BindingOrg extends Itemable {

    public Long getUserId();

    public Long getParentOrgId();

    public Long getOrgId();

    public void setOrgId(Long orgId);

    public Contact getContact();


}
