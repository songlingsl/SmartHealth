package com.smarthealth.diningroom.controller;


import com.smarthealth.diningroom.conf.WechatConfig;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/basicUser")
public class BasicUserController {
    @Resource
    private WechatConfig wechatConfig;

    @PostMapping("/getOrSaveUser")
    public R getOrSaveUser(@Valid @RequestBody BasicUser user){


       return R.ok("哈哈");
    }

}

