package com.outmao.ebs.jnet.dao.storage;

import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.entity.storage.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageDao extends JpaRepository<Storage,Long> {

    public Storage findByOrder(Order order);

}
