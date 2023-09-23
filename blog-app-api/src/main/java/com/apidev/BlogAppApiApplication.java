package com.apidev;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apidev.model.Role;
import com.apidev.repository.RoleRepo;
import com.apidev.utils.AppConstants;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("shaa"));
		
//		Role role = new Role();
//		role.setId(AppConstants.ADMIN_USER);
//		role.setName("ROLE_ADMIN");
//		
//		Role role2 = new Role();
//		role2.setId(AppConstants.NORMAL_USER);
//		role2.setName("ROLE_NORMAL");
//		
//		this.roleRepo.save(role);
//		this.roleRepo.save(role2);
	}
}
