package com.hsh.hystrix.command;

import com.hsh.model.UserDTO;
import com.hsh.service.UserService;
import com.netflix.hystrix.*;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息的Command
 */
@Component("getUserInfoCommand")
public class GetUserInfoCommand extends HystrixCommand<UserDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInfoCommand.class);

    @Autowired
    private UserService userService;

    private Integer userId;


    public GetUserInfoCommand(){

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserInfoGroup"))//指定group
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetUserInfoCommand"))//指定command
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(4000))//设置超时
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserInfoPool")));//指定ThreadPool

    }

    @Override
    protected UserDTO run() {

        return userService.getUserById(userId);

    }

    /**
     * 降级服务
     * @return
     */
    @Override
    protected UserDTO getFallback() {
        logger.info("GetUserInfoCommand调用降级服务,返回空对象...");
        return new UserDTO();
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
