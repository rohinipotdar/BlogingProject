package com.BikkadIT.CrudOperation.Dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author : Rohini
 * @apiNote : In This UserDto, we have to select some specific entity from User for security purpose
 * @version : V 2.7.5
 * @Param : Post
 * @see : take 6 fields from User
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
		private Long userId;

		private int userAge;

		@NotEmpty
		@Size(min=4,message = "User name min 4 characters..")
		private String userName;
		
		@NotNull
		private String userAdd;
		
		@Email
		@Size(message = "User email id is not valid..")
		private String email;
		
		@NotEmpty
		@Size(min=3, max=10, message = "Password must have min 3 and max 10 characters")
		private String password;
		
		private Set<RoleDto> roles=new HashSet<>();

		
	}


