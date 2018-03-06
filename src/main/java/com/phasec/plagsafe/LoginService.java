package com.phasec.plagsafe;

/**
 * Login service
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String userName, String secret) {
        int recordsCount = userRepository.find(userName, secret);
        return (recordsCount == 1);
    }
}