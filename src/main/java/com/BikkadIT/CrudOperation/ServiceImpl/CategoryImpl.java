package com.BikkadIT.CrudOperation.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BikkadIT.CrudOperation.Controller.CategoryController;
import com.BikkadIT.CrudOperation.Dto.CategoryDto;
import com.BikkadIT.CrudOperation.Exception.ResourceNotFoundException;
import com.BikkadIT.CrudOperation.Repository.CategoryRepo;
import com.BikkadIT.CrudOperation.model.Category;
import com.BikkadIT.CrudOperation.serviceI.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	//create Category

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		logger.info("Initiating Dao call for create category");
		Category category = this.mapper.map(categoryDto, Category.class);
		Category addcat = this.categoryRepo.save(category);
		CategoryDto categoryDto2 = this.mapper.map(addcat, CategoryDto.class);
		logger.info("Complete Dao call for create category");
		return categoryDto2;
	}

	//update category
	
	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, Long categoryId) {
		logger.info("Entering Dao call for update category with categoryId:{}",categoryId);
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescrip(categoryDto.getCategoryDescrip());
		
		Category category2 = this.categoryRepo.save(category);
		
		CategoryDto map = mapper.map(category2, CategoryDto.class);
		logger.info("Complete Dao call for update category with categoryId:{}",categoryId);
		return map;
	}

	//delete category
	
	@Override
	public void deletecategory(Long categoryId) {
		logger.info("Entering Dao call for delete category with categoryId:{}",categoryId);
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		this.categoryRepo.delete(category);
		logger.info("complete Dao call for delete category with categoryId:{}",categoryId);
	}

	// get single category by categoryId
	
	@Override
	public CategoryDto getCategory(Long categoryId) {
		
		logger.info("Entering Dao call for get single category with categoryId:{}",categoryId);
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		CategoryDto map = this.mapper.map(category, CategoryDto.class);
		
		logger.info("complete Dao call for get single category with categoryId:{}",categoryId);
		return map;
	}

	// get AllCategory
	
	@Override
	public List<CategoryDto> getAllCategory() {
		logger.info("Entering Dao call for get All categories ");
		List<Category> list = this.categoryRepo.findAll();
		List<CategoryDto> collect = list.stream().map((li)-> mapper.map(li, CategoryDto.class)).collect(Collectors.toList());
		logger.info("complete Dao call for get All categories ");
		return collect;
	}

}
