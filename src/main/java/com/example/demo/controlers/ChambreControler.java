
package com.example.demo.controlers;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.Cambre;
import com.example.demo.dao.LitRepo;
import com.example.demo.dao.PatientRepo;
import com.example.demo.entities.Chambre;
import com.example.demo.entities.Lit;
import com.example.demo.entities.Patient;



@Controller

@SessionAttributes({"username","role"})

@RequestMapping(value="/chambre")
public class ChambreControler {
	@Autowired
	Cambre cr;
	@Autowired
	LitRepo lit ;
	@Autowired
	PatientRepo patient;
	
	@RequestMapping(value="/liste")
	public String afficherChambres(Model model) {
		List<Chambre> liste = cr.findAll();
for (Chambre chambre : liste) {
	 int z=  lit.countlitnonchargé(chambre.getId());
	 chambre.setLitnoncharge(z);
}
		
		model.addAttribute("liste",liste);
		
		return "tous-chambres.html";
	}
	@RequestMapping(value="/addChambre", method=RequestMethod.GET)
	public String ajoutChambre(Model model) {
		Chambre ch = new Chambre();
		model.addAttribute("c",ch);
		return "ajout-chambre.html";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveChambret(@Valid Chambre c, BindingResult bindingResult, Model model, Boolean flase){		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult.getAllErrors());
			model.addAttribute("chambre", c);	
			
			
			
			return "redirect:addChambre";
		}
		List<Chambre> kr =cr.findByNumero(c.getNumero());
		if (kr.isEmpty()) {
		
			cr.save(c);
			for(int i = 0;i<c.getCapacite();i++ ) {
				Lit l = new Lit();

				l.setChambre(c);
				l.setChargé(false);;

					lit.save(l);
				}
		}else {
			String msg ="la chambre existe deja";
			model.addAttribute("error", msg);
			Chambre ch = new Chambre();
			model.addAttribute("c",ch);
			return "ajout-chambre.html";			

		}
		return "redirect:liste";			
	}
	
	@RequestMapping(value="/suprimer")
	public String suprimer_chambre(Model model, @RequestParam(name="id")long id){
		Chambre cha = cr.findById(id).get();
	List<Lit> li=	lit.findByChambre(cha);
	List<Lit> lii =li;
	lit.deleteAll(li);
	cr.deleteById(id); 
	for (Lit l : lii) {
		Lit lista=	lit.litdepatient(l.getPatient());
		System.out.println("rgzgzeg");
		if(l.getPatient()!=null) {
			Patient pa = new Patient();
pa.setAge(l.getPatient().getAge());
pa.setCin(l.getPatient().getCin());
pa.setEmail(l.getPatient().getEmail());
pa.setMaladie(l.getPatient().getMaladie());
pa.setPhoneNumber(l.getPatient().getPhoneNumber());
pa.setNom(l.getPatient().getNom());
pa.setPrenom(l.getPatient().getPrenom());
		patient.save(pa);
}
	}
	
		return "redirect:liste";		
	}
	@RequestMapping(value="/afficherChambre", method=RequestMethod.GET)
	public String afficherChambre(Model model,@RequestParam(name="id", defaultValue="1")long id) {
		Chambre c = cr.findById(id).get();
		model.addAttribute("chambre", c);
		return "afficher_chambre.html";		
	}
	@RequestMapping(value="/modifier", method=RequestMethod.POST)
	public String modifierClient(Model model, Chambre c,@RequestParam(name="id", defaultValue="1")long id, Boolean flase) {

		Chambre k= cr.findById(id).get();

		int x = lit.countlit(id);
		System.out.println("fdgdfjhkgfdkg"+x);
if(x>c.getCapacite())
{
	for(int i=0;i<x-c.getCapacite();i++) {
	List<Lit> li=	lit.findByChambre(c);
	lit.delete(li.get(0) );
	}
}else if( x<c.getCapacite()) {
	
	for(int i = 0;i<c.getCapacite()-x;i++ ) {
		Lit l = new Lit();

		l.setChambre(c);

			lit.save(l);

		}
	
	
	
}
		k.setCapacite(c.getCapacite());
		k.setNumero(c.getNumero());
		System.out.println(c);
		
	cr.save(k);
		
		return "redirect:liste";		
	}	
	

	
	
}








