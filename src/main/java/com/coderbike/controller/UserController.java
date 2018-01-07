package com.coderbike.controller;

import com.coderbike.client.AddUserRequest;
import com.coderbike.client.UserClient;
import com.coderbike.client.UserService;
import com.somelogs.model.Response;
import com.somelogs.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述
 *
 * @author LBG - 2018/1/4 0004
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserClient userClient;
    @Resource
    private UserService userService;

    @RequestMapping("/addUser")
    public Response<Long> addUser(@RequestBody AddUserRequest param) {
        LOGGER.info("request body: {}", JsonUtils.object2JSONString(param));
        Response<Long> resp = new Response<>();
        resp.setData(userService.addUser(param.getUsername(), param.getMobile()));
        return resp;
    }

    @RequestMapping("/test")
    public Response<Void> testRpcClient() {
        AddUserRequest param = new AddUserRequest();
        param.setUsername("imant");
        param.setMobile("18019921234");
        Response<Long> response = userClient.addUser(param);
        System.out.println(response);
        return new Response<>();
    }


}
