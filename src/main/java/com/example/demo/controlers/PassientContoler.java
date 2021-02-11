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

@RequestMapping(value="/patient")
public class PassientContoler {
	@Autowired
	PatientRepo patient;
	@Autowired
	LitRepo lit;
	@GetMapping(value="/allPatients")
	public String allPatients(Model model) {
		List<Patient> pa=patient.findAll();
		model.addAttribute("pa",pa);
		
		return "patients";
		
	}
	@RequestMapping(value="/addPatient", method=RequestMethod.GET)
	public String ajoutPatient(Model model) {
		Patient pa = new Patient();
		model.addAttribute("p",pa);
		return "ajoutPatient";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String savePatient(@Valid Patient p, BindingResult bindingResult, Model model){		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult.getAllErrors());
			model.addAttribute("pa", p);	
			
			return "redirect:addPatient";
		}

		Optional<Patient> pa= patient.findById(p.getCin());

		if (pa.isPresent()) {
		
			String msg ="la patient existe deja";
			model.addAttribute("error", msg);
			Patient paaa = new Patient();
			model.addAttribute("p",paaa);
			return "ajoutPatient";
		}else {

			patient.save(p);
		}
		return "redirect:allPatients";			
	}
	
	@RequestMapping(value="/suprimer")
	public String suprimer_patient(Model model, @RequestParam(name="id")long id){
		Patient pa= patient.findById(id).get();

		Lit li=lit.findByPatient(pa);
if(!(li==null)) {
		li.setChargé(false);	
		li.setPatient(null);
		lit.save(li);
}
		patient.deleteById(id);
		
		return "redirect:allPatients";		
	}
	@RequestMapping(value="/alllitnon")
	public String alllitnon(Model model, @RequestParam(name="id")long id){
		List<Object> pa=lit.findByChargéchambre();
		model.addAttribute("pa",pa);
		List<Lit> paa=lit.findByChargé(false);
		model.addAttribute("paa",paa);

		return "lits";
	}
	@RequestMapping(value="/patientDetail", method=RequestMethod.GET)
	public String afficherPatient(Model model,@RequestParam(name="id", defaultValue="1")long id) {
		Patient pa = patient.findById(id).get();
		model.addAttribute("p", pa);		
		System.out.println(pa);
		return "afficher_patient.html";		
	}
	@RequestMapping(value="/modifier", method=RequestMethod.POST)
	public String modifierClient(Model model, Patient c ,@RequestParam(name="id", defaultValue="1")long id) {
		Patient pa= patient.findById(id).get();
		pa.setMaladie(c.getMaladie());
		pa.setEtat(c.getEtat());
		pa.setNom(c.getNom());
		pa.setPrenom(c.getPrenom());
		pa.setAge(c.getAge());
		pa.setPhoneNumber(c.getPhoneNumber());
		pa.setEmail(c.getEmail());

	patient.save(pa);
	System.out.println(pa);
		
		return "redirect:allPatients";		
	}	
	
	@RequestMapping(value="/affecteraunlit", method=RequestMethod.POST)
	public String affecteraunlit(Model model, @RequestParam(name="id2", defaultValue="1")long id2,@RequestParam(name="id", defaultValue="1")long id) {

		Lit li= lit.findById(id2).get();
		Patient pa= patient.findById(id).get();


	li.setChargé(true);	
	li.setPatient(pa);
	lit.save(li);

		pa.setLit(li);

		pa.setEtat("c bon");

	patient.save(pa);


		
		return "redirect:allPatients";		
	}	
	
	@RequestMapping(value="/libererunlit", method=RequestMethod.GET)
	public String libererunlit(Model model ,@RequestParam(name="id", defaultValue="1")long id) {
System.out.println("yoyo");	
Patient pa= patient.findById(id).get();
Lit li=lit.findByPatient(pa);

	li.setChargé(false);	
	li.setPatient(null);
	lit.save(li);
		pa.setLit(null);

		pa.setEtat("en attente");

	patient.save(pa);


		
		return "redirect:allPatients";		
	}
}
