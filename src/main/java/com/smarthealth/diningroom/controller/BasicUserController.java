package com.smarthealth.diningroom.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.service.BasicUserService;
import com.smarthealth.diningroom.util.R;
import me.chanjar.weixin.common.error.WxErrorException;
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
    private WxMaService wxMaService;

    @Resource
    private BasicUserService basicUserService;

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

}

