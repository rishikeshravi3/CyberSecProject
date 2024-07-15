package com.xssdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
	@GetMapping("/dashboardSecure")
	public String dashboardSecure(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "dashboardSecure";
	}
	@GetMapping("/dashboardUnsecure")
	public String dashboardUnsecure(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "dashboardUnsecure";
	}
}
