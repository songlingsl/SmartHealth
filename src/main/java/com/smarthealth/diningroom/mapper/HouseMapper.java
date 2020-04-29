package com.smarthealth.diningroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smarthealth.diningroom.entity.House;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.vo.SonglingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songling
 * @since 2020-04-26
 */
public interface HouseMapper extends BaseMapper<House> {

   public  List findListByUsername(Songling songling);
   public IPage<List<SonglingVO>> pagingHouseByUser(Page page, @Param("query") Songling songling);


}
