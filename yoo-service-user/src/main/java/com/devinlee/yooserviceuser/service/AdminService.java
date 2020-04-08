package com.devinlee.yooserviceuser.service;

import com.devinlee.yooappapi.feignclient.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminService implements UserClient {

    @GetMapping("/user/info/{username}")
    public String getUserInfo(@PathVariable(name = "username")String userName){

        return String.format("哈喽~%s",userName);
    }
}