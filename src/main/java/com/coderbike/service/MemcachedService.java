package com.coderbike.service;

import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 描述
 *
 * @author LBG - 2017/11/3 0003
 */
public interface MemcachedService {

    boolean set(String key, String value);

    String get(String key);

    boolean delete(String key);

    void deleteWithNoReply(String key) throws MemcachedException, InterruptedException;

}
