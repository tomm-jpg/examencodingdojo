package com.codingdojoexam.tom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojoexam.tom.models.Priority;
import com.codingdojoexam.tom.models.Project;
import com.codingdojoexam.tom.models.User;
import com.codingdojoexam.tom.services.ProjectService;
import com.codingdojoexam.tom.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectsController {
	
	@Autowired
	private UserService serv;
	
	@Autowired
	private ProjectService ps;

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session,
							Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		//Lista de proyectos a los que se unió mi usuario
		model.addAttribute("myProjects", ps.findProjects());
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/tasks/new")
	public String projectNew(HttpSession session,
							 @ModelAttribute("project") Project project, Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		List<User> listOfUsers = serv.findAllUsers();
		List<String> namesUsers = new ArrayList<>();
		for (User user : listOfUsers) {
			namesUsers.add(user.getName());
		}
		model.addAttribute("namesUsers", namesUsers);
		model.addAttribute("priority", Priority.Priorities);
		
		return "new.jsp";
		
	}
	
	@PostMapping("/tasks/create")
	public String projectCreate(@Valid @ModelAttribute("project") Project project,
								BindingResult result,
								HttpSession session,
								Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		if(result.hasErrors()) {
			List<User> listOfUsers = serv.findAllUsers();
			List<String> namesUsers = new ArrayList<>();
			for (User user : listOfUsers) {
				namesUsers.add(user.getName());
			}
			model.addAttribute("namesUsers", namesUsers);
			model.addAttribute("priority", Priority.Priorities);
			return "new.jsp";
		} else {
			ps.saveProject(project);
			//Agregar el proyecto nuevo a la lista de mis proyectos unidos
			User myUser = serv.findUser(userTemp.getId());
			serv.saveUser(myUser);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/tasks/{id}")
	public String queryTask(@PathVariable("id") Long id,
			  HttpSession session, Model model) {
		
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		
		Project project = ps.findProject(id);
		model.addAttribute("project", project);
		
		return "infotask.jsp";
		
		
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String projectEdit(@PathVariable("id") Long id,
							HttpSession session,
							@ModelAttribute("project") Project project, /*Generar un objeto vacío*/
							Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		//Obtener el objeto de Project
		Project projectToEdit = ps.findProject(id);
		
		//Medida de seguridad extra
		if(userTemp.getId() != projectToEdit.getLead().getId()) {
			return "redirect:/dashboard";
		}
		List<User> listOfUsers = serv.findAllUsers();
		List<String> namesUsers = new ArrayList<>();
		for (User user : listOfUsers) {
			namesUsers.add(user.getName());
		}
		model.addAttribute("namesUsers", namesUsers);
		model.addAttribute("priority", Priority.Priorities);
		model.addAttribute("project", projectToEdit); //Objeto que SI tiene info del proyecto
		return "edit.jsp";
		
	}
	
	@PutMapping("/tasks/update")
	public String projectUpdate(HttpSession session,
								@Valid @ModelAttribute("project") Project project, /*Objeto si tiene info*/
								BindingResult result, Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		if(result.hasErrors()) {
			List<User> listOfUsers = serv.findAllUsers();
			List<String> namesUsers = new ArrayList<>();
			for (User user : listOfUsers) {
				namesUsers.add(user.getName());
			}
			model.addAttribute("namesUsers", namesUsers);
			model.addAttribute("priority", Priority.Priorities);
			return "edit.jsp";
		} else {
			//Debemos de agregar de nuevo los usuarios que se unieron al proyecto
			//project -> Objeto basado en el formulario.  thisProject -> Objeto extraido de BD
			Project thisProject = ps.findProject(project.getId());
			String asignee = thisProject.getAsignee(); //Lista de usuarios que se unieron al proyecto
			project.setAsignee(asignee);
			
			//Guardamos proyecto
			ps.saveProject(project);
			return "redirect:/dashboard";
		}
		
	} 
	
	@DeleteMapping("/tasks/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		
		ps.deleteProject(id);
		return "redirect:/dashboard";
		
	}
	
}
