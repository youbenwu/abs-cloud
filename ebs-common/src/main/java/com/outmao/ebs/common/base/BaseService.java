package com.outmao.ebs.common.base;

import com.outmao.ebs.common.configuration.sys.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseService {

	@Autowired
	protected Config config;


}
