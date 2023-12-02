package com.outmao.ebs.jnet.dao.warehouse;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.warehouse.SubWarehouseSession;
import com.outmao.ebs.jnet.entity.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubWarehouseSessionDao extends JpaRepository<SubWarehouseSession,Long> {

    public SubWarehouseSession findByWarehouseAndFromAndTo(Warehouse warehouse, User from, User to);

}
