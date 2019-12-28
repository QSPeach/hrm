package cn.itsource.hrm.client.impl;

import cn.itsource.hrm.client.RedisClient;
import org.springframework.stereotype.Component;

@Component
public class RedisClientImpl implements RedisClient{

    public void set(String key, String value) {

    }

    public String get(String key) {
        return "系统繁忙!";
    }
}
