package com.outmao.ebs.data.dao;

import com.outmao.ebs.data.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo,Long> {

    public void deleteAllByTargetType(String targetType);

    public void deleteAllByTargetTypeAndTargetId(String targetType,Long targetId);


}
