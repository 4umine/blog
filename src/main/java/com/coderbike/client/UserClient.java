package com.coderbike.client;

import com.somelogs.annotation.RequestRoute;
import com.somelogs.model.Response;

/**
 * 描述
 *
 * @author LBG - 2018/1/7 17:46
 */
@RequestRoute(url = "/user")
public interface UserClient {

    @RequestRoute(url = "/addUser")
    Response<Long> addUser(AddUserRequest param);
}
