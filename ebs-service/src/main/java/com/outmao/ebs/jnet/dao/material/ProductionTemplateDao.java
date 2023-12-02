package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.material.ProductionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionTemplateDao extends JpaRepository<ProductionTemplate,Long> {

    public Page<ProductionTemplate> findAllByUser(User user, Pageable pageable);

}
