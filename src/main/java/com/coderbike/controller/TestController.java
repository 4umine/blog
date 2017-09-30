package com.coderbike.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/view.do")
    public String testView(HttpServletRequest req) {
        req.setAttribute("username", myName);
        return "test";
    }
}
