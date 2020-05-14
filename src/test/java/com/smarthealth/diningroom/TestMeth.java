package com.smarthealth.diningroom;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthealth.diningroom.entity.BasicUser;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
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

    @Test
    public void fenci() {

        DicLibrary.insert(DicLibrary.DEFAULT, "鱼香茄子");
        DicLibrary.insert(DicLibrary.DEFAULT, "凉拌卤鸡胗");
        DicLibrary.insert(DicLibrary.DEFAULT, "黄瓜");
        System.out.println(DicAnalysis.parse("我吃了凉拌卤鸡胗100颗黄瓜字"));

      // Result parse =DicAnalysis.parse("鱼香茄子300克");
       // Result parse =DicAnalysis.parse("鱼香茄子300");
        Result parse =DicAnalysis.parse("黄瓜300颗");
        for (Term term : parse) {
            System.out.println(term.getName()+"    "+term.getNatureStr());
        }

    }
    @Test
    public void decode() throws Exception {
//         String str="https://mp.weixin.qq.com/a/~~aLNpFnXvyXU~4mh4z85WJTJCs1-cPzI8MA~~";
//        String result = new java.net.URI("~~aLNpFnXvyXU~4mh4z85WJTJCs1-cPzI8MA~~").getPath();
//
//
//        BASE64Decoder decoder = new BASE64Decoder();
//        System.out.println(new String(decoder.decodeBuffer(str), "UTF-8"));


        //System.out.println(URLDecoder.decode("https://mp.weixin.qq.com/a/~~aLNpFnXvyXU~4mh4z85WJTJCs1-cPzI8MA~~", "UTF-8"));
    }



}
