package com.hsh.service.impl;

import com.hsh.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("demoService")
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    public Map<String,Object> sayHello(String name) {

        Map<String,Object> map = new HashMap<String, Object>();
        String result = "Hello "+name+"!";
        logger.info("provider return:{}",result);
        map.put("result", result);
        return map;
    }
}
