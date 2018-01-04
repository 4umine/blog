package com.coderbike.service;

import com.coderbike.model.entity.User;

/**
 * 测试抛异常 aop 拦截
 *
 * @author LBG - 2017/12/26 0026
 */
public interface AopThrowExceptionService {

    User getUserById(Long userId);
}
