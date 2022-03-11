package com.zbc.practise.spring.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author StormT1King
 */
@Component
@Data
public class Person {

    @Resource
    private UserService userService;
    private String name;
    private int age;
}
