package com.xssdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
	@GetMapping("/dashboard")
	public String dashboard(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "dashboard";
	}
}