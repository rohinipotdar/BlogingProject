package com.BikkadIT.CrudOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BikkadIT.CrudOperation.Helper.AppConstants;
import com.BikkadIT.CrudOperation.Repository.RoleRepo;
import com.BikkadIT.CrudOperation.model.Role;


@SpringBootApplication
public class CrudOperationNewApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo repo;
	
	
		public static void main(String[] args) {

			SpringApplication.run(CrudOperationNewApplication.class, args);

		}

		@Override
		public void run(String... args) throws Exception {
			System.out.println(this.encoder.encode("rani123"));
			
			try {
				
				Role r1=new Role();
				r1.setId(AppConstants.ADMIN_USER);
				r1.setName("ADMIN_USER");
				
				Role r2=new Role();
				r2.setId(AppConstants.NORMAL_USER);
				r2.setName("NORMAL_USER");
				
				List<Role> list = List.of(r1,r2);
				
				List<Role> roles = this.repo.saveAll(list);
				
				roles.forEach(r->{
					System.out.println(r.getName());
					});
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	}

