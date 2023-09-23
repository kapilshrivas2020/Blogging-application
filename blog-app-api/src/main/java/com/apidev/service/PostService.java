package com.apidev.service;

import java.util.List;

import com.apidev.payloads.PostDto;
import com.apidev.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer id);
	
	PostDto getPost(Integer id);
	
	PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDesc);
	
	void deletePost(Integer id);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> searchPost(String keyword);
}
