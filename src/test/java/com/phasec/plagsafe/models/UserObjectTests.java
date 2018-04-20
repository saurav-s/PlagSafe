package com.phasec.plagsafe.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserObjectTests {
	
	UserObject object;
	
	@Before
	public void setUp(){
		object = new UserObject();
	}
	
	@Test
	public void testUserNameAttribute(){
		
		object.setUserName("JohnDoe");
		assertEquals("JohnDoe", object.getUserName());
		
	}
	
	@Test
	public void testStatusIdAttribute(){
		
		object.setStatusId("STATUS001");
		assertEquals("STATUS001", object.getStatusId());
		
	}
	
	@Test
	public void testSecretAttribute(){
		
		object.setSecret("password");
		assertEquals("password", object.getSecret());
		
	}
	
	@Test
	public void testIdAttribute(){
		
		object.setId(3);
		assertEquals(3, object.getId());
		
	}
	
	
	

}
