package com.BikkadIT.CrudOperation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.CrudOperation.model.Category;
 
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
