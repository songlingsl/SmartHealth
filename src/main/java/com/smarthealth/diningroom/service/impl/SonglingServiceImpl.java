package com.smarthealth.diningroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.mapper.SonglingMapper;
import com.smarthealth.diningroom.service.SonglingService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SonglingServiceImpl extends ServiceImpl<SonglingMapper, Songling> implements SonglingService {


    @Cacheable(cacheNames = "songling", key = "#songling.id")//unless="#result == null"
    public Songling getcatchSongling(Songling songling) {
        songling=baseMapper.selectById(songling.getId());
        return songling;
    }

}
