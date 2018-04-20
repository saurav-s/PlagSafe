package com.phasec.plagsafe.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;


import com.phasec.plagsafe.objects.User;

import com.phasec.plagsafe.controllers.LoginController;
import com.phasec.plagsafe.services.LoginService;
import com.phasec.plagsafe.services.StorageService;

/**
 * Tests for login controller class
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private LoginService service;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	@MockBean
    private StorageService storageService;
	
	
	/**
	 * Test for login redirect String
	 * @throws Exception
	 */
	@Test
	public void testLoginRedirecSuccess() throws Exception {
		mvc.perform(get("/login")).andExpect(content().string("login"));
	}
	
	/**
	 * Test for login validation failure
	 * @throws Exception
	 */
	@Test
	public void testLoginValidationFailure() throws Exception {
		when(service.validateUser("team109", "test109")).thenReturn(null);
		mvc.perform(get("/logincheck")
				.param("name", "team109")
				.param("password", "test109"))
			.andExpect(status().isOk())
			.andExpect(content().string(""));
	}
	
	/**
	 * Test for login validation success
	 * @throws Exception
	 */
	@Test
	public void testLoginValidationSuccess() throws Exception {
		User user = new User();
		user.setId((long) 24);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("test_mail@gmail.com");
		when(service.validateUser("test_mail@gmail.com", "testpwd@123")).thenReturn(user);
	     String encoded = passwordEncoder.encode("max123");
		mvc.perform(get("/logincheck").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("team109:encoded".getBytes()))
				.param("name", "team109")
				.param("password", "testpwd@123"))
			.andExpect(status().isOk());
	}

}

	