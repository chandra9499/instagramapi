package com.instagramapi.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.instagramapi.dto.UserDto;
import com.instagramapi.exception.UserException;
import com.instagramapi.model.User;
import com.instagramapi.repository.UserRepository;
import com.instagramapi.service.UserService;


public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) throws UserException {

		Optional<User> findByEmail = userRepository.findByEmail(user.getEmail());
		
		if(findByEmail.isPresent()) {
			throw new UserException("email is already registered");
		}
		Optional<User> findByUsername = userRepository.findByUsername(user.getUsername());
		if(findByUsername.isPresent()) {
			throw new UserException("username is already registered");
		}
		if(user.getEmail()==null || user.getPassword()==null || user.getName()==null || user.getUsername()==null) {
			throw new UserException("All fields are required");
		}
		
//		we have created new user because we want control which this the user has access to i.e non subscription user
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		return userRepository.save(newUser);
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> findById = userRepository.findById(userId);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		throw new UserException("user notexist with id : "+userId);
		
	}

	@Override
	public User findUserByProfile(User user) throws UserException {
		 Optional<User> opt = userRepository.findByEmail(user.getEmail());
		    
		 if(opt.isPresent()) {
			 return opt.get();
		 }
			
		 throw new UserException("user not exist with email : "+user.getEmail());
	}

	@Override
	public User findUserByUsername(String username) throws UserException {
		Optional<User> opt=userRepository.findByUsername(username);
		
		if(opt.isPresent()) {
			User user=opt.get();
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
//		the person i am following
		User followUser=findUserById(followUserId);
//		this is me
		User reqUser=findUserById(reqUserId);
		
		
//		i am follower convert my data into userdto
		UserDto follower=new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setUsername(reqUser.getUsername());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUserImage(reqUser.getImage());
		
//	    converting the person i am following into userdto
		UserDto following=new UserDto();
		following.setEmail(followUser.getEmail());
		following.setUsername(followUser.getUsername());
		following.setId(followUser.getId());
		following.setName(followUser.getName());
		following.setUserImage(followUser.getImage());
		
		
		followUser.getFollower().add(follower);
		
		reqUser.getFollowing().add(following);
		
		userRepository.save(followUser);
		userRepository.save(reqUser);
		
		return "you are following "+followUser.getUsername();
	}

	@Override
	public String unfollowUser(Integer reqUserId, Integer unfollowUserId) throws UserException {
		User unfollowUser=findUserById(unfollowUserId);
		
		System.out.println("unfollow user ---- "+unfollowUser.toString());
		System.out.println("unfollow user's follower"+unfollowUser.getFollower().toString());
		
		User reqUser=findUserById(reqUserId);
		
		UserDto unfollow=new UserDto();
		unfollow.setEmail(reqUser.getEmail());
		unfollow.setUsername(reqUser.getUsername());
		unfollow.setId(reqUser.getId());
		unfollow.setName(reqUser.getName());
		unfollow.setUserImage(reqUser.getImage());
		
	
		UserDto unfollowing=new UserDto();
		unfollowing.setEmail(unfollowUser.getEmail());
		unfollowing.setUsername(unfollowUser.getUsername());
		unfollowing.setId(unfollowUser.getId());
		unfollowing.setName(unfollowUser.getName());
		unfollowing.setUserImage(unfollowUser.getImage());
		
		
		unfollowUser.getFollower().remove(unfollow);

		userRepository.save(reqUser);
		
//		User user= userService.findUserById(userId);
//		UserDto userDto=new UserDto();
//		
//		userDto.setEmail(user.getEmail());
//		userDto.setUsername(user.getUsername());
//		userDto.setId(user.getId());
//		
//		Post post=findePostById(postId);
//		post.getLikedByUsers().remove(userDto);
		
		return "you have unfollow "+unfollowUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userId) throws UserException {
		List<User> users= userRepository.findAllUsersByIds(userId);
		
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		List<User> users=userRepository.findByQuery(query);
		if(users.size()==0) {
			throw new UserException("user not exist");
		}
		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
		if(updatedUser.getEmail()!= null) {
			existingUser.setEmail(updatedUser.getEmail());	
		}
		if(updatedUser.getBio()!=null) {
			existingUser.setBio(updatedUser.getBio());
		}
		if(updatedUser.getName()!=null) {
			existingUser.setName(updatedUser.getName());
		}
		if(updatedUser.getUsername()!=null) {
			existingUser.setUsername(updatedUser.getUsername());
		}
		if(updatedUser.getMobile()!=null) {
			existingUser.setMobile(updatedUser.getMobile());
		}
		if(updatedUser.getGender()!=null) {
			existingUser.setGender(updatedUser.getGender());
		}
		if(updatedUser.getWebsite()!=null) {
			existingUser.setWebsite(updatedUser.getWebsite());
		}
		if(updatedUser.getImage()!=null) {
			existingUser.setImage(updatedUser.getImage());
		}
		
		
		if(updatedUser.getId()==existingUser.getId()) {
			System.out.println(" u "+updatedUser.getId()+" e "+existingUser.getId());
			throw new UserException("you can't update another user"); 
		}
		
		
		return userRepository.save(existingUser);
	}

}
