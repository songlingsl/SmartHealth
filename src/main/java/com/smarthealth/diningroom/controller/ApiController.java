package com.smarthealth.diningroom.controller;

import com.smarthealth.diningroom.util.DelayQueueManager;
import com.smarthealth.diningroom.vo.RecivedDishVO;
import com.smarthealth.diningroom.vo.ReturnDishResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 接受其他服务调用
 * </p>
 *
 * @author songling
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Resource
    DelayQueueManager delayQueueManager;
    /**
     * 为一个用户增加菜肴
     * @param recivedDishVO 返回本餐盘所有摄入量
     * @return
     */
    @PostMapping("/addDishForUser")
    public ReturnDishResultVO addDishForUser(@Valid @RequestBody RecivedDishVO recivedDishVO ){
        Integer plateId=123;
        delayQueueManager.put(plateId);//发订阅通知
        //QueryWrapper query = new QueryWrapper(meal);
        ReturnDishResultVO vo=new ReturnDishResultVO();
        return vo;
    }
}
