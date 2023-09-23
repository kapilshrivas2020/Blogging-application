package com.apidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidev.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
