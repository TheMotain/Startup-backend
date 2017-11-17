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

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.exceptions.ClassroomExistsException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.repository.ClassRepository;

public class ClassServiceImplTest{

	@InjectMocks
	private ClassServiceImpl service;
	
	@Mock
	private ClassRepository repository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testClassNotExistAndCreated() throws ClassroomExistsException {
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity entity = Mockito.mock(ClassEntity.class);
		Mockito.doReturn(false).when(repository).existsByName("name");
		Mockito.doReturn("name").when(classe).getClassName();
		Mockito.doReturn(entity).when(mapper).map(classe, ClassEntity.class);
		
		service.createClass(classe);
		Mockito.verify(repository).save(entity);
	}
	
	@Test(expected=ClassroomExistsException.class)
	public void testClassExistAndNotCreated() throws ClassroomExistsException {
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn("name").when(classe).getClassName();
		Mockito.doReturn(true).when(repository).existsByName("name");
		service.createClass(classe);
	}

	@Test
	public void testGetAllClassCallFindAllFromRepository(){
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<ClassEntity>());
		service.getAllClassroom();
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetAllClassReturnListOfClass(){
		ClassModel stm1 = Mockito.mock(ClassModel.class);
		ClassModel stm2 = Mockito.mock(ClassModel.class);
		ClassModel stm3 = Mockito.mock(ClassModel.class);
		ClassEntity ste1 = Mockito.mock(ClassEntity.class);
		ClassEntity ste2 = Mockito.mock(ClassEntity.class);
		ClassEntity ste3 = Mockito.mock(ClassEntity.class);
		Mockito.when(mapper.map(ste1, ClassModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, ClassModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, ClassModel.class)).thenReturn(stm3);
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new ClassEntity[]{ste1,ste2,ste3}));
		List<ClassModel> result = service.getAllClassroom();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
}
