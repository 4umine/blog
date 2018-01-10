package com.coderbike.controller;

import com.coderbike.model.entity.User;
import com.coderbike.service.AopThrowExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述
 * Created by liubingguang on 2017/7/31.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${myname}")
    private String myName;

    @Resource
    private AopThrowExceptionService exceptionService;

    @RequestMapping("/view.do")
    public String testView(HttpServletRequest req) {
        req.setAttribute("username", myName);
        return "test";
    }

    @RequestMapping("/aop")
    @ResponseBody
    public User testAop()  {
        return exceptionService.getUserById(null);
    }

    @RequestMapping("/log")
    @ResponseBody
    public String testLoggerLevel() {
        LOGGER.info("this is info msg!");
        LOGGER.debug("this is debug msg!");
        LOGGER.warn("this is warn msg!");
        return "ok";
    }

}
