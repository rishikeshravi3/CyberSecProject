package com.xssdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @GetMapping("/searchUnsecure")
    public String searchUnsecure(@RequestParam(name="query", required=false, defaultValue="") String query, Model model) {
        model.addAttribute("query", query);
        return "searchUnsecure";
    }
    @GetMapping("/searchSecure")
    public String searchSecure(@RequestParam(name="query", required=false, defaultValue="") String query, Model model) {
        model.addAttribute("query", query);
        return "searchSecure";
    }
}