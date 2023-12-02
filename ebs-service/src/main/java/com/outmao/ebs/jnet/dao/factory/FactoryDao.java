package com.outmao.ebs.jnet.dao.factory;

import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.entity.factory.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FactoryDao extends JpaRepository<Factory,Long> {

    public Factory findByUser(User user);

    //@Query("select f from Factory f where f.user.id in ?1")
    public List<Factory> findAllByUserIdIn(Collection<Long> userIds);

}
