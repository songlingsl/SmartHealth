package com.smarthealth.diningroom.util;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.service.BasicUserService;
import com.smarthealth.diningroom.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
@Slf4j
@Component
public class DelayQueueManager implements CommandLineRunner {
    private DelayQueue<DelayedElement> delayQueue = new DelayQueue();
    @Resource
    private WxMaService wxMaService;
    @Resource
    private MealService mealService;

    @Resource
    private BasicUserService basicUserService;
    @Override
    public void run(String... args) throws Exception {
        new Thread(()->{
            while (true) {
                try {

                    DelayedElement e=delayQueue.take();
                    Long plateId=e.getPlateId();
                    Meal meal=new Meal();
                    int mealType=CommonUtil.getDealType();
                    meal.setMealDay(LocalDate.now());
                    meal.setMealType(mealType);
                    meal.setPlateId(plateId);
                    QueryWrapper query = new QueryWrapper(meal);
                    meal=mealService.getOne(query);
                    if(meal==null){
                        continue;
                    }
                    Long userId=meal.getUserId();
                    BasicUser user=basicUserService.getById(userId);
                    String openId=user.getOpenId();
                    WxMaSubscribeMessage message=new WxMaSubscribeMessage();
                    message.setTemplateId("5EQ2a0F9vX8_exQn7dhu7AJUoBhMif_y8zaYgF23BJ4");
                    message.setPage("/pages/canting/canting");
                    message.setToUser(openId);
                    List<WxMaSubscribeMessage.Data> dataList=new ArrayList<>();
                    WxMaSubscribeMessage.Data data=new WxMaSubscribeMessage.Data();
                    data.setName("thing1");
                    data.setValue("请查看您的本餐营养摄入量");
                    dataList.add(data);

                    data=new WxMaSubscribeMessage.Data();
                    data.setName("thing4");
                    data.setValue("学院健康食堂");
                    dataList.add(data);
                    message.setData(dataList);
                    wxMaService.getMsgService().sendSubscribeMsg(message);
                    log.info("延迟任务发送通知对应的用户是"+user.getNickName());
                } catch (Exception e) {
                    continue;
                }
            }
        }).start();

    }

    public void put(Long plateId) {
        DelayedElement element = new DelayedElement(10000,plateId);//3分钟3*60000
        if(delayQueue.contains(element)){
            delayQueue.remove(element);
            log.info("有相同plateId移除");
        }
        delayQueue.put(element);


    }
}
