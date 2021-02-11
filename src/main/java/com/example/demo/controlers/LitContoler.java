package com.example.demo.controlers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.LitRepo;
import com.example.demo.dao.PatientRepo;
import com.example.demo.entities.Lit;
import com.example.demo.entities.Patient;



@Controller
@SessionAttributes({"username","role"})

@RequestMapping(value="/lit")
public class LitContoler {
	@Autowired
	LitRepo lit ;
	@GetMapping(value="/allLits")
	public String allPatients(Model model) {
		List<Lit> pa=lit.findAll();
		model.addAttribute("pa",pa);
		
		return "patients";
		
	}
	@GetMapping(value="/allLitsnocharge")
	public String litnonchargé(Model model, boolean chargé) {
		
		
		List<Object> pa=lit.findByChargéchambre();
		System.out.println("dlhjkfsdf"+pa);
		model.addAttribute("pa",pa);
		
		return "lits";
		
	}
	@RequestMapping(value="/addPatient", method=RequestMethod.GET)
	public String ajoutPatient(Model model) {
		Patient pa = new Patient();
		model.addAttribute("p",pa);
		return "ajoutPatient";
	}

	

}
