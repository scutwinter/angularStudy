package com.winter.highlight_spring4.ch3.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)// 1 SpringJUnit4ClassRunner在JUNIT环境下提供Spring TestContext Framework
@ContextConfiguration(classes = {TestConfig.class}) //2 @ContextConfiguration用来加载配置的ApplicationContext,其中classes属性用来加载配置类
@ActiveProfiles("prod")//3 @ActiveProfiles用来声明活动的profile
public class DemoBeanIntegrationTests {
    @Autowired
    private TestBean testBean;

    @Test
    public void prodBeanShouldInject(){
        String expected = "from production profile";
        String actual = testBean.getContent();
        Assert.assertEquals(expected,actual);
    }

}
