package com.outmao.ebs.jnet.dao.material;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.material.MaterialSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MaterialSupplierDao extends JpaRepository<MaterialSupplier,Long> {

    public List<MaterialSupplier> findAllByCreator(User creator);

    public List<MaterialSupplier> findAllByIdIn(Collection<Long> ids);

}
