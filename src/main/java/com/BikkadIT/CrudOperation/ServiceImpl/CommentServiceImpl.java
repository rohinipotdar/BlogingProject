package com.BikkadIT.CrudOperation.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BikkadIT.CrudOperation.Dto.CommentDto;
import com.BikkadIT.CrudOperation.Exception.ResourceNotFoundException;
import com.BikkadIT.CrudOperation.Repository.CommentRepo;
import com.BikkadIT.CrudOperation.Repository.PostRepo;
import com.BikkadIT.CrudOperation.model.Comment;
import com.BikkadIT.CrudOperation.model.Post;
import com.BikkadIT.CrudOperation.serviceI.CommentServiceI;

@Service
public class CommentServiceImpl implements CommentServiceI{

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	// create comment
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) {
	Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		
		Comment comment = this.mapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		Comment comment2 = this.commentRepo.save(comment);
		
		CommentDto newcomment = this.mapper.map(comment2, CommentDto.class);
		
		return newcomment;
	}

	// delete comment
	
	@Override
	public void deleteComment(Long id) {
		Comment comment = this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", id));
		
		this.commentRepo.delete(comment);
		
		
	}

	// get all comment
	
	@Override
	public List<CommentDto> getAllComments() {
		List<Comment> list = this.commentRepo.findAll();
		
		List<CommentDto> list2 = list.stream().map((li)->mapper.map(li, CommentDto.class)).collect(Collectors.toList());
		return list2;
	}

}
