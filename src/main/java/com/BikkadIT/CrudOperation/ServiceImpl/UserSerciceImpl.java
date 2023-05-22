package com.BikkadIT.CrudOperation.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BikkadIT.CrudOperation.Controller.CategoryController;
import com.BikkadIT.CrudOperation.Dto.UserDto;
import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.Repository.RoleRepo;
import com.BikkadIT.CrudOperation.Repository.UserRepository;
import com.BikkadIT.CrudOperation.model.Role;
import com.BikkadIT.CrudOperation.model.User;
import com.BikkadIT.CrudOperation.serviceI.UserServiceI;

@Service
public class UserSerciceImpl implements UserServiceI {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	//create User
	
	@Override
	public UserDto createUserDto(UserDto userDao) {
		logger.info("Initiating Dao call for create User");
	User user = mapper.map(userDao, User.class);
	
	User user2 = repository.save(user);
	
	 UserDto userDao2 = mapper.map(user2, UserDto.class);
	 logger.info("Complete Dao call for create User");
		return userDao2;
	}

	// get user
	
	@Override
	public UserDto getUserDto(Long userId) {
		logger.info("Initiating Dao call for get single User by userID : {}",userId);
		User user = repository.findById(userId).orElseThrow(()->new RuntimeException(AppConstants.NOT_FOUND+userId));
		
		UserDto userDto = mapper.map(user, UserDto.class);
		logger.info("Initiating Dao call for get single User by userID : {}",userId);
		return userDto;
	}

	//update user
	
	@Override
	public UserDto updateUserDto(UserDto userDto, Long userId) {
		logger.info("Initiating Dao call for update User by userID : {}",userId);
		User user1 = repository.findById(userId).orElseThrow(()->new RuntimeException(AppConstants.NOT_FOUND+userId));
				
		user1.setUserName(userDto.getUserName());
		user1.setUserAdd(userDto.getUserAdd());
		user1.setUserAge(userDto.getUserAge());
		
		User user2 = repository.save(user1);
		logger.info("complete Dao call for update User by userID : {}",userId);
		return mapper.map(user2, UserDto.class);
	}

	//get All users
	
	@Override
	public List<UserDto> getAllUserDtos() {
		logger.info("Initiating Dao call for get All Users by userID ");
		List<User> list = repository.findAll();
		
		List<UserDto> list2 = list.stream().map((users)->mapper.map(users, UserDto.class)).collect(Collectors.toList());
		logger.info("Complete Dao call for get All Users by userID ");
		return list2;
	}

	//delete user
	
	@Override
	public String deleteUserDto(Long userId) {
		logger.info("Entering Dao call for delete the User by userID : {}",userId);
		repository.findById(userId).orElseThrow(()->new RuntimeException(userId+AppConstants.NOT_FOUND));
			
		repository.deleteById(userId);
		logger.info("complete Dao call for delete the User by userID : {}",userId);
		return AppConstants.USER_DELETE;
	}

	//register the user
	@Override
	public UserDto registerUser(UserDto userDto) {
		
		User user = this.mapper.map(userDto, User.class);
		logger.info("Entering Dao call for register the User");
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		// role
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User newuser = this.repository.save(user);
		
		UserDto userDto2 = this.mapper.map(newuser, UserDto.class);
		logger.info("complete Dao call for register the User");
		return userDto2;
	}

}
