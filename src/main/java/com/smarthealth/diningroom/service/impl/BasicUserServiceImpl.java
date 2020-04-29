package com.smarthealth.diningroom.service.impl;

import com.smarthealth.diningroom.entity.BasicUser;
import com.smarthealth.diningroom.mapper.BasicUserMapper;
import com.smarthealth.diningroom.service.BasicUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author songling
 * @since 2020-04-29
 */
@Service
public class BasicUserServiceImpl extends ServiceImpl<BasicUserMapper, BasicUser> implements BasicUserService {

}
