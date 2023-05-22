package com.BikkadIT.CrudOperation.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.CrudOperation.Dto.UserDto;
import com.BikkadIT.CrudOperation.Exception.ApiException;
import com.BikkadIT.CrudOperation.Payloads.JwtAuthRequest;
import com.BikkadIT.CrudOperation.security.JwtAuthResponse;
import com.BikkadIT.CrudOperation.security.JwtTokenHelper;
import com.BikkadIT.CrudOperation.serviceI.UserServiceI;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceI userServiceI;
	
	// login user
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception{
		logger.info("Request entering for the create Token ");
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response= new JwtAuthResponse();
		response.setToken(token);
		logger.info("Request completed for the create token ");
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}
	
	//Authenticate user
	@PostMapping("/authenticate")
	private void authenticate(String username, String password) throws ApiException {
		
		logger.info("Request entering for the authenticate Token ");
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username, password);
		
		try {
		
		this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e) {
			
			System.out.println("Invalid details");
			throw new ApiException("Invalid Username and password");
		}
		logger.info("Request completed for the authenticate token ");
	}
	
	// register user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		logger.info("Request occuring for register user");
		UserDto registerUser = this.userServiceI.registerUser(userDto);
		logger.info("Request completed for the register user ");
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
}
