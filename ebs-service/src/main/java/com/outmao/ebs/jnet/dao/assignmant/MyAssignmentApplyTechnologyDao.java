package com.outmao.ebs.jnet.dao.assignmant;

import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentApplyTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyAssignmentApplyTechnologyDao extends JpaRepository<MyAssignmentApplyTechnology, Long>{
	
	@Modifying
	@Query(nativeQuery = true,
			value = "DELETE aat FROM z_my_assignment_apply_technology aat WHERE aat.assignment_apply_id = ?")
	int deleteByAssignmentId(long assignmentId);

	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment_apply_technology aat WHERE aat.assignment_apply_id IN (:applyIds) ORDER BY aat.create_time DESC")
	List<MyAssignmentApplyTechnology> findByAssignmentApplyIds(@Param("applyIds") List<Long> applyIds);
}
