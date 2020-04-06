package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import com.example.demo.model.Users;
import com.example.demo.service.JwtResponse;
import com.example.demo.service.JwtUtil;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtutil;
	@GetMapping(path="aliens" )//,produces= {"application/xml","application/json"}
	public List<Alien> getAliens() {
		
		
		return repo.findAll();
	}
	@GetMapping("alien/{aid}")
	public Alien getAlien(@PathVariable("aid") int aid) {
		
		return repo.findById(aid).orElse(new Alien(aid+" Not found"));
	}
	@PostMapping(path="alien")//,consumes= {"application/xml"}
	public Alien PostAlien(@RequestBody Alien alien) {
		
		return repo.save(alien);
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Users userrequest)throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userrequest.getUsername(), userrequest.getPassword())
					);
		} catch (BadCredentialsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final UserDetails userdetails=userDetailsService.loadUserByUsername(userrequest.getUsername());
	final String jwt=jwtutil.generateToken(userdetails);
	
	
	return ResponseEntity.ok(new JwtResponse(jwt));
			}
	
	
	
	
	
	
	

}
