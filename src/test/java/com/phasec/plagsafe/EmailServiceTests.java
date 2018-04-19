package com.phasec.plagsafe;


import com.phasec.plagsafe.services.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class EmailServiceTests {
	
	@Autowired
    EmailService service;
	
	
	@Test
	public void test() {
		service.sendMail("snkt087@gmail.com", "test2", "this is test message body");
	}
	

	
	
}
