package com.smarthealth.diningroom.util;

import cn.hutool.core.date.DateUtil;
import com.smarthealth.diningroom.constant.CommonConstants;

public class CommonUtil {
    /**
     * 获取就餐类型 早中晚
     * @return
     */
    public static int getDealType(){
        int hour= DateUtil.thisHour(true);
        if(11<=hour&&hour<16){
            //System.out.println("中");
            return CommonConstants.NOON;
        }
        if(0<=hour&&hour<11){
            //System.out.println("早");
            return CommonConstants.MORNING;
        }
        return CommonConstants.NIGHT;
//        if(16<=hour&&hour<24){
//            //System.out.println("晚");
//            return CommonConstants.NIGHT;
//        }

    }
}
