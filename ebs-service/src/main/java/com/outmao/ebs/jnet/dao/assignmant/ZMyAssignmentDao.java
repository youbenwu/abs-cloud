package com.outmao.ebs.jnet.dao.assignmant;

import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZMyAssignmentDao extends JpaRepository<ZMyAssignment, Long>{
	@Modifying
	@Query(nativeQuery = true,
			value = "update z_my_assignment ma SET ma.visits_num = ma.visits_num+:num WHERE ma.id IN (:ids)")
	int updateVisitsNumInIds(@Param("ids") List<Long> ids, @Param("num") long num);

	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment ma WHERE ma.id = ? AND ma.is_deleted = ? ORDER BY ma.update_time LIMIT 1")
    ZMyAssignment find1ByIdAndDeleted(long id, boolean deleted);

	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment ma WHERE ma.status = ? AND ma.is_deleted = ? ORDER BY ma.update_time DESC")
	List<ZMyAssignment> findByStatusAndDeleted(byte status, boolean deleted);

	/**
	 * 查找过期的外发
	 * @author yeyi
	 * @date 2019/9/25 16:44
	 **/
	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment ma WHERE ma.status in :statu AND ma.is_deleted = :deleted AND UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(ma.release_time) > ma.validity_time*3600 ORDER BY ma.update_time")
	List<ZMyAssignment> findByStatusAndDeletedExpired(@Param("statu") List<Byte> status, @Param("deleted") boolean deleted);

	@Modifying
	@Query(nativeQuery = true,
			value = "UPDATE z_my_assignment ma SET ma.`status`=:statu, ma.update_time = NOW() WHERE ma.id IN (:ids)")
	int updateStatusInIds(@Param("ids") List<Long> ids, @Param("statu") byte status);

	/**
	 * 按工艺及距离推荐外发
	 * 取前五十个中的随机 num 个
	 * @author yeyi
	 * @date 2019/10/25 19:22
	 **/
	@Query(nativeQuery = true,
			value = "SELECT * FROM ( SELECT ma.* , ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((:latitude * PI() / 180 - ma.latitude * PI() / 180) / 2), 2) + COS(:latitude * PI() / 180) * COS(ma.latitude * PI() / 180) * POW(SIN((:longitude * PI() / 180 - ma.longitude * PI() / 180) / 2), 2))) * 1000) AS distance_um FROM z_my_assignment ma JOIN z_my_assignment_technology mat ON mat.assignment_id = ma.id AND mat.technology_id IN (:technologyIds) WHERE ma.user_id <> :userId AND ma.is_deleted = 0 AND ma.`status` = 0 GROUP BY ma.id ORDER BY distance_um ASC LIMIT 50 ) t ORDER BY rand() LIMIT :num")
	List<ZMyAssignment> recomnend(@Param("userId") long userId, @Param("latitude") String latitude, @Param("longitude") String longitude, @Param("num") long num, @Param("technologyIds") List<Long> technologyIds);

	/**
	 * 按工艺及距离推荐外发
	 * 取前五十个中的随机 num 个
	 * (比 recomnend 方法多一个 userType 参数)
	 * @author yeyi
	 * @date 2019/11/03 15:22
	 **/
	@Query(nativeQuery = true,
			value = "SELECT * FROM ( SELECT ma.* , ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((:latitude * PI() / 180 - ma.latitude * PI() / 180) / 2), 2) + COS(:latitude * PI() / 180) * COS(ma.latitude * PI() / 180) * POW(SIN((:longitude * PI() / 180 - ma.longitude * PI() / 180) / 2), 2))) * 1000) AS distance_um FROM z_my_assignment ma JOIN z_my_assignment_technology mat ON mat.assignment_id = ma.id AND mat.technology_id IN (:technologyIds) WHERE ma.user_id <> :userId AND ma.user_type=:userType AND ma.is_deleted = 0 AND ma.`status` = 0 GROUP BY ma.id ORDER BY distance_um ASC LIMIT 50 ) t ORDER BY rand() LIMIT :num")
	List<ZMyAssignment> recomnendByUserType(@Param("userId") long userId, @Param("latitude") String latitude, @Param("longitude") String longitude, @Param("num") long num, @Param("technologyIds") List<Long> technologyIds, @Param("userType") int userType);

	/**
	 * 随机推荐 num 个外发
	 * @author yeyi
	 * @date 2019/10/25 19:22
	 **/
	@Query(nativeQuery = true,
			value = "SELECT * FROM z_my_assignment ma WHERE ma.is_deleted=0 AND ma.`status` = 0 ORDER BY RAND() LIMIT :num")
	List<ZMyAssignment> randomList(@Param("num") long num);
}
