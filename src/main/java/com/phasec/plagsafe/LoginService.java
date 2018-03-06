package com.phasec.plagsafe;

/**
 * Login service
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean validateUserFromDB(String username, String Password) {
        return false;
    }

    @Autowired
    private UserRepository userRepository;

    public boolean validateUsersFromDB(String userName, String secret) {
        int recordsCount = userRepository.find(userName, secret);
        return (recordsCount == 1);
    }
}