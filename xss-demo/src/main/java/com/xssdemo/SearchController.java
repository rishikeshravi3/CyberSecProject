package com.xssdemo;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SearchController {

    @GetMapping("/searchUnsecure")
    public String searchUnsecure(@RequestParam(name="query", required=false, defaultValue="") String query, Model model) {
        model.addAttribute("query", query);
        return "searchUnsecure";
    }
    
    @GetMapping("/searchSecure")
    public String searchSecure(@RequestParam(name="query", required=false, defaultValue="") String query, Model model) {
    	// Input Validation
        if (!isValidQuery(query)) {
            return "error";
        }
        
        // Sanitize and Encode the input
        String sanitizedQuery = Jsoup.clean(query, Safelist.none());
        String encodedQuery = HtmlUtils.htmlEscape(sanitizedQuery);
    	
        model.addAttribute("query", encodedQuery);
        return "searchSecure";
    }
    
    private boolean isValidQuery(String query) {
        // Allow only alphanumeric and space characters
        return query.matches("^[a-zA-Z0-9 ]+$");
    }
}