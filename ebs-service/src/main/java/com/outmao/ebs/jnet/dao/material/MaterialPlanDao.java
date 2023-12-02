package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.material.MaterialPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialPlanDao extends JpaRepository<MaterialPlan,Long> {

    public Page<MaterialPlan> findAllByUser(User user, Pageable pageable);

}
