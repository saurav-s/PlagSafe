package com.phasec.plagsafe;

import com.phasec.plagsafe.objects.User;
import com.phasec.plagsafe.objects.UserRegistrationDto;

public interface UserService {
	
    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
