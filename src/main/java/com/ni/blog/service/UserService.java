package com.ni.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ni.blog.entity.User;
import com.ni.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUserByUserId(Integer userId) {
		
		return userRepository.findUserByUserId(userId);
	}
	
	public boolean verify(User user) {
		User find=userRepository.getOne(user.getUserId());
		if(find.getPassword().equals(user.getPassword()) && find.getUserId()==(user.getUserId())) {
			return true;
		}
		return false;
	}
	public User alterUser(User user) {
		return userRepository.save(user);
	}
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	//获取用户状态，查看是否禁用
	public String getState(User user) {
		User find = userRepository.getOne(user.getUserId());
		if(find.getState().equals("disable")) {
			return "disable";
		}
		return "enable";
	}
	
	//禁用用户
	public User disableUser(User user) {
		user.setState("disable");
		return userRepository.save(user);
	}
	//启用用户
	public User enableUser(User user) {
		user.setState("enable");
		return userRepository.save(user);
	}
	//删除用户
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User signInVerify(Integer userId, String password) {
		// TODO Auto-generated method stub
		
		return userRepository.signInVerify(userId,password);
	}
}
