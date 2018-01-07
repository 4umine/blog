package com.coderbike.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author LBG - 2018/1/4 0004
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //@RequestMapping("/addUser")
    //public Response<Long> addUser(@RequestBody AddUserRequest param) {
    //    LOGGER.info("request body: {}", JsonUtils.object2JSONString(param));
    //    //AddUserRequest param = JsonUtils.readValue(body, new TypeReference<AddUserRequest>() {
    //    //});
    //    System.out.println(param);
    //    Response<Long> resp = new Response<>();
    //    resp.setData(RandomUtils.nextLong(1L, 10L));
    //    return resp;
    //}
}
