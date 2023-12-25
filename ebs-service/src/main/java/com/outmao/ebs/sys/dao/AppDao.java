package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDao extends JpaRepository<App,Long> {


    public App findByCode(String code);

}
