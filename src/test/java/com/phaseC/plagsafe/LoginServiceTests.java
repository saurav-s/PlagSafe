package com.phasec.plagsafe;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	
	// member variable
	private LoginService service;

	//valid login
    private static final String VALID_USER_NAME = "team109";
    private static final String VALID_PASSWORD = "max123";

    private static final String INVALID_USER_NAME = "wrong_amin";
    private static final String INVALID_PASSWORD = "wrong_password";
	
	/**
	 * Method responsible for setting up the test suite
	 */
	@Before
	public void setUp(){
		
		service = new LoginService();
		
	}

	/**
	 * Test for positive scenario where both username and password are correct
	 */
	@Test
	public void testValidateUserPoistiveScenario(){
		
		
		boolean expectedResponse = service.validateUser(VALID_USER_NAME, VALID_PASSWORD);
		assertEquals(true, expectedResponse);
		
	}
	
	/**
	 * Test for negative scenario where the username is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsername(){
		
		boolean expectedResponse = service.validateUser(INVALID_USER_NAME, VALID_PASSWORD);
		assertEquals(false, expectedResponse);
		
		
	}
	
	/**
	 * Test for negative scenario where the password is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongPassword(){
		
		boolean expectedResponse = service.validateUser(VALID_USER_NAME, INVALID_PASSWORD);
		assertEquals(false, expectedResponse);
		
		
	}
	
	/**
	 * Test for scenario where both username and password are incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsernameAndPassword(){
		
		boolean expectedResponse = service.validateUser(INVALID_PASSWORD, INVALID_PASSWORD);
		assertEquals(false, expectedResponse);
		
		
	}
}
