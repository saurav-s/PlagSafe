//package com.phasec.plagsafe.util;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.phasec.plagsafe.PlagsafeApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootConfiguration(classes = PlagsafeApplication.class)
//@WebIntegrationTest(value = "server.port=9000")
//@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:9000")
//public class HomeControllerTest {
//
//    @Autowired
//    private WebDriver driver;
//
//    private HomePage homePage;
//
//    @Before
//    public void setUp() throws Exception {
//        homePage = PageFactory.initElements(driver, HomePage.class);
//    }
//
//    @Test
//    public void containsActuatorLinks() {
//        homePage.assertThat()
//                .hasActuatorLink("autoconfig", "beans", "configprops", "dump", "env", "health", "info", "metrics", "mappings", "trace")
//                .hasNoActuatorLink("shutdown");
//    }
//
//    @Test
//    public void failingTest() {
//        homePage.assertThat()
//                .hasNoActuatorLink("autoconfig");
//    }
//}