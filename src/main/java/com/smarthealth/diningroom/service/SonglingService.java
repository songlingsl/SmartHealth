package com.smarthealth.diningroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smarthealth.diningroom.entity.Songling;

public interface SonglingService extends IService<Songling> {
    public Songling getcatchSongling(Songling songling);
}
