package com.coderbike.controller;

import com.coderbike.model.entity.User;
import com.coderbike.service.AopThrowExceptionService;
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

}
