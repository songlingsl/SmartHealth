package com.smarthealth.diningroom;

import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.service.SonglingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SonglingService slService;//后期要用service实现
    @Test
    public void testStringRedisTemplate() throws InterruptedException {
        stringRedisTemplate.opsForValue().set("aaa","aaa值");
        Thread.sleep(5000);
        String aa=stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(aa);

    }

    @Test
    public void stringRedisTemplate() throws InterruptedException {
        redisTemplate.opsForValue().set("aaa","aaa值");
        Thread.sleep(5000);
        String aa=(String)redisTemplate.opsForValue().get("aaa");
        System.out.println(aa);
        BasicUser user =new BasicUser();
        user.setNickName("宋");
        redisTemplate.opsForValue().set("user",user);
        user=(BasicUser)redisTemplate.opsForValue().get("user");
        System.out.println(redisTemplate.opsForValue().get("user").toString());
        System.out.println(user.getNickName());
        redisTemplate.opsForValue().set("int1",123);
        int int1=(int)redisTemplate.opsForValue().get("int1");//数字

        System.out.println(int1);
    }

    @Test
    public void getcatchSongling(){//注解获取redis
        Songling sl=new Songling();
        sl.setId(1254013078316630018l);
        slService.getcatchSongling(sl);
        System.out.println( slService.getcatchSongling(sl));

    }

}
