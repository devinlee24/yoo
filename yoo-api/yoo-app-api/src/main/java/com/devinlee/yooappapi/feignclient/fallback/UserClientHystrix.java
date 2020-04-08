package com.devinlee.yooappapi.feignclient.fallback;

import com.devinlee.yooappapi.feignclient.UserClient;

/**
 * 接口熔断
 */
public class UserClientHystrix implements UserClient{

    @Override
    public String getUserInfo(String userName) {

        return String.format("抱歉！系统遇到问题！【%s】",userName);
    }
}
