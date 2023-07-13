package com.zbc.practise.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/checkUser")
    @OperationTimeout
    public String checkUser(@RequestBody String userId) throws InterruptedException {
        logger.info("controller start");
        Thread.sleep(6000);
        logger.info("controller end");
        return userId;
    }
}
