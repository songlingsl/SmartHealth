package com.smarthealth.diningroom.conf;

import com.smarthealth.diningroom.controller.ApiController;
import com.smarthealth.diningroom.vo.ReturnDishResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
@Slf4j
@ControllerAdvice(basePackageClasses = {ApiController.class})
public class ApiControllerAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ReturnDishResultVO handleException(Throwable throwable) throws IOException {
        log.error("api调用接口异常",throwable);
        ReturnDishResultVO vo=new ReturnDishResultVO();
        vo.setCode(1);
        vo.setMsg("fail");
        return vo;
    }
}
