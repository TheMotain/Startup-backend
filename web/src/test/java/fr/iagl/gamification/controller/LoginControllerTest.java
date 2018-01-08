package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.services.TeacherService;

public class LoginControllerTest extends SpringIntegrationTest{

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@InjectMocks
	private LoginController controller;
	
	@Mock
	protected TeacherService teacherService;

	 @Before
     public void onSetUp() {
		 this.mockMvc = MockMvcBuilders
                 .webAppContextSetup(this.wac)
                 .build();
         Authentication auth = new UsernamePasswordAuthenticationToken(
			      "string@s.com", "string", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER")));
         
         SecurityContextHolder.getContext().setAuthentication(auth);
         MockitoAnnotations.initMocks(this);
     }

	 @After
	 public void tearDown() {
		 SecurityContextHolder.clearContext();
	 }
	 
	 @Test
	 public void testPrincipal() {
		 String teacher = (new LoginController()).getPrincipal();
		 assertEquals("string@s.com", teacher);
	 }
	 
	 @Test
	 public void testUserDetailsPrincipal(){
		 UserDetails usr = User.withUsername("name")
		  	.password("password")
		  	.roles("ROLE").build();
		 Authentication auth = new UsernamePasswordAuthenticationToken(usr, null);
        
        SecurityContextHolder.getContext().setAuthentication(auth);
		 String teacher = (new LoginController()).getPrincipal();
		 assertEquals("name", teacher);
	 }
	 
	 @Test
	 public void testGetTeacher() {
		 Mockito.when(teacherService.getTeacherByMail(Mockito.anyString())).thenReturn(Mockito.mock(TeacherModel.class));
		 TeacherModel teacher = controller.getTeacher();
		 assertNotNull(teacher);
	 }
	 
	 @Test
	 public void testLoginTeacherMauvaiseAuth() {
		 ResponseEntity<TeacherModel> res = controller.loginTeacher();
		 assertNotNull(res);
		 assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	 }
	 
	 @Test
	 public void testLoginTeacherOK() {
		 Mockito.when(teacherService.getTeacherByMail(Mockito.anyString())).thenReturn(Mockito.mock(TeacherModel.class));
		 ResponseEntity<TeacherModel> res = controller.loginTeacher();
		 assertNotNull(res);
		 assertEquals(HttpStatus.OK, res.getStatusCode());
	 }
	 
	 @Test
	 public void testLogoutWhenLoginBeforeOK() {
		 HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		 controller.logoutPage(request, null);
		 assertNull(controller.getPrincipal());
	 }
	 
	 @Test
	 public void testLogoutWhenNotLoginBeforeOK() {
		 HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		 controller.logoutPage(request, null);
		 controller.logoutPage(request, null);
		 assertNull(controller.getPrincipal());
	 }
}
