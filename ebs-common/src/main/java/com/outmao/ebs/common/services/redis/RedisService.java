package com.outmao.ebs.common.services.redis;

public interface RedisService {

    public void setValue(String key, String value);

    public String getValue(String key);

}
