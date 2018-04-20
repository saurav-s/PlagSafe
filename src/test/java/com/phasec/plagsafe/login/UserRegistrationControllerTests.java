package com.phasec.plagsafe.login;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


import com.phasec.plagsafe.services.StorageService;
import com.phasec.plagsafe.UserService;
import com.phasec.plagsafe.system.UserRegistrationController;

/**
 * Tests for login controller class
 * @author sanketsaurav
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserRegistrationController.class)
public class UserRegistrationControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private StorageService storageService;
	
	
	@MockBean
    private UserService userService;
	
	
	/**
	 * Test for user registration success failure
	 * @throws Exception
	 */
	@Test
	public void testUserRegistrationSuccess() throws Exception {
		when(userService.findByEmail(anyString())).thenReturn(null);
		mvc.perform(post("/user/registration")
				.param("firstName", "team")
				.param("lastName", "109")
				.param("email", "test123@gmail.com")
				.param("confirmEmail", "test123@gmail.com")
				.param("password", "123")
				.param("confirmPassword", "123")
				)
			.andExpect(status().isOk())
			.andExpect(content().string("\"success\""));
	}
	
}

	