package fr.iagl.gamification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration pour la connexion
 *
 * @author Hélène MEYER
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * authentification personnalisé
	 */
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	/**
	 * Configure l'authentification personnalisé
	 * 
	 * @param auth 
	 */
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
			.antMatchers("/swagger-ui.html").permitAll()
		.and()
			.formLogin()
	        .loginPage("/login")
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .permitAll()
	        .and()
	    .logout()
	    	.logoutUrl("/logout")
	        .permitAll()
	        .and().csrf().disable();
	}
	
}

