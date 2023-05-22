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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.CrudOperation.Dto.CategoryDto;
import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.serviceI.CategoryService;

/*
 * @author : Rohini
 * @apiNote : In This CategoryController requests handle from server like create, update, delete, get and getAll
 * @version : V 2.7.5
 * @Param : Category
 * @see :
 */
 
@RestController 
@RequestMapping("/api/categories")
public class CategoryController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@RequestBody CategoryDto categoryDto){
		
		logger.info("Request entering for Create Category");
		
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		
		logger.info("Complete the create request for Category");
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId){
		
		logger.info("Request the category object for update Category with categoryId" + categoryId);
		CategoryDto updatecategory = this.categoryService.updatecategory(categoryDto,categoryId);
		logger.info("Complete the request for update category with new object");
		
		return new ResponseEntity<CategoryDto>(updatecategory,HttpStatus.OK);
		
	}
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> createcategory(@PathVariable  Long categoryId){
		
		logger.info("Request delete Category with categoryId"+categoryId);
		this.categoryService.deletecategory(categoryId);
		logger.info("complete delete the category with categoryId",categoryId);
		
		return new ResponseEntity<String>(AppConstants.USER_DELETE,HttpStatus.CREATED);
		
	}
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable  Long categoryId){
		
		logger.info("Request the get Category with categoryId"+categoryId);
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		logger.info("Complete the get category with categoryId",categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllcategory(){
		
		logger.info("Request the get All Category");
		List<CategoryDto> list = this.categoryService.getAllCategory();
		logger.info(" Complete request for get All category");
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
}
