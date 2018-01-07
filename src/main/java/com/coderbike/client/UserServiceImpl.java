package com.coderbike.client;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author LBG - 2018/1/7 17:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Long addUser(String username, String mobile) {
        return RandomUtils.nextLong(1L, 10L);
    }
}
