package com.outmao.ebs.data.dao.enterprise;

import com.outmao.ebs.data.entity.enterprise.EnterpriseBrandInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EnterpriseBrandInformationDao extends JpaRepository<EnterpriseBrandInformation,Long> {

    @Query("select e from EnterpriseBrandInformation e where e.enterprise.id in ?1")
    public List<EnterpriseBrandInformation> findAllByEnterpriseIdIn(Collection<Long> idIn);

}
