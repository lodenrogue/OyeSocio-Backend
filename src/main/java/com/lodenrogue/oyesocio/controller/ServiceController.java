package com.lodenrogue.oyesocio.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.TypeResponse;
import com.lodenrogue.oyesocio.model.User;
import com.lodenrogue.oyesocio.service.UserFacade;
import com.lodenrogue.oyesocio.view.HtmlViewBuilder;

@RestController
public class ServiceController {

	@RequestMapping(path = "/api/signin", method = RequestMethod.GET)
	public TypeResponse signin(@RequestParam String email) throws IOException {
		String type = "";
		String data = "";
		User user = new UserController().getUser(email);
		if (user == null) {
			type = "SIGNUP";
		}
		else {
			type = "PROFILE";
		}
		TypeResponse response = new TypeResponse();
		response.setType(type);
		response.setData(data);
		return response;
	}

	@RequestMapping(path = "/api/register", method = RequestMethod.POST)
	public User register(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName) {
		User user = new UserFacade().findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user = new UserFacade().create(user);
		}
		return user;
	}

	@RequestMapping(path = "api/profile", method = RequestMethod.GET)
	public String getProfile(@RequestParam String email) {
		User user = new UserController().getUser(email);
		if (user != null) {
			return new HtmlViewBuilder("schemas").buildProfile(user);
		}
		else {
			return "ERROR: NO USER FOUND";
		}
	}

}
