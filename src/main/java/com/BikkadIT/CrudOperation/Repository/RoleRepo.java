package com.BikkadIT.CrudOperation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BikkadIT.CrudOperation.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	
	
}
