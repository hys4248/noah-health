package com.noahhealth.service;

import com.noahhealth.util.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RedisService
 * Created by zlren on 2017/6/11.
 */
@Service
public class RedisService {

    @Autowired(required = false)
    private JedisClient jedisClient;

    @Autowired
    private PropertyService propertyService;

    public String get(String key) {
        return jedisClient.get(key);
    }

    public String set(String key, String value) {
        return jedisClient.set(key, value);
    }

    public long expire(String key, int second) {
        return jedisClient.expire(key, second);
    }

    public long del(String key) {
        return jedisClient.del(key);
    }

    /**
     * 保存验证码到redis，过期时间为1分钟
     *
     * @param key
     * @param value
     */
    public void setSmSCode(String key, String value) {
        this.set(key, value);
        this.expire(key, propertyService.smsCodeExpire);
    }
}
