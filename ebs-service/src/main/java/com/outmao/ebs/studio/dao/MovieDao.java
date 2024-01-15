package com.outmao.ebs.studio.dao;

import com.outmao.ebs.studio.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MovieDao extends JpaRepository<Movie,Long> {


    public void deleteAllByOrgId(Long orgId);

    @Query("select e.productId from Movie e where e.orgId=?1")
    public Collection<Long> findAllProducttIdByOrgId(Long orgId);

}
