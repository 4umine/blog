package com.coderbike.controller;

import com.somelogs.soa.model.AddUserRequest;
import com.somelogs.soa.model.Response;
import com.somelogs.utils.JsonUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/addUser")
    public Response<Long> addUser(@RequestBody AddUserRequest param) {
        System.out.println("request body: " + JsonUtils.object2JSONString(param));
        //AddUserRequest param = JsonUtils.readValue(body, new TypeReference<AddUserRequest>() {
        //});
        System.out.println(param);
        Response<Long> resp = new Response<>();
        resp.setData(RandomUtils.nextLong(1L, 10L));
        return resp;
    }
}
