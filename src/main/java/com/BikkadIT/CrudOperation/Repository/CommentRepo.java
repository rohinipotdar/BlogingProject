package com.BikkadIT.CrudOperation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIT.CrudOperation.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{

	
	
}
