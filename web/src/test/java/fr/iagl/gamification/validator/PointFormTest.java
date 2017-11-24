package fr.iagl.gamification.validator;

import org.junit.Test;

import fr.iagl.gamification.AbstractFormTest;

public class PointFormTest extends AbstractFormTest{

	@Test
	public void testGoodElements() {
		PointForm form = new PointForm();
		form.setBonus(5L);
		form.setMalus(2L);
		form.setIdStudent(2L);
		validateSuccess(form);
	}
}
