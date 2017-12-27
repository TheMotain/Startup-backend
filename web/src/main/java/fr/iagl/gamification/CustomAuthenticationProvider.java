package fr.iagl.gamification;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import fr.iagl.gamification.services.TeacherService;

/**
 * Authentification personnalisée
 *
 * @author Hélène MEYER
 *
 */
@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(CustomAuthenticationProvider.class);
	
	/**
	 * service du professeur
	 */
	@Autowired
	private TeacherService teacherService;
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }

	@Override
	public Authentication authenticate(Authentication authentication) {
	 	String email = authentication.getName();
		if (authentication.getCredentials() != null) {
			String password = authentication.getCredentials().toString();
	         
			try {
				if (teacherService.teacherExists(email, password)) {
				    LOG.info("Professeur identifié");
					return new UsernamePasswordAuthenticationToken(
				      email, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER")));
				} else {
					 throw new
				      BadCredentialsException("Autentification incorrecte");
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				LOG.warn("Erreur lors de l'appel au service");
			} 
		}
       
		throw new
	      BadCredentialsException("Autentification incorrecte");
	}
}
