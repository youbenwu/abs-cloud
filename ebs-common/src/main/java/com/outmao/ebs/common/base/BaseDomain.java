package com.outmao.ebs.common.base;



import com.outmao.ebs.common.configuration.security.SecurityPermissionEvaluator;
import com.outmao.ebs.common.configuration.sys.Config;
import com.outmao.ebs.common.dsl.BeanQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class BaseDomain extends BeanQuery {

    @Autowired
    protected Config config;

    @Autowired
    protected SecurityPermissionEvaluator security;




}
