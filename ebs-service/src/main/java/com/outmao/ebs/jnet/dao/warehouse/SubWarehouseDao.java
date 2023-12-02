package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.warehouse.SubWarehouse;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubWarehouseDao extends JpaRepository<SubWarehouse,Long> {

    public List<SubWarehouse> findAllByWarehouse(Warehouse warehouse);

    public SubWarehouse findByWarehouseAndUser(Warehouse warehouse, User user);

    @Query("select s.warehouse from SubWarehouse s where s.user=?1 order by s.updateTime desc ")
    public List<Warehouse> findWarehouseAllByUser(User user);

}
