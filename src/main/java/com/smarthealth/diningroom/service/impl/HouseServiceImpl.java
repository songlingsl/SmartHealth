package com.smarthealth.diningroom.service.impl;

import com.smarthealth.diningroom.entity.House;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.mapper.HouseMapper;
import com.smarthealth.diningroom.service.HouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songling
 * @since 2020-04-26
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Override
    public List findListByUsername(Songling songling) {
        return baseMapper.findListByUsername(songling);
    }


}
