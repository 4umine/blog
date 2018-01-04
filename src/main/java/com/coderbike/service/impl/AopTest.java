package com.coderbike.service.impl;

import com.coderbike.model.entity.User;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author LBG - 2017/12/26 0026
 */
@Aspect
@Component
public class AopTest {

    @AfterThrowing(throwing="ex",pointcut="execution(* com.coderbike.service.*.*(..))")
    public Object catchException(Throwable ex) {
        System.out.println("=================");
        RuntimeException exp = (RuntimeException) ex;
        if (exp instanceof IllegalArgumentException) {
            System.out.println("+++++++++++ IllegalArgumentException");
        }
        return new User();
    }
}
