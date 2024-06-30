package com.codingdojoexam.tom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojoexam.tom.models.Project;
import com.codingdojoexam.tom.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	//Consulta que me regrese un User en base a su email
	User findByEmail(String email); //SELECT * FROM users WHERE email = correito
	
}
