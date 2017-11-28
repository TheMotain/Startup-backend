package fr.iagl.gamification.validator;

import java.util.Date;

import org.junit.Test;

import fr.iagl.gamification.AbstractFormTest;

public class StudentFormTest extends AbstractFormTest {
	
	@Test
	public void testFirstNameIsNotNull(){
		StudentForm form = new StudentForm();
		form.setFirstName(null);
		form.setLastName("A");
		form.setBorn(new Date());
		validateFailure(form);
	}
	
	@Test
	public void testFirstNameIsNotEmpty(){
		StudentForm form = new StudentForm();
		form.setFirstName("");
		form.setLastName("A");
		form.setBorn(new Date());
		validateFailure(form);
	}
	
	@Test
	public void testFirstNameNotStartWithMajuscule(){
		StudentForm form = new StudentForm();
		form.setFirstName("a");
		form.setLastName("A");
		form.setBorn(new Date());
		validateFailure(form);		
	}
	
	@Test
	public void testFirstNameIsOk(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName("A");
		form.setBorn(new Date());
		form.setIdClass(2L);
		validateSuccess(form);
		form.setFirstName("Azzeee refzrzer");
		form.setLastName("A");
		form.setBorn(new Date());
		form.setIdClass(6L);
		validateSuccess(form);
	}
	

	@Test
	public void testLastNameIsNotNull(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName(null);
		form.setBorn(new Date());
		validateFailure(form);
	}
	
	@Test
	public void testLastNameIsNotEmpty(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName("");
		form.setBorn(new Date());
		validateFailure(form);
	}
	
	@Test
	public void testLastNameNotStartWithMajuscule(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName("a");
		form.setBorn(new Date());
		validateFailure(form);		
	}
	
	@Test
	public void testLastNameIsOk(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName("A");
		form.setBorn(new Date());
		form.setIdClass(2L);
		validateSuccess(form);
		form.setFirstName("Azzeee refzrzer");
		form.setLastName("Azeazezae zaezaezae");
		form.setBorn(new Date());
		form.setIdClass(3L);
		validateSuccess(form);
	}
	
	@Test
	public void testBornIsNotNull(){
		StudentForm form = new StudentForm();
		form.setFirstName("A");
		form.setLastName("A");
		form.setBorn(null);
		validateFailure(form);		
	}
	
	@Test
	public void testBornIsOk(){
		StudentForm form = new StudentForm();
		form.setFirstName("Azzeee refzrzer");
		form.setLastName("Azeazezae zaezaezae");
		form.setBorn(new Date());
		form.setIdClass(2L);
		validateSuccess(form);
	}
}
