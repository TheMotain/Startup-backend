package fr.iagl.gamification.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.iagl.gamification.SpringIntegrationTest;

public class ClassFormTest extends SpringIntegrationTest{

	@Test
	public void testClassForm() {
		ClassForm classForm = new ClassForm();
		classForm.setClassName("class");
		assertEquals("class", classForm.getClassName());
	}

}
