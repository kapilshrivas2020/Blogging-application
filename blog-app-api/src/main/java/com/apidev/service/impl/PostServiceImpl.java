package com.apidev.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apidev.exception.ResourceNotFoundException;
import com.apidev.model.Category;
import com.apidev.model.Post;
import com.apidev.model.User;
import com.apidev.payloads.PostDto;
import com.apidev.payloads.PostResponse;
import com.apidev.repository.CategoryRepo;
import com.apidev.repository.PostRepo;
import com.apidev.repository.UserRepo;
import com.apidev.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow( (()-> new ResourceNotFoundException("User", "id", userId)) );

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow( (()-> new ResourceNotFoundException("Category", "id", categoryId)) );
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());;
		post.setUser(user);
		post.setCategory(cat);
		
		Post savedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		
		Post post = this.postRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Post", "id", id)) );
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Post", "id", id)) );
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDesc) {
		
		Sort sort = null;
		if(sortDesc.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		PageRequest p = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> post = this.postRepo.findAll(p);
		
		List<Post> posts = post.getContent();
		List<PostDto> postDtolist = new ArrayList<>();
		for(int i=0; i<posts.size(); i++) {
			PostDto postDto = this.modelMapper.map(posts.get(i), PostDto.class);
			postDtolist.add(postDto);
		}
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtolist);
		postResponse.setPageNo(post.getNumber());
		postResponse.setPageSize(post.getSize());
		postResponse.setTotalElement(post.getTotalElements());
		postResponse.setTotalPages(post.getTotalPages());
		postResponse.setLastPage(post.isLast());
		
		
		return postResponse;
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow( (()-> new ResourceNotFoundException("Post", "id", id)) );
		this.postRepo.delete(post);
		return;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow( (()-> new ResourceNotFoundException("User", "id", userId)) );
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtolist = new ArrayList<>();
		for(int i=0; i<posts.size(); i++) {
			PostDto postDto = this.modelMapper.map(posts.get(i), PostDto.class);
			postDtolist.add(postDto);
		}
		
		return postDtolist;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow( (()-> new ResourceNotFoundException("Category", "id", categoryId)) );
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtolist = new ArrayList<>();
		for(int i=0; i<posts.size(); i++) {
			PostDto postDto = this.modelMapper.map(posts.get(i), PostDto.class);
			postDtolist.add(postDto);
		}
		
		return postDtolist;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts =  this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postDtolist = new ArrayList<>();
		for(int i=0; i<posts.size(); i++) {
			PostDto postDto = this.modelMapper.map(posts.get(i), PostDto.class);
			postDtolist.add(postDto);
		}
		
		return postDtolist;
	}

}
