package com.smarthealth.diningroom.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.mapper.SonglingMapper;
import com.smarthealth.diningroom.service.SonglingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SonglingServiceImpl extends ServiceImpl<SonglingMapper, Songling> implements SonglingService {

}
