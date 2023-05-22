package com.BikkadIT.CrudOperation.Controller;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import com.BikkadIT.CrudOperation.Dto.UserDto;
import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.Payloads.ApiResponce;
import com.BikkadIT.CrudOperation.serviceI.UserServiceI;

/*
 * @author : Rohini
 * @apiNote : In This UserController requests handle from server like create, update, delete, get and getAll
 * @version : V 2.7.5
 * @Param : User
 * @see :
 */

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserServiceI serviceI;
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	//create user
	@PostMapping("/User")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		logger.info("Request entering for the create user ");
		UserDto createUser = serviceI.createUserDto(userDto);
		logger.info("Request completed for the create user ");
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
		
	}
	
	//update user
	@PutMapping("/User/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable Long userId){
		
		logger.info("Request entering for the update user with userId :{}", userId);
		UserDto updateUser = serviceI.updateUserDto(user, userId);
		logger.info("Completed request for the update user with userId :{}", userId);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.CREATED);
		
	}
	
	//get user
	@GetMapping("/User/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
		logger.info("Request entering the get single User with userId :{}", userId);
		UserDto newUser = serviceI.getUserDto(userId);
		logger.info("Completed request for get single User with userId :{}", userId);
		return new ResponseEntity<UserDto>(newUser,HttpStatus.OK);
		
	}
	
	//get All user
	@GetMapping("/Users")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		logger.info("Request entering for get All Users");
		List<UserDto> list = serviceI.getAllUserDtos();
		logger.info("Complete request for get All Users");
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	//delete user
	@DeleteMapping("/users/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable Long userId){
		logger.info("Request entering for Delete User with userId :{}", userId);	
		serviceI.deleteUserDto(userId);
		logger.info("Complete request for Delete User with userId :{}", userId);
		return new ResponseEntity<ApiResponce>(new ApiResponce(AppConstants.USER_DELETE,true),HttpStatus.OK);
		
	}

}
