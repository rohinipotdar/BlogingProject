package com.BikkadIT.CrudOperation.Controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.CrudOperation.Dto.PostDto;
import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.Payloads.ApiResponce;
import com.BikkadIT.CrudOperation.Payloads.PostResponce;
import com.BikkadIT.CrudOperation.serviceI.FileServiceI;
import com.BikkadIT.CrudOperation.serviceI.PostService;

/*
 * @author : Rohini
 * @apiNote : In This PostController requests handle from server like create, update, delete, get and getAll
 * @version : V 2.7.5
 * @Param : Post
 * @see :
 */

@RestController
@RequestMapping("/api")
public class PostController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileServiceI fileServiceI;
	
	@Value("${project.image}")
	private String path;
	
	//create
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			                                  @PathVariable Long userId,
			                                  @PathVariable Long categoryId)
	{
		logger.info("Request entering for the create Post");
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		logger.info("Completed the request for the create Post");
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//update post
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId){
		logger.info("Request entering for the update Post with postId : {}",postId);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("complete the request for update Post with postId : {}",postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// get posts by category
	@GetMapping("/category/{categoryId}/posts")
	
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId){
		logger.info("Request entering for get the Posts by categoryId : {}",categoryId);
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		logger.info("Complete the request for get the Posts by categoryId : {}",categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.CREATED);
	}
	
	// get posts by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
		logger.info("Request entering for get the Posts by userId : {}",userId);
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		logger.info("Complete the request for get the Posts by categoryId : {}",userId);
	 return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.CREATED);
	}
	// get all post
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPosts(
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize, 
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy)
	
	{
		 logger.info("Request entering for get All the Posts");
		 PostResponce postResponce1 = this.postService.getAllPost(pageSize,pageNumber,sortBy);
		 logger.info("Complete the request for get All the Posts");
		return new ResponseEntity<PostResponce>(postResponce1,HttpStatus.OK);
	}
	
	//get post by postId
	
	@GetMapping("/{postId}/post")
	public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
		logger.info("Request entering for get the Post by postId : {}",postId);
		PostDto postDto = this.postService.getPost(postId);
		logger.info("Request complete for get the Post by postId : {}",postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//Admin
	//delete post by postId
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{postId}")
	public ApiResponce deletePost(@PathVariable Long postId) {
		logger.info("Request entering for delete the Post by postId : {}",postId);
		this.postService.deletePost(postId);
		logger.info("Request complete for delete the Post by postId : {}",postId);
		return new ApiResponce(AppConstants.USER_DELETE,true);
	}
	
	//search post by title
	
	@GetMapping("/posts/search/{title}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String title){
		logger.info("Request enter for search the Post by postId : {}",title);
		List<PostDto> posts = this.postService.searchPosts(title);
		logger.info("Request complete for search the Post by postId : {}",title);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	// post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile images,
														@PathVariable Long postId) throws IOException{
		
		PostDto postDto = this.postService.getPost(postId);
		
		String fileName = this.fileServiceI.uploadImage(path, images);
				
		postDto.setImageName(fileName);
		
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
}
