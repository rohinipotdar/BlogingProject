package com.BikkadIT.CrudOperation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.CrudOperation.model.Category;
import com.BikkadIT.CrudOperation.model.Post;
import com.BikkadIT.CrudOperation.model.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Long>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitle(String title);
	
}
