package com.soen.empower.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	

	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{	
		// Roles - Teacher and Parent
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery(
			"select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users where username=?");
	}
	
	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/teacher/**").access("hasRole('ROLE_TEACHER')")
		.antMatchers("/parent/**").access("hasRole('ROLE_PARENT')")
		.antMatchers("/","/login*", "/monitor/*").permitAll()
		.and()
        .formLogin()
        .loginPage("/login").successHandler(myAuthenticationSuccessHandler)
        .permitAll()
        .and()
        .logout()
        .permitAll();
	}

}
