package com.instagramapi.service;

import java.util.List;

import com.instagramapi.exception.UserException;
import com.instagramapi.model.User;

public interface UserService {
	
	public User registerUser(User user) throws UserException;
	
	public User findUserById(Integer userid) throws UserException;
	
	public User findUserByProfile(User user) throws UserException;
	
	public User findUserByUsername(String userName) throws UserException;
	
//	to follow the user 
//	when the user is following someone we need an api
	public String followUser(Integer reqUserId,Integer followUserId)  throws UserException;
	
	public String unfollowUser(Integer reqUserId,Integer followUserId)  throws UserException;

	
	public List<User> findUserByIds(List<Integer> userId) throws UserException;
	
	
	public List<User> searchUser(String query) throws UserException;
	
	public User updateUserDetails(User updatedUser,User existingUser) throws UserException;
	

}
