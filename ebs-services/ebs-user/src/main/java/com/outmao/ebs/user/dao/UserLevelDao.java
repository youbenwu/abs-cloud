package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserLevelDao extends JpaRepository<UserLevel,Long> {

    @Query("select l from UserLevel l where l.min>=?1 and l.max<=?1")
    public UserLevel findByStartLessThanEqualAndEndGreaterThanEqual(int value);


}
