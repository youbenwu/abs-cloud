package com.outmao.ebs.jnet.dao.storage;

import com.outmao.ebs.jnet.entity.storage.Storage;
import com.outmao.ebs.jnet.entity.storage.StorageStyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageStyleDao extends JpaRepository<StorageStyle,Long> {

    public StorageStyle findByStorageAndName(Storage storage, String name);

}
