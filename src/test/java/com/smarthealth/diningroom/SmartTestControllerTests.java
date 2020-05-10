package com.smarthealth.diningroom;

import com.smarthealth.diningroom.controller.ApiController;
import com.smarthealth.diningroom.controller.BasicUserController;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.util.DelayQueueManager;
import com.smarthealth.diningroom.vo.RecivedDishVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmartTestControllerTests {
    @Autowired
    BasicUserController basicUserController;
    @Autowired
    DelayQueueManager delayQueueManager;

    @Autowired
    ApiController api;
    @Test
   public  void testConfig(){
        BasicUser user=new BasicUser();
        user.setNickName("老宋");
        user.setUserCode("222");
        basicUserController.getOrSaveUser(user);


    }

    @Test
    public  void startMeal(){
        Meal meal =new Meal();
        meal.setPlateId(226L);
        meal.setUserId(3444L);
        basicUserController.startMeal(meal);


    }


    @Test
    public  void testNotify(){
        BasicUser user=new BasicUser();
        user.setOpenId("owUSI5GDJfsF0p_FSkP9wz9UqYk8");
        basicUserController.notify(user);


    }
    @Test
    public  void testdelay() throws InterruptedException {
        System.out.println("开始");
        delayQueueManager.put(55L);
        Thread.sleep(5000L);
        delayQueueManager.put(55L);
        System.out.println("15秒后");
        Thread.sleep(100000000L);
    }

    @Test
    public  void addDishForUser() throws InterruptedException {
        RecivedDishVO vo=new RecivedDishVO();
        vo.setSid(12L);
        vo.setWeight(333);
        System.out.println(api.addDishForUser(vo));
        Thread.sleep(1000000);

    }


}
