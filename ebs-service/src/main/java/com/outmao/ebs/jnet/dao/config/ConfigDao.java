package com.outmao.ebs.jnet.dao.config;

import com.outmao.ebs.jnet.entity.config.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ConfigDao extends JpaRepository<Config, Long>{
	@Query(nativeQuery = true,
			value = "SELECT * from z_config c WHERE c.`key` = :key AND c.is_deleted = 0")
    Config findByKey(@Param("key") String key);
}
