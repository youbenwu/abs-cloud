package com.outmao.ebs.jnet.dao.assignmant;

import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentRobot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZMyAssignmentRobotDao extends JpaRepository<MyAssignmentRobot, Long>{
	@Query(nativeQuery = true,
			value = "SELECT * from z_my_assignment_robot ar WHERE ar.is_deleted = :deleted")
	List<MyAssignmentRobot> findByDeleted(@Param("deleted") boolean deleted);

	@Modifying
	@Query(nativeQuery = true,
			value = "UPDATE z_my_assignment_robot ma SET ma.is_deleted = :isDeleted WHERE ma.id IN(:ids) AND ma.is_deleted<> :isDeleted")
	int updateDeleted(@Param("isDeleted") boolean isDeleted, @Param("ids") List<Long> ids);
}
