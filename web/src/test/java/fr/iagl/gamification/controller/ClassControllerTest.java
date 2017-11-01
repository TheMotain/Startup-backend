package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.services.ClassService;
import fr.iagl.gamification.validator.ClassForm;

public class ClassControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private ClassController controller;
	
	@Mock
	private ClassService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testClassFormOK(){
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("className").when(classForm).getClassName();
		
		String output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any());
		assertEquals("ok", output);
	}

	@Test
	public void testClassFormKO(){
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(classe).when(mapper).map(classForm, ClassModel.class);
		
		String output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.never()).createClass(classe);
		assertEquals("error message", output);
	}
}
