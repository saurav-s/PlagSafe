package com.phasec.plagsafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for login functionality
 */
import com.phasec.plagsafe.objects.UserObject;


@RestController
public class LoginController {

    @Autowired
    LoginService service;

    /**
     * default login page mapping
     * @param model ModelMap for the shouLoginPage method
     * @return the default login string
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    /**
     * api for login credential checks
     * @param model ModelMap for the shouLoginPage method
     * @param name the name of the logged in user
     * @param password the password of the logged in user
     * @return the User
     */
    @RequestMapping(value="/logincheck", method = RequestMethod.GET)
    public UserObject showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        return service.validateUser(name, password);

    }

}