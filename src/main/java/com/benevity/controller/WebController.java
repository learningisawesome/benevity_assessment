package com.benevity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.benevity.model.Role;
import com.benevity.model.User;
import com.benevity.service.UserServiceImpl;

@Controller
public class WebController {

	@Autowired
	private UserServiceImpl service;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}

	@GetMapping("/users/filter")
	public String filterByFamilyName(@RequestParam(value="familyName",required=false) String familyName, Model model) {
		User user = service.getUserByFamilyName(familyName);
		List<User> listUsers = new ArrayList();
		listUsers.add(user);
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);

		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		service.deleteUser(id);
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
}
