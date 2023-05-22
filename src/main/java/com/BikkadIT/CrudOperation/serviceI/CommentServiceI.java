package com.BikkadIT.CrudOperation.serviceI;

import java.util.List;

import com.BikkadIT.CrudOperation.Dto.CommentDto;

public interface CommentServiceI {

	CommentDto createComment(CommentDto commentDto, Long postId);
	
	void deleteComment(Long id);
	
	List<CommentDto> getAllComments();
	
}
