package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.enterprise.EnterpriseAccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EnterpriseAccountInformationDao extends JpaRepository<EnterpriseAccountInformation,Long> {

    @Query("select e from EnterpriseAccountInformation e where e.enterprise.id in ?1")
    public List<EnterpriseAccountInformation> findAllByEnterpriseIdIn(Collection<Long> idIn);

    public void deleteAllByEnterpriseId(Long enterpriseId);

}
