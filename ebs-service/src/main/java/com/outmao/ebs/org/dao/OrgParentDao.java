package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.OrgParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgParentDao extends JpaRepository<OrgParent,Long> {

    public OrgParent findByOrgIdAndParentId(Long orgId,Long parentId);

    @Query("select o.parentId from OrgParent o where o.orgId=?1")
    public List<Long> findAllParentIdByOrgId(Long orgId);

    public void deleteAllByOrgId(Long orgId);

}
