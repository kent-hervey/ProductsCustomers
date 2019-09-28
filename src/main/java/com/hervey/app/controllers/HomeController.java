package com.hervey.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hervey.app.services.ApiService;

@Controller
public class HomeController {
	private final ApiService apiService;

	public HomeController(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping("")
	public String showHome() {

		return "home.jsp";
	}

}
