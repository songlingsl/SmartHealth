package com.smarthealth.diningroom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smarthealth.diningroom.entity.House;
import com.smarthealth.diningroom.entity.Songling;
import com.smarthealth.diningroom.mapper.HouseMapper;
import com.smarthealth.diningroom.mapper.SonglingMapper;
import com.smarthealth.diningroom.service.HouseService;
import com.smarthealth.diningroom.service.SonglingService;
import com.smarthealth.diningroom.vo.SonglingVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmartHealthApplicationTests {
    @Autowired
    private SonglingMapper slMapper;

    @Autowired
    private SonglingService slService;//后期要用service实现

    @Autowired
    private HouseService houseService;//后期要用service实现

    @Autowired
    private HouseMapper houseMapper;//后期要用service实现

    @Test
    void contextLoads() {
        System.out.println("jjjjj");
    }


    @Test
    public void slInsert() {//插入一条记录

        Songling user = new Songling();
        user.setName("dd");
        user.setNumber(55);
        slMapper.insert(user);
        // 成功直接拿会写的 ID
        System.out.println(user.getId());

    }

    @Test
    public void selectById() {//根于id查一条
        Songling user = slMapper.selectById(1254013078316630018l);
        System.out.println(user);
    }


    @Test
    void deleteById() {//根据id删除
        slMapper.deleteById(1254013440129777665l);

    }

    @Test
    public void deleteByMap() {//多条件删除单表
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "hhh新");
        columnMap.put("number", 666);
        int deleteByMap = slMapper.deleteByMap(columnMap);
        System.out.println(deleteByMap);
    }


    @Test
    void updateById() {//根据id更新
        Songling user = new Songling();
        user.setName("hhh新");
        user.setId(1254013511000928257l);
        slMapper.updateById(user);

    }

    @Test
    void update() {//根据条件更新
        Songling user = new Songling();
        //将name是hhh新更新number为666
        user.setNumber(666);
        UpdateWrapper<Songling> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "hhh新");
        slMapper.update(user, updateWrapper);
    }


    @Test
    void selectAll() {//查所有数据
        System.out.println(("----- selectAll method test ------"));
        List<Songling> userList = slMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByIds() {//多id查
        List<Long> asList = Arrays.asList(1088250446457389058L, 1254014061415256066l);
        List<Songling> userList = slMapper.selectBatchIds(asList);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {//多条件单表查询
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "hhh新");
        columnMap.put("number", 666);
        List<Songling> userList = slMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByENtity() {//与上面效果一样
        Songling user = new Songling();
        user.setName("hhh新");
        user.setNumber(666);
        QueryWrapper query = new QueryWrapper(user);
        List<Songling> userList = slMapper.selectList(query);
        userList.forEach(System.out::println);
    }


    @Test
    public void selectCount() {//条件数量
        Songling user = new Songling();
        user.setName("hhh新");
        user.setNumber(666);
        QueryWrapper query = new QueryWrapper(user);
        System.out.println("记录数" + slMapper.selectCount(query));
    }

    @Test
    public void insertHouse() {//
        House house = new House();
        house.setHouseName("鑫湖");
        house.setUserId(1254013511000928257l);
        houseService.save(house);

        // 成功直接拿会写的 ID
        System.out.println(house.getHouseId());

    }

    @Test
    public void slInsert11() {//插入一条记录

        Songling user = new Songling();
        user.setName("iiii");
        user.setNumber(99);
        slService.save(user);
        // 成功直接拿会写的 ID
        System.out.println(user.getId());

    }

    @Test
    public void getHouseByUser() {//联表查询
        Songling user = new Songling();
        user.setName("hhh新");
        user.setNumber(666);
        List list = houseService.findListByUsername(user);
        System.out.println("数量" + list.size());
        list.forEach(SmartHealthApplicationTests::printmy);
    }

    public static void printmy(Object c) {
        System.out.println("打印" + c);
    }

    @Test
    public void pagingHouseByUser() {//分页联表查询
        Page userPage = new Page<>(2, 2);//参数一是当前页，参数二是每页个数
        Songling user = new Songling();
        user.setNumber(1);
        houseMapper.pagingHouseByUser(userPage,user);

        System.out.println("总条数 -------------> {}"+userPage.getTotal());
        System.out.println("当前页数 -------------> {}"+ userPage.getCurrent());
        System.out.println("当前每页显示数 -------------> {}"+ userPage.getSize());
        //userPage.getRecords().forEach(SmartHealthApplicationTests::printmy);

        userPage.getRecords().forEach((item)->{
            SonglingVO vo=(SonglingVO)item;System.out.println(vo.getName()+vo.getHouse().getHouseName());});
    }

    @Test
    public void allUser() {//单表分页查询，在controller即可做

        Songling user = new Songling();
        user.setNumber(1);
        QueryWrapper query = new QueryWrapper(user);

        IPage<Songling> userPage = new Page<>(2, 2);//参数一是当前页，参数二是每页个数
       // userPage = slMapper.selectPage(userPage, query);
        slService.page(userPage, query);
        System.out.println("总条数 -------------> {}"+userPage.getTotal());
        System.out.println("当前页数 -------------> {}"+ userPage.getCurrent());
        System.out.println("当前每页显示数 -------------> {}"+ userPage.getSize());
        List<Songling> list = userPage.getRecords();
        list.forEach(SmartHealthApplicationTests::printmy);
    }




}
