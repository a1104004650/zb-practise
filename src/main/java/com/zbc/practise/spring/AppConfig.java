package com.zbc.practise.spring;

import com.zbc.practise.spring.bean.Person;
import com.zbc.practise.spring.bean.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author StormT1King
 */
@Configuration
public class AppConfig {
    @Bean(name="person")
    public Person entitlement() {
        Person ent= new Person();
        ent.setName("java");
        ent.setAge(12);
        return ent;
    }

    @Bean(name="userService")
    public UserService entitlement2() {
        UserService ent= new UserService();
        ent.setName("php");
        return ent;
    }

}