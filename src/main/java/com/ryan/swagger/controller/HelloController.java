package com.ryan.swagger.controller;

import com.ryan.swagger.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiOperation("hello接口说明")
    @GetMapping("/hello")
    public Object hello(@ApiParam("用户名") @RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    @ApiOperation("实体接口说明")
    @GetMapping("/entity")
    public User entity() {
        User user = new User();
        return user;
    }
}
