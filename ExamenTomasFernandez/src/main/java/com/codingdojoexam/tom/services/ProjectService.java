package com.codingdojoexam.tom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojoexam.tom.models.Project;
import com.codingdojoexam.tom.models.User;
import com.codingdojoexam.tom.repositories.ProjectRepository;
import com.codingdojoexam.tom.repositories.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository pr;
	
	@Autowired
	private UserRepository ur;
	
	public Project saveProject(Project newProject){
		return pr.save(newProject);
	}
	
	/*Método que regresa los proyectos en los que mi usuario se unió*/
	public List<Project> findProjects(){
		return pr.findAll();
	}
	
	/*Método que me regrese un usuario en base al id*/
	public User findUser(Long id) {
		return ur.findById(id).orElse(null);
	}
	
	/*Método que me regresa un proyecto en base al id*/
	public Project findProject(Long id) {
		return pr.findById(id).orElse(null);
	}
	
	public void deleteProject(Long projectId) {
		pr.deleteById(projectId);
	}
}
