package fr.iagl.gamification;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;

import fr.iagl.gamification.validator.AbstractForm;

/**
 * Permet d'abstraire les tests au niveau des validator afin de rendre transparante
 * la réalisation de ceux-ci
 * @author ALEX
 *
 */
public abstract class AbstractFormTest extends SpringIntegrationTest {
	
	/**
	 * Validator
	 */
	private static Validator validator;
	
	/**
	 * Setup du validator
	 */
	@Before
	public void setup(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	/**
	 * Méthode qui test que la validation échoue
	 * @param form à tester
	 */
	protected void validateFailure(final AbstractForm form){
		Set<ConstraintViolation<AbstractForm>> violations = validator.validate(form);
		Assert.assertFalse(violations.isEmpty());
	}

	
	/**
	 * Méthode qui test que la validation réussi
	 * @param form à tester
	 */
	protected void validateSuccess(final AbstractForm form){
		Set<ConstraintViolation<AbstractForm>> violations = validator.validate(form);
		Assert.assertTrue(violations.isEmpty());
	}
}
