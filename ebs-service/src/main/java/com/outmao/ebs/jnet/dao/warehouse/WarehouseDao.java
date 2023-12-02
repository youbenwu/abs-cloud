package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseDao extends JpaRepository<Warehouse,Long> {

    public List<Warehouse> findAllByUser(User user);

    public List<Warehouse> findAllByManager(User manager);

}
