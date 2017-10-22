package fr.iagl.gamification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello-world")
public class HomeController {
	
	private static final String template = "Hello, %s!";
   
    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new ModelAndView(String.format(template, name));
    }
}
