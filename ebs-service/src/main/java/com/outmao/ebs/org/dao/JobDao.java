package com.outmao.ebs.org.dao;



import com.outmao.ebs.org.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobDao extends JpaRepository<Job,Long> {

    public List<Job> findAllByOrgId(Long orgId);


    @Modifying
    @Query("update Job e set e.members=e.members+?2 where e.id=?1")
    public void membersAdd(Long id, int add);

}
