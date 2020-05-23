package com.smarthealth.diningroom;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.smarthealth.diningroom.controller.ApiController;
import com.smarthealth.diningroom.controller.BasicUserController;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.entity.Plate;
import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.service.PlateService;
import com.smarthealth.diningroom.util.DelayQueueManager;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmartTestControllerTests {
    @Autowired
    BasicUserController basicUserController;
    @Autowired
    DelayQueueManager delayQueueManager;
    @Resource
    private WxMaService wxMaService;
    @Autowired
    ApiController api;

    @Autowired
    private PlateService plateService;//
    @Autowired
    private MealService mealService;//
    @Autowired
    WxMpService wxMpService;
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
//        RecivedDishVO vo=new RecivedDishVO();
//        vo.setSid(164L);
//        vo.setWeight(555);
//        System.out.println(api.addDishForUser(vo));
//        Thread.sleep(1000000);

    }
    @Test
    public  void customIntake(){
        basicUserController.customIntake("一斤二两米饭");
        basicUserController.customIntake("一斤三两米饭");
        basicUserController.customIntake("半斤米饭");
        basicUserController.customIntake("米饭半斤，米饭一斤三两");
        basicUserController.customIntake("米饭200克");
        basicUserController.customIntake("两斤米饭");
        basicUserController.customIntake("两斤半米饭");
        basicUserController.customIntake("米饭三斤半");
    }

    @Test
    public  void testPlates() throws InterruptedException {//根据扫描的小程序码生成对应的餐盘入库

        List<File> list= FileUtil.loopFiles("G:\\desktopview\\重要文件\\智慧餐厅\\每日选餐助手");
        for (File f:list){
           // Image i= (Image) ImgUtil.read(f.getAbsolutePath());
            String decode = QrCodeUtil.decode(f);

            String[] name= f.getName().split("\\.");
           Long plateId=Long.valueOf(name[0].replace("餐盘","")) ;
           String plateUrl= decode.split("/a/")[1];
            Plate p=new Plate();
            p.setPlateId(plateId);
            p.setPlateUrl(plateUrl);
            plateService.save(p);
        }
    }


    @Test
    public  void getTodayMeal(){
        System.out.println(mealService.getTodayMeal("1263454513285599233"));;

    }


}
