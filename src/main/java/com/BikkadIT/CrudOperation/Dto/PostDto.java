package com.BikkadIT.CrudOperation.Dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * @author : Rohini
 * @apiNote : In This PostDto, we have to select some specific entity from Post for security purpose
 * @version : V 2.7.5
 * @Param : Post
 * @see : Take 6 fields from Post
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	@NotEmpty
	@Size(min=5,max=100)
	private String title;
	
	@NotBlank
	@Size(min=10, max=500)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments= new HashSet<>();
	
}
