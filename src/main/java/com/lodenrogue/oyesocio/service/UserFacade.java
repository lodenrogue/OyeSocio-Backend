package com.lodenrogue.oyesocio.service;

import java.util.HashMap;
import java.util.Map;

import com.lodenrogue.oyesocio.model.User;

public class UserFacade extends AbstractFacade<User> {

	public UserFacade() {
		super(User.class);
	}

	public User findByEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		return findUnique("FROM User where email= :email", parameters);
	}

}
