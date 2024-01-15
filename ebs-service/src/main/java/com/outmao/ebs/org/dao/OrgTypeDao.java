package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.OrgType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgTypeDao extends JpaRepository<OrgType,Long> {

    public boolean existsByOrgIdAndType(Long orgId,int type);

    public OrgType findByOrgIdAndType(Long orgId,int type);

    public OrgType findByTargetId(Long targetId);

    public void deleteAllByOrgId(Long orgId);

}
