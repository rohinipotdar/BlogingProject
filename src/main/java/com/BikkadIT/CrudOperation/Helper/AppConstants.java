package com.BikkadIT.CrudOperation.Helper;

/*
 * @author : Rohini
 * @apiNote : This is AppConstant
 * @version : V 2.7.5
 * @Param : 
 * @see : In This we have to write all constants which are in this application.
 */

public class AppConstants {
	
	public static final String[] PUBLIC_URLS= {
			"/api/v1/auth/login",
			"v3/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};
	
	public static final String USER_DELETE="User deleted successfully";
	
	public static final String NOT_FOUND="User not found";
	
	public static final String PAGE_NUMBER="10";
	
	public static final String PAGE_SIZE="1";
	
	public static final String SORT_BY="postId";
	
	public static final Long NORMAL_USER=2L;

	public static final Long ADMIN_USER=1L;
}
