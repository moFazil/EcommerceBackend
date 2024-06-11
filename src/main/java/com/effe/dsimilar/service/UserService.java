package com.effe.dsimilar.service;

import com.effe.dsimilar.exception.UserException;
import com.effe.dsimilar.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
