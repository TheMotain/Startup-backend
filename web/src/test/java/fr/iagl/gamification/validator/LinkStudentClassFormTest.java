package fr.iagl.gamification.validator;

import org.junit.Test;

import fr.iagl.gamification.AbstractFormTest;

public class LinkStudentClassFormTest extends AbstractFormTest{

	@Test
	public void idStudentClassOk() {
		LinkStudentClassForm form = new LinkStudentClassForm();
		form.setIdClass(2L);
		form.setIdStudent(1L);
		validateSuccess(form);
	}
	
	
}
