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

import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.repository.QcmRepository;

public class QcmServiceImplTest {

	@InjectMocks
	private QcmServiceImpl service;
	
	@Mock
	private QcmRepository repository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllQcmCallFindAllFromRepository(){
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<QcmEntity>());
		service.getAllQcm();
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetAllQcmReturnListOfQcm(){
		QcmModel stm1 = Mockito.mock(QcmModel.class);
		QcmModel stm2 = Mockito.mock(QcmModel.class);
		QcmModel stm3 = Mockito.mock(QcmModel.class);
		QcmEntity ste1 = Mockito.mock(QcmEntity.class);
		QcmEntity ste2 = Mockito.mock(QcmEntity.class);
		QcmEntity ste3 = Mockito.mock(QcmEntity.class);
		Mockito.when(mapper.map(ste1, QcmModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, QcmModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, QcmModel.class)).thenReturn(stm3);
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new QcmEntity[]{ste1,ste2,ste3}));
		List<QcmModel> result = service.getAllQcm();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
}
