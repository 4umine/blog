package com.coderbike.controller;

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

    @RequestMapping("/view.do")
    public String testView(HttpServletRequest req) {
        req.setAttribute("username", "tom");
        return "test";
    }
}
