package fr.iagl.gamification.validator;

import org.junit.Test;

import fr.iagl.gamification.AbstractFormTest;

public class ClassFormTest extends AbstractFormTest{
	
	@Test
	public void alphanumericOK() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("class");
		validateSuccess(classForm);
	}
	
	@Test
	public void alphanumericKO() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName(";select#");
		validateFailure(classForm);
	}
	
	@Test
	public void alphanumericOK3() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("CP 2'-OK_");
		validateSuccess(classForm);
	}
	
	@Test
	public void sizeOK() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("class");
		validateSuccess(classForm);
	}
	
	@Test
	public void littleSize1KO() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("c");
		validateFailure(classForm);
	}
	
	@Test
	public void bigSize31KO() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		validateFailure(classForm);
	}
	
	@Test
	public void nullKO() {
		ClassForm classForm = new ClassForm();
		validateFailure(classForm);
	}

}
