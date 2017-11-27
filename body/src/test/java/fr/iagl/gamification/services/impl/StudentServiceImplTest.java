package fr.iagl.gamification.services.impl;

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

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.ClassroomAlreadyExistedException;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.repository.StudentRepository;

public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl service;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private ClassRepository classRepository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllStudentCallFindAllFromRepository(){
		Mockito.when(studentRepository.findAll()).thenReturn(new ArrayList<StudentEntity>());
		service.getAllStudent();
		Mockito.verify(studentRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetAllStudentReturnListOfStudent(){
		StudentModel stm1 = Mockito.mock(StudentModel.class);
		StudentModel stm2 = Mockito.mock(StudentModel.class);
		StudentModel stm3 = Mockito.mock(StudentModel.class);
		StudentEntity ste1 = Mockito.mock(StudentEntity.class);
		StudentEntity ste2 = Mockito.mock(StudentEntity.class);
		StudentEntity ste3 = Mockito.mock(StudentEntity.class);
		Mockito.when(mapper.map(ste1, StudentModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, StudentModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, StudentModel.class)).thenReturn(stm3);
		Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(new StudentEntity[]{ste1,ste2,ste3}));
		List<StudentModel> result = service.getAllStudent();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
	
	@Test
	public void testSaveStudentCallSaveRepositoryMethod() {
		service.saveStudent(Mockito.mock(StudentModel.class));
		Mockito.verify(studentRepository, Mockito.times(1)).save((StudentEntity)Mockito.any());
	}
	
	@Test
	public void testSaveStudentReturnCreatedOrUpdatedStudentByRepository() {
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentEntity)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		Assert.assertEquals(model, service.saveStudent(Mockito.mock(StudentModel.class)));
	}
	
	@Test
	public void testSaveStudentUseStudentModelInParamToPersistIt() {
		StudentModel in = Mockito.mock(StudentModel.class);
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		ArgumentCaptor<StudentEntity> captor = ArgumentCaptor.forClass(StudentEntity.class);
		
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentEntity.class))).thenReturn(entity);
		service.saveStudent(in);
		Mockito.verify(studentRepository, Mockito.times(1)).save(captor.capture());
		Assert.assertEquals(entity, captor.getValue());
	}
	
	@Test
	public void testDeleteStudentFromClass() throws StudentNotFoundException{
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		StudentModel studentModel = Mockito.mock(StudentModel.class);
		
		Mockito.when(mapper.map(Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(studentModel);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(studentEntity);
		
		StudentModel model = service.deleteStudentFromClass(1L);
		Mockito.verify(studentEntity).setClassroom(null);
		Mockito.verify(studentRepository, Mockito.times(1)).save(studentEntity);
		
		Assert.assertEquals(studentModel, model);

	}
	
	@Test(expected=StudentNotFoundException.class)
	public void testDeleteStudentFromClassWithInexistingStudentThrowException() throws StudentNotFoundException{
		service.deleteStudentFromClass(1L);
	}
	
	@Test
	public void testAddClassToStudent() throws StudentNotFoundException, ClassroomNotFoundException, ClassroomAlreadyExistedException {
		ClassEntity classe = Mockito.mock(ClassEntity.class);
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		StudentModel model = Mockito.mock(StudentModel.class);
		
		Mockito.when(mapper.map(Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(entity);
		Mockito.when(classRepository.findOne(Mockito.any())).thenReturn(classe);
		StudentModel output = service.addClassToStudent(1L, 2L);
		Mockito.verify(entity).setClassroom(classe);
		Mockito.verify(studentRepository, Mockito.times(1)).save(entity);
		Assert.assertEquals(model, output);
	}
	

	@Test(expected=StudentNotFoundException.class)
	public void testAddClassToStudentWithBadIdStudentThrowException() throws StudentNotFoundException, ClassroomNotFoundException, ClassroomAlreadyExistedException {
		service.addClassToStudent(1L, 2L);
	}
	
	@Test(expected=ClassroomNotFoundException.class)
	public void testAddClassToStudentWithBadIdClassThrowException() throws StudentNotFoundException, ClassroomNotFoundException, ClassroomAlreadyExistedException {
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(entity);
		StudentModel output = service.addClassToStudent(1L, 2L);
	}
	
	@Test(expected=ClassroomAlreadyExistedException.class)
	public void testAddClassToStudentWithClassThrowException() throws StudentNotFoundException, ClassroomNotFoundException, ClassroomAlreadyExistedException {
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		ClassEntity classroom = Mockito.mock(ClassEntity.class); 
		
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(entity);
		Mockito.when(entity.getClassroom()).thenReturn(classroom);
		StudentModel output = service.addClassToStudent(1L, 2L);
	}
}
