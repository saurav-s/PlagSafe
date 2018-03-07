package com.phasec.plagsafe;

/**
 * Login service
 */
import com.phasec.plagsafe.objects.UserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param userName username that needs to be validated
     * @param secret password
     * @return true iff a matching record exists in the database
     */
    public UserObject validateUser(String userName, String secret) {
        List<UserObject> records = userRepository.findAll();
        UserObject match = null;

        for(UserObject user : records) {
            //user found, successful validation
            if(user.getUserName().equals(userName) && user.getSecret().equals(secret)) {
                match = user;
                break;
            }
        }
        // no user exists with the given credentials, validation failed
        return match;
    }
}