package com.phasec.plagsafe.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTests {
	
	User object;
	
	@Before
	public void setUp(){
		object = new User();
	}
	
	@Test
	public void testUserNameAttribute(){
		
		object.setFirstName("John");
		object.setLastName("doe");
		assertEquals("John", object.getFirstName());
		assertEquals("doe", object.getLastName());
		
	}
	
	@Test
	public void testEmailAttribute(){
		
		object.setEmail("bc@bc.com");
		assertEquals("bc@bc.com", object.getEmail());
		
	}
	

	
	@Test
	public void testIdAttribute(){
		
		object.setId((long)3);
		assertEquals(3, (long) object.getId());
		
	}
	
	
	

}
