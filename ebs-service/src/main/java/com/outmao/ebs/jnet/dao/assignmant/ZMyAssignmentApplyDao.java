package com.outmao.ebs.jnet.dao.assignmant;

import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignmentApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZMyAssignmentApplyDao extends JpaRepository<ZMyAssignmentApply, Long>{
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment_apply aa WHERE aa.user_id IN (:userIds) ORDER BY aa.update_time DESC")
	List<ZMyAssignmentApply> findByUserId(@Param("userIds") List<Long> userIds);

	@Modifying
	@Query(nativeQuery = true,
			value = "UPDATE z_my_assignment_apply aa SET aa.selected = :selected WHERE aa.id IN (:ids)")
	int updateSelectedInIds(@Param("ids") List<Long> ids, @Param("selected") boolean selected);
}
