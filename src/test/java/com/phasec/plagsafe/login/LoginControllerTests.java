package com.phasec.plagsafe.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.phasec.plagsafe.LoginController;
import com.phasec.plagsafe.LoginService;
import com.phasec.plagsafe.StorageService;
import com.phasec.plagsafe.objects.UserObject;

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
		UserObject user = new UserObject();
		user.setId(24);
		user.setSecret("secret");
		user.setStatusId("test_status");
		user.setUserName("team109");
		when(service.validateUser("team109", "testpwd@123")).thenReturn(user);
		mvc.perform(get("/logincheck")
				.param("name", "team109")
				.param("password", "testpwd@123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(24))
			.andExpect(jsonPath("userName").value("team109"))
			.andExpect(jsonPath("secret").value("secret"))
			.andExpect(jsonPath("statusId").value("test_status"));
	}

}

	