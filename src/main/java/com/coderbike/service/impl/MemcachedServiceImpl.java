package com.coderbike.service.impl;

import com.coderbike.service.MemcachedService;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.XMemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述
 *
 * @author LBG - 2017/11/3 0003
 */
//@Service
@Slf4j
public class MemcachedServiceImpl implements MemcachedService {

    @Autowired
    private XMemcachedClient client;

    private static final int EXPIRE = 24*60*60;

    @Override
    public boolean set(String key, String value) {
        try {
            return client.set(key, EXPIRE, value);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String get(String key) {
        try {
            return client.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(String key) {
        try {
            return client.delete(key);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleteWithNoReply(String key) {
        try {
            client.deleteWithNoReply(key);
        } catch (Exception e) {
            // log...
        }
    }
}
