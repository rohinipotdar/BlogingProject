package com.BikkadIT.CrudOperation.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.CrudOperation.Dto.CommentDto;
import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.serviceI.CommentServiceI;

@RestController
@RequestMapping("/api")
public class CommentController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CommentServiceI commentServiceI;
	
	//create Comment
	
	@PostMapping("/postId/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId)
	{
		logger.info("Request entering for create comment");
		CommentDto createComment = this.commentServiceI.createComment(commentDto, postId);
		logger.info("Complete request for create comment");
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	//delete comment
	
	@DeleteMapping("/Id/{Id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id)
	{
		logger.info("Request entering for delete comment with id", id);
		this.commentServiceI.deleteComment(id);
		logger.info("Copmlete request for delete comment with id", id);
		return new ResponseEntity<String>(AppConstants.USER_DELETE,HttpStatus.OK);
	}
	
	//get all comments
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> getAllComments(){
		logger.info("Request entering for get all comments");
		List<CommentDto> allComments = this.commentServiceI.getAllComments();
		logger.info("Request completed for get all comments");
		return new ResponseEntity<List<CommentDto>>(allComments,HttpStatus.OK);
		
	}
}
