package com.apidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidev.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
