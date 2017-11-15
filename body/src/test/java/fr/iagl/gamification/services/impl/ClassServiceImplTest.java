package fr.iagl.gamification.services.impl;

import org.dozer.Mapper;
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

}
