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
	public void testCreateStudentCallCreateRepositoryMethod() {
		service.createStudent(Mockito.mock(StudentModel.class));
		Mockito.verify(studentRepository, Mockito.times(1)).save((StudentEntity)Mockito.any());
	}
	
	@Test
	public void testCreateStudentReturnCreatedStudentByRepository() {
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentEntity)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		Assert.assertEquals(model, service.createStudent(Mockito.mock(StudentModel.class)));
	}
	
	@Test
	public void testCreateStudentUseStudentModelInParamToPersistIt() {
		StudentModel in = Mockito.mock(StudentModel.class);
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		ArgumentCaptor<StudentEntity> captor = ArgumentCaptor.forClass(StudentEntity.class);
		
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentEntity.class))).thenReturn(entity);
		service.createStudent(in);
		Mockito.verify(studentRepository, Mockito.times(1)).save(captor.capture());
		Assert.assertEquals(entity, captor.getValue());
	}
	
	@Test
	public void testDeleteStudentFromClass() throws StudentNotFoundException, ClassroomNotFoundException{
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentModel studentModel = Mockito.mock(StudentModel.class);
		
		Mockito.when(mapper.map(Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(studentModel);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(studentEntity);
		Mockito.when(classRepository.findOne(Mockito.any())).thenReturn(classEntity);
		
		StudentModel model = service.deleteStudentFromClass(1L, 2L);
		Mockito.verify(studentEntity).setClassroom(null);
		Mockito.verify(studentRepository, Mockito.times(1)).save(studentEntity);
		
		Assert.assertEquals(studentModel, model);
	}
}
