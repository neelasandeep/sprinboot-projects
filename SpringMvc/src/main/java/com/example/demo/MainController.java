package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Controller
public class MainController {
    @Autowired
	AlienRepo repo;
	
	@ModelAttribute
	public void modeldata(Model m ) {
		m.addAttribute("name","Aliens");
		
	}
	@RequestMapping("/")
	public String showhomepage() {
		return "index";
	}
	
	
	@PostMapping("addalien")
	public String addAlien(@ModelAttribute("alien") Alien a) {
		
		repo.save(a);
		return "result";
	}
	@GetMapping("getAliens")
	public String getAliens(Model m) {
		m.addAttribute("result",repo.findAll());
		
		return "showAliens";
	}
	@PostMapping("getAlien")
	public String getAlien(@RequestParam("aid") int a,Model m ) {
		
		m.addAttribute("result",repo.getOne(a));
		return "showAliens";
	}
	@PostMapping("removeAlien")
	public String removeAlien(@RequestParam("aid") int a,Model m ) {
		Alien ailen=repo.find(a);
		if(ailen!=null) {
		repo.deleteById(a);
		m.addAttribute("result","Alien "+ailen+"deleted successfully");}
		else {
			m.addAttribute("result","Alien with "+a+"doesnt exists");
		}
		return "showAliens";
	}
	@PostMapping("byNameOfAlien")
	public String byNameOfAlien(@RequestParam("aname") String a,Model m ) {
		
		m.addAttribute("result",repo.findByAnameOrderByAidDesc(a));
		return "showAliens";
	}
	@RequestMapping("login")
	public String loginpage() {
		return "login";
	}
	@RequestMapping("logout-success")
	public String logoutpage() {
		return "logout";
	}

}
