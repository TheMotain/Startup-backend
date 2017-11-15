package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;

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
import fr.iagl.gamification.exceptions.ClassroomExistsException;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.validator.ClassForm;
import fr.iagl.gamification.validator.LinkStudentClassForm;
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
	public void testGetAllStudentCallGetAllFromService() throws ClassroomExistsException{
		controller.getAllStudent();
		Mockito.verify(studentService, Mockito.times(1)).getAllStudent();
	}
	
	@Test
	public void testGetAllStudentCallGetAllWithoutClassFromService() throws ClassroomExistsException{
		controller.getAllStudentWithoutClass();
		Mockito.verify(studentService, Mockito.times(1)).getStudentsWithoutClass();
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
	public void testGetAllStudentWithoutClassReturnResponseEntityContainsServiceResult(){
		List<StudentModel> mock = new ArrayList<>();
		Mockito.when(studentService.getStudentsWithoutClass()).thenReturn(mock);
		ResponseEntity<List<StudentModel>> response = controller.getAllStudentWithoutClass();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testCreateStudentCallCreateFromService(){
		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(studentService, Mockito.times(1)).saveStudent((StudentModel)Mockito.any());
	}
	
	@Test
	public void testCreateStudentCallCreateFromServiceWithMappedModel(){
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		ArgumentCaptor<StudentModel> modelCaptor = ArgumentCaptor.forClass(StudentModel.class);

		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(mapper, Mockito.times(1)).map((StudentForm)Mockito.any(), Mockito.eq(StudentModel.class));
		Mockito.verify(studentService, Mockito.times(1)).saveStudent(modelCaptor.capture());
		Assert.assertEquals(model, modelCaptor.getValue());
	}
	
	@Test
	public void testCreateStudentReturnNewCreatedStudent(){
		StudentModel in = Mockito.mock(StudentModel.class);
		StudentModel out = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(in);
		Mockito.when(studentService.saveStudent(in)).thenReturn(out);
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
	
	@Test
	public void testLinkClassStudentFormOK() throws ClassroomExistsException, StudentNotFoundException, ClassroomNotFoundException{
		LinkStudentClassForm linkForm = Mockito.mock(LinkStudentClassForm.class);
		StudentModel classModel = Mockito.mock(StudentModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(1L).when(linkForm).getIdStudent();
		Mockito.doReturn(2L).when(linkForm).getIdClass();
		Mockito.doReturn(classModel).when(studentService).addClassToStudent(Mockito.anyLong(), Mockito.anyLong());

		ResponseEntity output = controller.addClassToStudent(linkForm, bindingResult);
		Mockito.verify(studentService, Mockito.times(1)).addClassToStudent(1L, 2L);
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testLinkClassStudentFormKO() throws ClassroomExistsException, StudentNotFoundException, ClassroomNotFoundException{
		LinkStudentClassForm linkForm = Mockito.mock(LinkStudentClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		
		ResponseEntity output = controller.addClassToStudent(linkForm, bindingResult);
		Mockito.verify(studentService, Mockito.never()).addClassToStudent(Mockito.anyLong(), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testLinkClassStudentFormKOStudentNotFound() throws ClassroomExistsException, StudentNotFoundException, ClassroomNotFoundException{
		LinkStudentClassForm linkForm = Mockito.mock(LinkStudentClassForm.class);
		StudentModel classModel = Mockito.mock(StudentModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(1L).when(linkForm).getIdStudent();
		Mockito.doReturn(2L).when(linkForm).getIdClass();
		Mockito.doThrow(StudentNotFoundException.class).when(studentService).addClassToStudent(Mockito.anyLong(), Mockito.anyLong());
		
		ResponseEntity output = controller.addClassToStudent(linkForm, bindingResult);
		Mockito.verify(studentService, Mockito.times(1)).addClassToStudent(1L, 2L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testLinkClassStudentFormKOClassroomNotFound() throws ClassroomExistsException, StudentNotFoundException, ClassroomNotFoundException{
		LinkStudentClassForm linkForm = Mockito.mock(LinkStudentClassForm.class);
		StudentModel classModel = Mockito.mock(StudentModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(1L).when(linkForm).getIdStudent();
		Mockito.doReturn(2L).when(linkForm).getIdClass();
		Mockito.doThrow(ClassroomNotFoundException.class).when(studentService).addClassToStudent(Mockito.anyLong(), Mockito.anyLong());
		
		ResponseEntity output = controller.addClassToStudent(linkForm, bindingResult);
		Mockito.verify(studentService, Mockito.times(1)).addClassToStudent(1L, 2L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
}
