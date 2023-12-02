package com.outmao.ebs.jnet.service.config;

import com.outmao.ebs.jnet.dao.config.ConfigDao;
import com.outmao.ebs.jnet.entity.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 把一些会变的配置放到 mysql 中，继承此类来操作
 * @author: yeyi
 * @date: 2019/4/24 14:08
 **/
@Service
public class ConfigBaseService {

	@Autowired
    ConfigDao configDao;
	
	private static final Logger log = LoggerFactory.getLogger(ConfigBaseService.class);

	protected String get(String key) {
		Config conf = configDao.findByKey(key);
        if(null==conf){
            log.error("get key no exist: {}", key);
            return null;
        }

        return conf.getValue();
	}

}
