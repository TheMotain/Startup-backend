package fr.iagl.gamification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.iagl.gamification.services.TeacherService;

public class CustomAuthenticationProviderTest extends SpringIntegrationTest{

	@Mock
	private TeacherService teacherService;
	
	@InjectMocks
	private CustomAuthenticationProvider custom;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAuthenticateOK() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(auth.getName()).thenReturn("name");
		Credential cred = Mockito.mock(Credential.class);
		Mockito.when(auth.getCredentials()).thenReturn(cred);
		Mockito.when(cred.toString()).thenReturn("password");
		Mockito.when(teacherService.teacherExists("name", "password")).thenReturn(true);
		
		assertNotNull(custom.authenticate(auth));
	}
	
	@Test(expected=BadCredentialsException.class)
	public void testAuthenticateKO() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(auth.getName()).thenReturn("name");
		Credential cred = Mockito.mock(Credential.class);
		Mockito.when(auth.getCredentials()).thenReturn(cred);
		Mockito.when(cred.toString()).thenReturn("password");
		Mockito.when(teacherService.teacherExists("name", "password")).thenReturn(false);
		
		custom.authenticate(auth);
	}

	@Test(expected=BadCredentialsException.class)
	public void testAuthenticateKOServiceThrowException() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(auth.getName()).thenReturn("name");
		Credential cred = Mockito.mock(Credential.class);
		Mockito.when(auth.getCredentials()).thenReturn(cred);
		Mockito.when(cred.toString()).thenReturn("password");
		Mockito.when(teacherService.teacherExists("name", "password")).thenThrow(NoSuchAlgorithmException.class);
		
		custom.authenticate(auth);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void testAuthenticateKOServiceThrowException2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(auth.getName()).thenReturn("name");
		Credential cred = Mockito.mock(Credential.class);
		Mockito.when(auth.getCredentials()).thenReturn(cred);
		Mockito.when(cred.toString()).thenReturn("password");
		Mockito.when(teacherService.teacherExists("name", "password")).thenThrow(UnsupportedEncodingException.class);
		
		custom.authenticate(auth);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void testAuthenticateKOCredentialsNull() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(auth.getName()).thenReturn("name");
		
		custom.authenticate(auth);
	}
	
	@Test
	public void testSupportsOK() {
		assertTrue(custom.supports(UsernamePasswordAuthenticationToken.class));
	}
	
	@Test
	public void testSupportsKO() {
		assertFalse(custom.supports(UsernamePasswordAuthenticationFilter.class));
	}
}

