package com.coderbike.mybatis;

import com.coderbike.model.entity.User;
import com.coderbike.mybatis.mbg.MBGUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述
 * Created by liubingguang on 2017/7/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml", "classpath:spring/spring-mybatis.xml"})
public class CRUDTest {

    @Autowired
    private MBGUserDao userDao;

    @Test
    public void insertTest() throws Exception {
        User user = new User();
        user.setUsername("imant");
        user.setPassword("hahahah");

        int i = userDao.insertSelective(user);
        System.out.println("=====" + i);
    }

    @Test
    public void getByIdTest() throws Exception {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void updateTest() throws Exception {
        User user = userDao.selectByPrimaryKey(1);
        user.setUsername("daodao1");

        userDao.updateByPrimaryKeySelective(user);
    }

    @Test
    public void deleteTest() throws Exception {
        int i = userDao.deleteByPrimaryKey(1);
        System.out.println("delete result count " + i);
    }
}
