package com.coderbike.service;

import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述
 *
 * @author LBG - 2017/11/3 0003
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class MemcachedTest {

    @Autowired
    private MemcachedService service;

    @Test
    public void testContext() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        XMemcachedClientBuilder builder = (XMemcachedClientBuilder) context.getBean("clientBuilder");
        System.out.println("builder: " + builder);
    }

    @Test
    public void setTest() throws Exception {
        boolean set = service.set("tea", "green team");
        if (set) {
            System.out.println("set ok!");
        }
    }
}
