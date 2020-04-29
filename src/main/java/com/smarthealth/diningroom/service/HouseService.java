package com.smarthealth.diningroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smarthealth.diningroom.entity.House;
import com.smarthealth.diningroom.entity.Songling;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songling
 * @since 2020-04-26
 */
public interface HouseService extends IService<House> {
    List findListByUsername(Songling songling);


}
