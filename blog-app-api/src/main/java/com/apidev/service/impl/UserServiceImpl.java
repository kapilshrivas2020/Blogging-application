package com.apidev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apidev.model.Role;
import com.apidev.model.User;
import com.apidev.payloads.UserDto;
import com.apidev.repository.RoleRepo;
import com.apidev.repository.UserRepo;
import com.apidev.service.UserService;
import com.apidev.utils.AppConstants;
import com.apidev.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = this.userRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("user", "id", id)) );
		
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		this.userRepo.save(user);
		
		UserDto userDto1 = userToDto(user);
		return userDto1;
	}

	@Override
	public UserDto getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException("user", "id", id));
		UserDto reqUser = userToDto(user);
		return reqUser;
	}

	@Override
	public List<UserDto> getAllusers() {
		List<User> usersList = this.userRepo.findAll();
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(int i=0; i<usersList.size(); i++) {
			UserDto userDto = userToDto(usersList.get(i));
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException("user", "id", id) );
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User savedUser = this.userRepo.save(user);
		
		return this.modelMapper.map(savedUser, UserDto.class);
	}
}
