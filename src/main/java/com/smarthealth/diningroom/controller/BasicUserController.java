package com.smarthealth.diningroom.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.entity.Dishes;
import com.smarthealth.diningroom.entity.Meal;
import com.smarthealth.diningroom.service.BasicUserService;
import com.smarthealth.diningroom.service.MealService;
import com.smarthealth.diningroom.util.CommonUtil;
import com.smarthealth.diningroom.util.DictManager;
import com.smarthealth.diningroom.util.R;
import com.smarthealth.diningroom.vo.IntakeVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@Slf4j
@RestController
@RequestMapping("/basicUser")
public class BasicUserController {
    @Resource
    private WxMaService wxMaService;

    @Resource
    private BasicUserService basicUserService;


    @Resource
    private MealService mealService;
    @Resource
    DictManager dictManager;
    /**
     * 先根据微信codeid拿到openid，根据openid从库中查找，没有的话入库
     * @param user
     * @return
     */
    @PostMapping("/getOrSaveUser")
    public R getOrSaveUser(@Valid @RequestBody BasicUser user){
        String openid="";
        try {
            WxMaJscode2SessionResult sessionResult=wxMaService.getUserService().getSessionInfo(user.getUserCode());
            openid=sessionResult.getOpenid();
        } catch (WxErrorException e) {
            return R.failed("获取用户openId出错");
        }
        BasicUser queryUser=new BasicUser();
        queryUser.setOpenId(openid);
        QueryWrapper query = new QueryWrapper(queryUser);
        queryUser= basicUserService.getOne(query);
        if(queryUser==null){
            user.setOpenId(openid);
            basicUserService.save(user);
            queryUser=user;
        }
        return R.ok(queryUser);
    }

    /**
     * 根据用户id更新个人的性别，年龄（出生年），身高，体重，工作强度（轻，中，重）
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public R updateUser(@Valid @RequestBody BasicUser user){
        basicUserService.updateById(user);
        return R.ok();
    }

    /**
     * 用户每次订阅要点击？必须页面点击总是
     * @param user
     * @return
     */
    @SneakyThrows
    @PostMapping("/notify")
    public R notify(@Valid @RequestBody BasicUser user){
        String openId=user.getOpenId();
        WxMaSubscribeMessage message=new WxMaSubscribeMessage();
        message.setTemplateId("jPMFdsrdlieFGGGCNplTqtmxCKv85B5W7RNfiBgz2rA");
        message.setPage("pages/about/notify/notify");
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
        Thread.sleep(10000);
        wxMaService.getMsgService().sendSubscribeMsg(message);
        return R.ok();
    }

    @SneakyThrows
    @PostMapping("/startMeal")
    public R startMeal(@Valid @RequestBody Meal meal){
        int mealType=CommonUtil.getDealType();
        meal.setMealDay(LocalDate.now());
        meal.setMealType(mealType);
        QueryWrapper query = new QueryWrapper(meal);
        if(mealService.count(query)<=0){//防止扫多次
           mealService.saveOrUpdate(meal);
       }
        return R.ok();
    }

    @SneakyThrows
    @PostMapping("/customIntake")
    public R customIntake(@RequestParam("voiceStr") String voiceStr){
        Map<String, Dishes> dishesNameMap=dictManager.getDishesNameMap();
         List<IntakeVO> list=new ArrayList();
        String[] voiceStrs=voiceStr.split("，");
        for(String voice:voiceStrs){
            Result parse =DicAnalysis.parse(voice);
            String dishName="";
            String type="";
            for (Term term : parse) {
                log.info("语音段："+term.getName()+"   分词型 "+term.getNatureStr());
                if(term.getNatureStr().equals("mq")){
                    type=term.getName();
                }
                if(term.getNatureStr().equals("userDefine")){
                    dishName=term.getName();
                }
            }
            if(!StringUtils.isEmpty(dishName)&&!StringUtils.isEmpty(type)){
                Dishes dish=dishesNameMap.get(dishName);
                if(dish==null){
                    continue;
                }
                IntakeVO vo=new IntakeVO();
                vo.setDishName(dishName);
                vo.setCalcium(dish.getEnergyCal().intValue());
                vo.setCarbohydrates(dish.getCarbohy().intValue());
                vo.setEnergy(dish.getEnergyCal().intValue());
                vo.setFats(dish.getFat().intValue());
                vo.setProteins(dish.getProtein().intValue());
                list.add(vo);
            }

        }
        return R.ok(list);
    }

}

