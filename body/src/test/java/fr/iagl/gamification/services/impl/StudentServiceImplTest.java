package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.repository.StudentRepository;

public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl service;
	
	@Mock
	private StudentRepository studentRepository;
	
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
}
