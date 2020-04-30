package com.smarthealth.diningroom.conf;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {
    private String appId;
    private String  secret;

    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl conf=new WxMaDefaultConfigImpl();
        conf.setAppid(this.appId);
        conf.setSecret(this.secret);
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(conf);
        return wxMaService;
    }

}
