package com.apidev.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apidev.exception.ResourceNotFoundException;
import com.apidev.model.Comment;
import com.apidev.model.Post;
import com.apidev.payloads.CommentDto;
import com.apidev.repository.CommentRepo;
import com.apidev.repository.PostRepo;
import com.apidev.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired 
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow( (()-> new ResourceNotFoundException("Post", "id", postId)) );
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow( (()-> new ResourceNotFoundException("Comment", "id", commentId)) );
		this.commentRepo.delete(com);
	}

}
