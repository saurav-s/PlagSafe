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
		
		
		boolean expectedResponse = service.validateUser("admin", "test-password");
		assertEquals(true, expectedResponse);
		
	}
	
	/**
	 * Test for negative scenario where the username is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsername(){
		
		boolean expectedResponse = service.validateUser("wrong_admin", "test-password");
		assertEquals(false, expectedResponse);
		
		
	}
	
	/**
	 * Test for negative scenario where the password is incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongPassword(){
		
		boolean expectedResponse = service.validateUser("admin", "wrong_password");
		assertEquals(false, expectedResponse);
		
		
	}
	
	/**
	 * Test for scenario where both username and password are incorrect
	 */
	@Test
	public void testValidateUserNegativeScenarioWrongUsernameAndPassword(){
		
		boolean expectedResponse = service.validateUser("wrong_admin", "wrong_password");
		assertEquals(false, expectedResponse);
		
		
	}

    /**
     * Test for scenario where both username and password are correct
     */
    @Test
    public void testValidateExistingUserFromDB() {
        boolean expectedResponse = service.validateUserFromDB("team109", "max123");
        assertEquals(false, expectedResponse);
    }


	/**
	 * Test for scenario where both username and password are incorrect
	 */
	@Test
	public void testValidateFromDB() {
		boolean expectedResponse = service.validateUserFromDB("wrong_admin", "wrong_password");
		assertEquals(false, expectedResponse);
	}
}
