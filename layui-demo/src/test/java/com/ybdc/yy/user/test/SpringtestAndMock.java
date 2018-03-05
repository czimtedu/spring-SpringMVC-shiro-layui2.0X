package com.ybdc.yy.user.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:spring/access-control.xml", "classpath:spring/dao.xml",
        "classpath:spring/property.xml", "classpath:spring/service.xml" })
public class SpringtestAndMock {
	
}
