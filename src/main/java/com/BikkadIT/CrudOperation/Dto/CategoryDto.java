package com.BikkadIT.CrudOperation.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author : Rohini
 * @apiNote : In This CategoryDto, we have to select some specific entity from Category for security purpose
 * @version : V 2.7.5
 * @Param : Post
 * @see : take 3 fields from Category
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	@NotBlank
	private Integer categoryId;
	
	@NotBlank()
	private String categoryTitle;
	
	@NotBlank
	private String categoryDescrip;
	
}
