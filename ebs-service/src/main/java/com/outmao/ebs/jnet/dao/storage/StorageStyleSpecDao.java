package com.outmao.ebs.jnet.dao.storage;

import com.outmao.ebs.jnet.entity.storage.StorageStyle;
import com.outmao.ebs.jnet.entity.storage.StorageStyleSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageStyleSpecDao extends JpaRepository<StorageStyleSpec,Long> {

    public StorageStyleSpec findByStyleAndName(StorageStyle style, String name);

}
