package com.phasec.plagsafe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.phasec.plagsafe.objects.User;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private  BCryptPasswordEncoder encoder;

    /**
     *
     * @param userName username that needs to be validated
     * @param secret password
     * @return true iff a matching record exists in the database
     */
    public User validateUser(String userName, String secret) {
    		
        List<User> records = userRepository.findAll();
       
        User match = null;

        for(User user : records) {
            //user found, successful validation
        		if(userName.equalsIgnoreCase(user.getEmail()) && encoder.matches(secret, user.getPassword())) {
                match = user;
                break;
            }
        }
        // no user exists with the given credentials, validation failed
        return match;
    }
}