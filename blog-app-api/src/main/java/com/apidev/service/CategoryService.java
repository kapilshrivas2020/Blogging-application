package com.apidev.service;

import java.util.List;

import com.apidev.payloads.CategoryDto;

public interface CategoryService{
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	
	CategoryDto getCategory(Integer id);
	
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(Integer id);
}
