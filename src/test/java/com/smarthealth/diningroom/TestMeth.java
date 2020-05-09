package com.smarthealth.diningroom;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthealth.diningroom.entity.BasicUser;
import org.junit.Test;

import java.time.LocalDate;

public class TestMeth {
    @Test
    public void abc() throws JsonProcessingException {
        BasicUser user=new BasicUser();
        user.setNickName("呼呼段");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        BasicUser u= mapper.readValue(json,BasicUser.class);
        System.out.println(u.getNickName());


    }

    @Test
    public void date(){

        System.out.println(  LocalDate.now());
        System.out.println(  DateUtil.today());
        System.out.println(   DateUtil.thisHour(true));
        int hour=DateUtil.thisHour(true);
        if(0<=hour&&hour<11){
            System.out.println("早");
        }
        if(11<=hour&&hour<16){
            System.out.println("中");
        }
        if(16<=hour&&hour<24){
            System.out.println("晚");
        }
       ;

    }
    @Test
    public void orgData() {
        ObjectMapper mapper = new ObjectMapper();


    }
}
