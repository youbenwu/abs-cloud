package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.material.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MaterialDao extends JpaRepository<Material,Long> , QuerydslPredicateExecutor<Material> {

    public Page<Material> findAllByUserAndDeletedFalse(User user, Pageable pageable);

}
