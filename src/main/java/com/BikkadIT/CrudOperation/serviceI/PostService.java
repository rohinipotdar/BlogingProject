package com.BikkadIT.CrudOperation.serviceI;

import java.util.List;

import com.BikkadIT.CrudOperation.Dto.PostDto;
import com.BikkadIT.CrudOperation.Payloads.PostResponce;

public interface PostService {

	//create
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId);
	
	//update
	public PostDto updatePost(PostDto postDto, Long postId);
	
	//get
	public PostDto getPost(Long postId);
	
	//get post by category
	
	List<PostDto> getPostByCategory(Long categoryId);
	
	//get all post by User
	
	List<PostDto> getPostByUser(Long userId);
	
	// search post by user
	
	List<PostDto> searchPosts(String title);
	
	//getAll
	public PostResponce getAllPost(Integer pageSize, Integer pageNumber,String sortBy);
	
	//delete
	public void deletePost(Long postId);
}
