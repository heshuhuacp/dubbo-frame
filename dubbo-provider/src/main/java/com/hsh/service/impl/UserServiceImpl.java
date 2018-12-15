package com.hsh.service.impl;

import com.hsh.mapper.UserMapper;
import com.hsh.model.UserDTO;
import com.hsh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDTO getUserById(Integer userId){

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        logger.info("线程开始休眠,beginTime={}", dateFormat.format(new Date(System.currentTimeMillis())));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        logger.info("线程休眠结束,beginTime={}", dateFormat.format(new Date(System.currentTimeMillis())));
//        logger.info("服务返回结果");

        return userMapper.getUserById(userId);
    }

    @Override
    public List<UserDTO> getUserByProvince(String province) {

        return userMapper.getUsersByProvince(province);
    }
}
