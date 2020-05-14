package com.smarthealth.diningroom;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlateGenerator {


    @Test
    public  void testPlates(){//根据扫描的小程序码生成对应的餐盘入库

        List<File> list= FileUtil.loopFiles("G:\\desktopview\\重要文件\\智慧餐厅\\每日选餐助手");
        for (File f:list){
            // Image i= (Image) ImgUtil.read(f.getAbsolutePath());
            String decode = QrCodeUtil.decode(f);

            String[] name= f.getName().split("\\.");
            Long plateId=Long.valueOf(name[0].replace("餐盘","")) ;
            String plateUrl= decode.split("/a/")[1];
            System.out.println(plateId);
//            Plate p=new Plate();
//            p.setPlateId(plateId);
//            p.setPlateUrl(plateUrl);
//            plateService.save(p);
        }


    }

}
