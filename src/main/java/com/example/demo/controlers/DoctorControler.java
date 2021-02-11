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

import com.example.demo.dao.DoctorRepo;
import com.example.demo.dao.PatientRepo;
import com.example.demo.entities.Chambre;
import com.example.demo.entities.Docteur;
import com.example.demo.entities.Patient;



@Controller
@SessionAttributes({"username","role"})

@RequestMapping(value="/doctor")
public class DoctorControler {
	@Autowired
	DoctorRepo doctor;
	
	@GetMapping(value="/allDoctors")
	public String allDoctors(Model model) {
		List<Docteur> pa=doctor.findAll();
		model.addAttribute("pa",pa);
		
		return "doctors";
		
	}
	@RequestMapping(value="/addDocotor", method=RequestMethod.GET)
	public String ajoutDoctor(Model model) {
		Docteur pa = new Docteur();
		model.addAttribute("p",pa);
		return "ajoutDoctor";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveDoctor(@Valid Docteur p, BindingResult bindingResult, Model model){		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult.getAllErrors());
			model.addAttribute("pa", p);	
			
			return "redirect:addDoctor";
		}

		Optional<Docteur> pa= doctor.findById(p.getCin());

		if (pa.isPresent()) {
		
			String msg ="le docteur existe deja";
			model.addAttribute("error", msg);
			
			Docteur d = new Docteur();
			model.addAttribute("p",d);
			return "ajoutDoctor.html";	
		}else {

			doctor.save(p);
		}
		return "redirect:allDoctors";			
	}
	
	@RequestMapping(value="/suprimer")
	public String suprimer_docotor(Model model, @RequestParam(name="id")long id){
		doctor.deleteById(id);
		
		return "redirect:allDoctors";		
	}
	@RequestMapping(value="/DoctorDetail", method=RequestMethod.GET)
	public String afficherDoctor(Model model,@RequestParam(name="id", defaultValue="1")long id) {
		Docteur pa = doctor.findById(id).get();
		model.addAttribute("p", pa);		
		System.out.println(pa);
		return "Afficher_Doctor.html";		
	}
	@RequestMapping(value="/modifier", method=RequestMethod.POST)
	public String modifierDoctor(Model model, Docteur c ,@RequestParam(name="id", defaultValue="1")long id) {
		Docteur pa= doctor.findById(id).get();
		pa.setDepartement(c.getDepartement());
		pa.setJob(c.getJob());
		pa.setNom(c.getNom());
		pa.setPrenom(c.getPrenom());
		pa.setAge(c.getAge());
		pa.setPhoneNumber(c.getPhoneNumber());
		pa.setEmail(c.getEmail());

		doctor.save(pa);
	System.out.println(pa);
		
		return "redirect:allDoctors";		
	}	
	

}
