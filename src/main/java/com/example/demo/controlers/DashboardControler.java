package com.example.demo.controlers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.Cambre;
import com.example.demo.dao.DoctorRepo;
import com.example.demo.dao.LitRepo;
import com.example.demo.dao.PatientRepo;
import com.example.demo.entities.Chambre;
import com.example.demo.entities.Lit;
import com.example.demo.entities.LoginForm;
import com.example.demo.entities.Patient;

@Controller
@SessionAttributes({"username","role"})


@RequestMapping(value="/")
public class DashboardControler {
	@Autowired

	LitRepo lit ;
	@Autowired

	Cambre chambre;
	@Autowired

	DoctorRepo doctor ;
	@Autowired

	PatientRepo patient ;
	
	
	@GetMapping(value="/")	
	public String testMestod(HttpServletRequest request,Model model){
		System.out.println("kfl");
		int c1=  lit.countalllit();
		int c2=  chambre.countallchambre();
		int c3=  doctor.countalldoctor();
		int c4=  patient.countallpattient();
		System.out.println("effzzezezzfefzefzeffz"+c1+c2+c3+c4);
		model.addAttribute("lit",c1);
		model.addAttribute("chambre",c2);
		model.addAttribute("doctor",c3);
		model.addAttribute("patient",c4);
	  if(request.getSession()!=null) {
		
		  return "all.html";
	  }
	   return "redirect:signin";
	}
	
	

	
	@RequestMapping(value="/signin")
	public String login() {
	
		return "login.html";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String savePatient( @ModelAttribute(name="LoginForm") LoginForm loginform,Model model){		
		
	String username =loginform.getUsername();
	String email =loginform.getEmail();

	String password = loginform.getPassword();
	if("admin@gmail.com".equals(email) && "admin".equals(password)) {
		
model.addAttribute("username",username);
model.addAttribute("role","admin");

		return "all.html";			
	}
	if("doctor@gmail.com".equals(email) && "doctor".equals(password)) {
		
		model.addAttribute("username",username);
		model.addAttribute("role","doctor");

				return "all.html";			
			}
	
	int c1=  lit.countalllit();
	int c2=  chambre.countallchambre();
	int c3=  doctor.countalldoctor();
	int c4=  patient.countallpattient();
	System.out.println("effzzezezzfefzefzeffz"+c1+c2+c3+c4);
	model.addAttribute("lit",c1);
	model.addAttribute("chambre",c2);
	model.addAttribute("doctor",c3);
	model.addAttribute("patient",c4);
	return"login.html";
	}
	
	@RequestMapping(value = "/sign")
	public String logout(HttpServletRequest request) {
		System.out.println("hghgjdfhgkhjg");
	    HttpSession session = request.getSession();
	        session.invalidate();
	    
	    return "redirect:signin";  //Where you go after logout here.
	}
	
	
}
