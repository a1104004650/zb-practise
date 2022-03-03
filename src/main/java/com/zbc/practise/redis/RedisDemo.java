package com.zbc.practise.redis;

import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

/**
 * @author StormT1King
 */
public class RedisDemo {

	public static void main(String[] args) throws IOException {
		 //连接本地的 Redis 服务
		// 方法1
        Jedis jedis = new Jedis("10.83.163.126",12684);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        jedis.auth("goodluck"); 
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        // 前端的值
        String mobile = "1111";
        String value = "";
        if(jedis.get("userName" + mobile) == null) {
        	  // 查询数据库
            AppntDTO ebz = new AppntDTO();
            ebz.setName("唐");
            ebz.setSex("男");
            ObjectMapper mapper = new ObjectMapper();
            String writeValue = mapper.writeValueAsString(ebz);
        	// 查询数据库 数据库的值返回一个dto ebz
            jedis.set("userName" + mobile, writeValue);
        }else {
        	value = jedis.get("userName" + mobile);
        }
        System.out.println(value);
        
        System.out.println("ssssssssssssssssssssssssssssssssss");
        // 方法2
        RedisUtil.set("key1", "123");
        String string = RedisUtil.get("key1"); 
        System.out.println(string);
        
        RedisUtil.set("eeee", "12345");
        String string2 = RedisUtil.get("eeee"); 
        System.out.println(string2);
        
	}
}
