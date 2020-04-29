package com.smarthealth.diningroom;

import com.smarthealth.diningroom.controller.BasicUserController;
import com.smarthealth.diningroom.entity.BasicUser;
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
    @Test
   public  void testConfig(){
        BasicUser user=new BasicUser();
        user.setNickName("老宋");
        user.setUserCode("222");
        basicUserController.getOrSaveUser(user);

    }
}
