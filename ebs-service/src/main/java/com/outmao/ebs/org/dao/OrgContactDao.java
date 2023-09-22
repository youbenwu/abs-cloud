package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.OrgContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgContactDao extends JpaRepository<OrgContact,Long> {
}
