package com.codingdojoexam.tom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojoexam.tom.models.Project;
import com.codingdojoexam.tom.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	//SELECT * FROM projects JOIN users_has_projects ON users_has_projects.project_id = projects.id WHERE users_has_projects.user_id != usuario
	//Lista de proyectos que incluyan un usuario 
	
	List<Project> findAll();

}
