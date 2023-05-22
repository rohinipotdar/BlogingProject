package com.BikkadIT.CrudOperation.serviceI;

import java.util.List;

import com.BikkadIT.CrudOperation.Dto.CategoryDto;

public interface CategoryService {
	
	//create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updatecategory(CategoryDto categoryDto, Long categoryId);
	
	//delete
	void deletecategory(Long categoryId);
	
	//get
	public CategoryDto getCategory(Long categoryId);
	
	//getAll
	public List<CategoryDto> getAllCategory();
	
}
