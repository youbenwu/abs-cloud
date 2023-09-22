package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleDao extends JpaRepository<Role, Long> {

    public Role findByOrgIdAndValue(Long orgId, String value);

}
