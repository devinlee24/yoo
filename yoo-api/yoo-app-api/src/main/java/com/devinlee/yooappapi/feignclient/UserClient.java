package com.devinlee.yooappapi.feignclient;

import com.devinlee.yooappapi.feignclient.fallback.UserClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通过Feign对外开放Api
 */
@FeignClient(name = "yoo-service-user",fallback = UserClientHystrix.class)
public interface UserClient {

    @GetMapping("/user/info/{username}")
    public String getUserInfo(@PathVariable(name = "username")String userName);
}
