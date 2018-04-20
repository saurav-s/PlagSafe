package com.phasec.plagsafe.system;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phasec.plagsafe.UserService;
import com.phasec.plagsafe.objects.User;
import com.phasec.plagsafe.objects.UserRegistrationDto;

import util.DataFormatUtility;


@RestController
@RequestMapping("/user")
public class UserRegistrationController {

	@Autowired
	private UserService userService;


	/**
	 * 
	 * @return a new User Registration Dto
	 */
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}


	/**
	 * registers UserAccount
	 * @param userDto
	 * @param result
	 */
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userDto, BindingResult result) {
		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			return DataFormatUtility.getJsonString("There is already an account registered with that email");
		}
		if (result.hasErrors()) {
			return DataFormatUtility.getJsonString("Validation errors");
		}
		userService.save(userDto);
		return DataFormatUtility.getJsonString("success");
	}

}