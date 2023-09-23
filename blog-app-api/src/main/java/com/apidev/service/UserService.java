package com.apidev.service;

import java.util.List;

import com.apidev.payloads.UserDto;

public interface UserService{
	
	UserDto registerUser(UserDto userDto);
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer id);
	
	UserDto getUser(Integer id);
	
	List<UserDto> getAllusers();
	
	void deleteUser(Integer id);
}
