package fr.iagl.gamification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.exceptions.ClassExistsException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.validator.StudentForm;

public class StudentControllerTest extends SpringIntegrationTest {
	
	@InjectMocks
	private StudentController controller;
	
	@Mock
	private StudentService studentService;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllStudentCallGetAllFromService() throws ClassExistsException{
		controller.getAllStudent();
		Mockito.verify(studentService, Mockito.times(1)).getAllStudent();
	}
	
	@Test
	public void testGetAllStudentReturnResponseEntityContainsServiceResult(){
		List<StudentModel> mock = new ArrayList<>();
		Mockito.when(studentService.getAllStudent()).thenReturn(mock);
		ResponseEntity<List<StudentModel>> response = controller.getAllStudent();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testCreateStudentCallCreateFromService(){
		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(studentService, Mockito.times(1)).createOrUpdateStudent((StudentModel)Mockito.any());
	}
	
	@Test
	public void testCreateStudentCallCreateFromServiceWithMappedModel(){
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		ArgumentCaptor<StudentModel> modelCaptor = ArgumentCaptor.forClass(StudentModel.class);

		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(mapper, Mockito.times(1)).map((StudentForm)Mockito.any(), Mockito.eq(StudentModel.class));
		Mockito.verify(studentService, Mockito.times(1)).createOrUpdateStudent(modelCaptor.capture());
		Assert.assertEquals(model, modelCaptor.getValue());
	}
	
	@Test
	public void testCreateStudentReturnNewCreatedStudent(){
		StudentModel in = Mockito.mock(StudentModel.class);
		StudentModel out = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(in);
		Mockito.when(studentService.createOrUpdateStudent(in)).thenReturn(out);
		ResponseEntity<StudentModel> response = (ResponseEntity<StudentModel>) controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assert.assertEquals(out, response.getBody());
	}
	
	@Test
	public void testCreateStudentErrorOnFormReturnKoResponse(){
		StudentForm form = Mockito.mock(StudentForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		ResponseEntity<List<String>> response = (ResponseEntity<List<String>>) controller.createStudent(form, bindingResult);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testCreateStudentErrorOnFormResponseContainsErrors(){
		StudentForm form = Mockito.mock(StudentForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError errors = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn("message").when(errors).getDefaultMessage();
		Mockito.doReturn(Arrays.asList(errors)).when(bindingResult).getAllErrors();
		ResponseEntity<List<String>> response = (ResponseEntity<List<String>>) controller.createStudent(form, bindingResult);
		Assert.assertEquals("message", response.getBody().get(0));
	}
}
