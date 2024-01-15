package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.OrgTypeDTO;
import com.outmao.ebs.org.entity.OrgType;
import com.outmao.ebs.org.vo.OrgTypeVO;

import java.util.Collection;
import java.util.List;

public interface OrgTypeDomain {


    public OrgType saveOrgType(OrgTypeDTO request);

    public void deleteOrgTypeById(Long id);

    public void deleteOrgTypeByOrgId(Long orgId);

    public OrgType getOrgTypeByTargetId(Long targetId);

    public List<OrgTypeVO> getOrgTypeVOListByOrgIdIn(Collection<Long> orgIdIn);


}
