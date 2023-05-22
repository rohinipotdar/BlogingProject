package com.BikkadIT.CrudOperation.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.BikkadIT.CrudOperation.Controller.CategoryController;
import com.BikkadIT.CrudOperation.Dto.PostDto;
import com.BikkadIT.CrudOperation.Exception.ResourceNotFoundException;
import com.BikkadIT.CrudOperation.Payloads.PostResponce;
import com.BikkadIT.CrudOperation.Repository.CategoryRepo;
import com.BikkadIT.CrudOperation.Repository.PostRepo;
import com.BikkadIT.CrudOperation.Repository.UserRepository;
import com.BikkadIT.CrudOperation.model.Category;
import com.BikkadIT.CrudOperation.model.Post;
import com.BikkadIT.CrudOperation.model.User;
import com.BikkadIT.CrudOperation.serviceI.PostService;

@Service
public class PostServiceImpl implements PostService {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo category;
	
		
	//update post by postId
		
	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		
		logger.info("Entering Dao call for update the Post by postId : {}",postId);
		Post post1 = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		
		post1.setTitle(postDto.getTitle());
		post1.setContent(postDto.getContent());
		post1.setImageName(postDto.getImageName());
		
		Post newpost = this.postRepo.save(post1);
		PostDto postdto1 = this.mapper.map(newpost, PostDto.class);
		logger.info("Complete Dao call for update the Post by postId : {}",postId);
		return postdto1;
	}

	// get posts by user
	
	@Override
	public PostDto getPost(Long postId) {
		logger.info("Entering Dao call for get single Post by postId : {}",postId);
		Post post1 = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		PostDto postDto = this.mapper.map(post1, PostDto.class);
		logger.info("Complete Dao call for get single Post by postId : {}",postId);
		return postDto;
	}

	// get post by categoryId
	
	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		logger.info("Entering Dao call for get single Post by categoryId : {}",categoryId);
		Category newcategory = this.category.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("user", "categoryId", categoryId));
		List<Post> listpost = this.postRepo.findByCategory(newcategory);
		
		List<PostDto> list = listpost.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		logger.info("Complete Dao call for get single Post by categoryId : {}",categoryId);
		return list;
	}

	// get post by userId
	
	@Override
	public List<PostDto> getPostByUser(Long userId) {
		logger.info("Entering Dao call for get single Post by userId : {}",userId);
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> listpost = this.postRepo.findByUser(user);
		List<PostDto> collectpost = listpost.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		logger.info("Complete Dao call for get single Post by userId : {}",userId);
		return collectpost;
	}

	// search post by title
	
	@Override
	public List<PostDto> searchPosts(String title) {
		logger.info("Entering Dao call for search Post by title : {}",title);
		List<Post> list = this.postRepo.findByTitle(title);
		
		List<PostDto> collect = list.stream().map((li)->this.mapper.map(li, PostDto.class)).collect(Collectors.toList());
		logger.info("Complete Dao call for search Post by title : {}",title);
		return collect;
	}

	
	// get all post
	@Override
	public PostResponce getAllPost(Integer pageSize, Integer pageNumber,String sortBy) {
		
//		int pageSize=2;
//		int pageNumber=1;
		logger.info("Entering Dao call for get All Post by pageSize, pageNumber and sortedBy : {}",pageSize,pageNumber,sortBy);
		Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();
		
		List<PostDto> listpost1 = allPost.stream().map((li)->this.mapper.map(li, PostDto.class)).collect(Collectors.toList());
		
		PostResponce postResponce=new PostResponce();
		
		postResponce.setContent(listpost1);
		postResponce.setPageNumber(pagePost.getNumber());
		postResponce.setPageSize(pagePost.getSize());
		postResponce.setTotalElement(pagePost.getTotalPages());
		postResponce.setTotalPages(pagePost.getTotalPages());
		logger.info("Complete Dao call for get All Post by pageSize, pageNumber and sortedBy : {}",pageSize,pageNumber,sortBy);
		return postResponce;
	}

	// delete post
	
	@Override
	public void deletePost(Long postId) {
		logger.info("Entering Dao call for delete Post by postId : {}",postId);
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);
		logger.info("Complete Dao call for delete Post by postId : {}",postId);
	}

	//create post 
	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		logger.info("Entering Dao call for Create Post by userId, categoryId : {}",userId,categoryId);
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		
		Category category2 = this.category.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post = this.mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category2);
		
		Post newpost = this.postRepo.save(post);
		 PostDto postDto2 = this.mapper.map(newpost,PostDto.class);
		 logger.info("Complete Dao call for Create Post by userId, categoryId : {}",userId,categoryId);
		return postDto2;
	}

}
