package com.example.demo.controlers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"username","role"})

public class ErrorControler implements ErrorController {
	 @RequestMapping("/error")
	    public String handleError() {
	        //do something like logging
	        return "error";
	    }

	    public String getErrorPath() {
	        return null;
	    }
}
