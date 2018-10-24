package com.soen.empower.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    /** The data source. */
    @Autowired
    private DataSource dataSource;


    /** The my authentication success handler. */
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * Configure global.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Roles - Teacher and Parent
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from users where username=?");
    }

        //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/teacher/**").access("hasRole('ROLE_TEACHER')")
                .antMatchers("/parent/**").access("hasRole('ROLE_PARENT')")
                .antMatchers("/user/**", "/message/**", "/friend/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_PARENT')")
                .antMatchers("/", "/login*", "/monitor/*", "/h2-console*").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}
