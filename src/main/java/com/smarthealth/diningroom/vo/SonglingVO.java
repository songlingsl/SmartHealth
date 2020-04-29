package com.smarthealth.diningroom.vo;

import com.smarthealth.diningroom.entity.House;
import com.smarthealth.diningroom.entity.Songling;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SonglingVO extends Songling{
       private House house;
      // private String houseName;

}
