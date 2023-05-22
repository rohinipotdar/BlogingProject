package com.BikkadIT.CrudOperation.serviceI;

import java.util.List;

import com.BikkadIT.CrudOperation.Dto.UserDto;

public interface UserServiceI {
	
	public UserDto registerUser(UserDto userDto);
	
	public UserDto createUserDto(UserDto userDao);
	
	public UserDto getUserDto(Long userId);
	
	public UserDto updateUserDto(UserDto userDto, Long userId);
	
	public List<UserDto> getAllUserDtos();
	
	public String deleteUserDto(Long userId);

}
