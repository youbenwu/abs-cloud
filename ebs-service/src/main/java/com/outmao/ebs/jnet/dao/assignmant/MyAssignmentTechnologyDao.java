package com.outmao.ebs.jnet.dao.assignmant;

import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyAssignmentTechnologyDao extends JpaRepository<MyAssignmentTechnology, Long>{
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE mat FROM z_my_assignment_technology mat WHERE mat.assignment_id = ?")
    int deleteByAssignmentId(long assignmentId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM z_my_assignment_technology mat WHERE mat.assignment_id IN (:applyIds) ORDER BY mat.create_time DESC")
    List<MyAssignmentTechnology> findByAssignmentIds(@Param("applyIds") List<Long> applyIds);
}
