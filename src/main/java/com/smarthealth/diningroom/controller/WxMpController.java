package com.smarthealth.diningroom.controller;

import com.smarthealth.diningroom.util.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wxMp")
@Slf4j
public class WxMpController {
    @Resource
    WxMpService wxMpService;
    @SneakyThrows
    @GetMapping("/getNews")
    public R getNews(){
        WxMpMaterialNewsBatchGetResult result=wxMpService.getMaterialService().materialNewsBatchGet( 0, 10);

        log.info("获取图文"+result.getItems());
        return R.ok(result.getItems());
    }
}
