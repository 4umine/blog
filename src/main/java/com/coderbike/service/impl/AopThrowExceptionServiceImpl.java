package com.coderbike.service.impl;

import com.coderbike.model.entity.User;
import com.coderbike.service.AopThrowExceptionService;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author LBG - 2017/12/26 0026
 */
@Service
public class AopThrowExceptionServiceImpl implements AopThrowExceptionService {

    @Override
    public User getUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("user id is null");
        }
        User user = new User();
        user.setUsername("some logs");
        return user;
    }
}
