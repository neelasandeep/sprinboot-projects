package com.example.demo;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;





@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	//google cloud to provide security
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.
//		csrf().disable().
//		authorizeRequests().antMatchers("/login").
//		permitAll().
//		anyRequest().authenticated();
//		
//		
//	}
//	
	
	
	
	
	
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
    private JwtFilter jwtFilter;
	@Bean
	public AuthenticationProvider authprovider() {
		
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
		
	}
//own login form
  @Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// for rest services
	http.
	csrf().disable().
	authorizeRequests().antMatchers("/authenticate").
	permitAll().anyRequest().authenticated().and().
	
	sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	  
	  
	  //for UI part
//	http.
//		csrf().disable().
//		authorizeRequests().antMatchers("/login","/authenticate").
//		permitAll().anyRequest().authenticated().and().
//		formLogin().
//		loginPage("/login").
//		and().
//		logout().invalidateHttpSession(true).
//		clearAuthentication(true).
//		logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
//		logoutSuccessUrl("/logout-success").
//		permitAll();
//	
//		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		
//		
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	
	
	
	
	//if no data base needed
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("sandeep").password("1234").roles("USER").build());
//		return new InMemoryUserDetailsManager(users);
//	}

}
