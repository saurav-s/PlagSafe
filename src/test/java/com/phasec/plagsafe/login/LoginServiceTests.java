package com.phasec.plagsafe.login;

import static org.junit.Assert.assertEquals;

import com.phasec.plagsafe.LoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * This file contains the test cases required for testing the login service
 * @author Tridiv
 * created on 1st March 2018
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTests {
	
	@Autowired
	private LoginService service;

	//valid login
    private static final String VALID_USER_NAME = "team109";
    private static final String VALID_PASSWORD = "max123";

    private static  final String  VALID_TEST_USER_NAME = "testadmin";
    private static final String VALID_TEST_USER_PASSWORD = "test123";

    private static final String INVALID_USER_NAME = "wrong_amin";
    private static final String INVALID_PASSWORD = "wrong_password";

	/**
	 * Test for positive scenario where both username and password are correct
	 */
	@Test
	public void testValidateUserPoistiveScenario(){
	    boolean expectedResponse = service.validateUser(VALID_USER_NAME, VALID_PASSWORD);
	    String message = "Login test for a valid admin user";
        Assert.assertTrue(message, expectedResponse);
	}

    @Test
    public void testLoginTestUserPositive() {
        boolean expectedResponse = service.validateUser(VALID_TEST_USER_NAME, VALID_TEST_USER_PASSWORD);
        String message = "Login test for a valid admin test user";
        Assert.assertTrue(message, expectedResponse);
    }

	/**
	 * Test for negative scenario where the username is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsername(){
	    boolean expectedResponse = service.validateUser(INVALID_USER_NAME, VALID_PASSWORD);
        String message = "Login test for a invalid user";
        Assert.assertFalse(message, expectedResponse);
	}
	
	/**
	 * Test for negative scenario where the password is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongPassword(){
	    boolean expectedResponse = service.validateUser(VALID_USER_NAME, INVALID_PASSWORD);
        String message = "Login test for a valid admin user, but invalid password";
        Assert.assertFalse(message, expectedResponse);
	}
	
	/**
	 * Test for scenario where both username and password are incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsernameAndPassword(){
	    boolean expectedResponse = service.validateUser(INVALID_PASSWORD, INVALID_PASSWORD);
        String message = "Login test for credentials not present in db";
        Assert.assertFalse(message, expectedResponse);
	}
}
