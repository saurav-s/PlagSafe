package com.phasec.plagsafe;
/**
 * Login service
 */
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	/**
	 * Validate the user credentials
	 * @param userid
	 * @param password
	 * @return
	 */
    public boolean validateUser(String userid, String password) {
        return userid.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("test-password");
    }

}