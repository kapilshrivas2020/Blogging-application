package com.apidev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apidev.exception.ResourceNotFoundException;
import com.apidev.model.Category;
import com.apidev.payloads.CategoryDto;
import com.apidev.repository.CategoryRepo;
import com.apidev.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category cat = this.categoryRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Category", "id", id)) );
		cat.setTitle(categoryDto.getTitle());
		cat.setDescription(categoryDto.getDescription());
		this.categoryRepo.save(cat);
		return categoryDto;
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category cat = this.categoryRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Category", "id", id)) );
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> list = this.categoryRepo.findAll();
		List<CategoryDto> catList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			catList.add(this.modelMapper.map(list.get(i), CategoryDto.class));
		}
		return catList;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category cat = this.categoryRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Category", "id", id)) );
		this.categoryRepo.delete(cat);
	}

}
