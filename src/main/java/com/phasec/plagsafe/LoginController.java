package com.phasec.plagsafe;
/**
 * Controller for login functionality
 */
import com.phasec.plagsafe.objects.UserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @Autowired
    LoginService service;

    /**
     * default login page mapping
     * @param model
     * @return
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    /**
     * api for login credential checks
     * @param model
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value="/logincheck", method = RequestMethod.GET)
    public UserObject showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        return service.validateUser(name, password);

    }

}