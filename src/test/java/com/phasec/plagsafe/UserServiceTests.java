package com.phasec.plagsafe;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.phasec.plagsafe.objects.Role;
import com.phasec.plagsafe.objects.User;
import com.phasec.plagsafe.objects.UserRegistrationDto;

/**
 * test for UserService
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlagsafeApplication.class)
public class UserServiceTests {
	
	@Autowired
	UserService service;
	
	@MockBean
	UserRepo userRepository;
	
	@MockBean
    private BCryptPasswordEncoder passwordEncoder;
	
	@Test
	public void testfindByEmail() {
		User user = new User();
		Collection<Role> roles = new  ArrayList<>();
		Role role = new Role();
		role.setId((long) 1);
		role.setName("ROLE_USER");
		roles.add(role);
		user.setEmail("testmail@gmail.com");
		user.setFirstName("first");
		user.setLastName("last");
		user.setPassword("xyz");
		user.setRoles(roles);
		
		user.setRoles(roles);
		when(userRepository.findByEmail(anyString())).thenReturn(user);
		User userFound = service.findByEmail("testmail@gmail.com");
		assertEquals("first", userFound.getFirstName());
		assertEquals("last", userFound.getLastName());
		assertEquals("testmail@gmail.com", userFound.getEmail());
		assertEquals(roles, user.getRoles());
	}
	
	@Test
	public void testUserSave() {
		UserRegistrationDto userDto = new UserRegistrationDto();
		Collection<Role> roles = new  ArrayList<>();
		Role role = new Role();
		role.setId((long) 1);
		role.setName("ROLE_USER");
		roles.add(role);
		userDto.setEmail("testmail@gmail.com");
		userDto.setFirstName("first");
		userDto.setLastName("last");
		userDto.setPassword("xyz");
		when(passwordEncoder.encode("xyz")).thenReturn("abc1de7fg454fd");
		
		User user = new User();
		role.setId((long) 1);
		user.setEmail("testmail@gmail.com");
		user.setFirstName("first");
		user.setLastName("last");
		user.setPassword("xyz");
		
		Mockito.doReturn(user).when(userRepository).save(any(User.class));
		
		User userFound = service.save(userDto);
		assertEquals("first", userFound.getFirstName());
		assertEquals("last", userFound.getLastName());
		assertEquals("testmail@gmail.com", userFound.getEmail());
	}
	

	
	
}
