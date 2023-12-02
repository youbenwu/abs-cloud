package com.outmao.ebs.jnet.dao.storage;

import com.outmao.ebs.jnet.entity.storage.StorageStyleSpec;
import com.outmao.ebs.jnet.entity.storage.StorageStyleSpecTech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageStyleSpecTechDao extends JpaRepository<StorageStyleSpecTech,Long> {

    public StorageStyleSpecTech findBySpecAndName(StorageStyleSpec spec, String name);


}
