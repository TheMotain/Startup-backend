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

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.repository.PointRepository;
import fr.iagl.gamification.repository.StudentRepository;


public class PointServiceImplTest {

	@InjectMocks
	private PointServiceImpl service;
	
	@Mock
	private PointRepository repository;
	
	@Mock 
	private StudentRepository studentRepository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllPointCallFindAllFromRepository(){
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<PointEntity>());
		service.getPoints();
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetAllPointReturnListOfPoint(){
		PointModel stm1 = Mockito.mock(PointModel.class);
		PointModel stm2 = Mockito.mock(PointModel.class);
		PointModel stm3 = Mockito.mock(PointModel.class);
		PointEntity ste1 = Mockito.mock(PointEntity.class);
		PointEntity ste2 = Mockito.mock(PointEntity.class);
		PointEntity ste3 = Mockito.mock(PointEntity.class);
		Mockito.when(mapper.map(ste1, PointModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, PointModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, PointModel.class)).thenReturn(stm3);
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new PointEntity[]{ste1,ste2,ste3}));
		List<PointModel> result = service.getPoints();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testAddPointWithBadStudentThrowException() throws GamificationServiceException{
		service.updatePoint(Mockito.any(), 0);
	}
	
	@Test
	public void testAddPointWithPointDoesntExists() throws GamificationServiceException{
		StudentEntity student = Mockito.mock(StudentEntity.class);
		PointEntity points = Mockito.mock(PointEntity.class);
		PointModel model = Mockito.mock(PointModel.class);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(student);
		Mockito.when(mapper.map(model, PointEntity.class)).thenReturn(points);
		Mockito.when(mapper.map(points, PointModel.class)).thenReturn(model);
		
		service.updatePoint(model, 2L);
		Mockito.verify(points).setStudent(student);
		Mockito.verify(repository).save(Mockito.any(PointEntity.class));
	}
	
	@Test
	public void testUpdatePointWithPointExists() throws GamificationServiceException{
		StudentEntity student = Mockito.mock(StudentEntity.class);
		PointEntity points = Mockito.mock(PointEntity.class);
		PointModel ptUpdated = Mockito.mock(PointModel.class);
		PointModel model = Mockito.mock(PointModel.class);
		Mockito.when(repository.findByStudent_Id(2L)).thenReturn(points);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(student);
		Mockito.when(mapper.map(ptUpdated, PointEntity.class)).thenReturn(points);
		Mockito.when(mapper.map(points, PointModel.class)).thenReturn(model);
		Mockito.when(points.getBonus()).thenReturn(1L);
		Mockito.when(points.getMalus()).thenReturn(0L);
		Mockito.when(ptUpdated.getBonus()).thenReturn(2L);
		Mockito.when(ptUpdated.getMalus()).thenReturn(5L);
		
		service.updatePoint(ptUpdated, 2L);
		Mockito.verify(points).setBonus(3L);
		Mockito.verify(points).setMalus(5L);
		Mockito.verify(repository).save(Mockito.any(PointEntity.class));
		
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testUpdatePointWithBadBonus() throws GamificationServiceException{
		StudentEntity student = Mockito.mock(StudentEntity.class);
		PointEntity points = Mockito.mock(PointEntity.class);
		PointModel ptUpdated = Mockito.mock(PointModel.class);
		Mockito.when(repository.findByStudent_Id(2L)).thenReturn(points);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(student);
		Mockito.when(mapper.map(ptUpdated, PointEntity.class)).thenReturn(points);
		Mockito.when(points.getBonus()).thenReturn(1L);
		Mockito.when(points.getMalus()).thenReturn(1L);
		Mockito.when(ptUpdated.getBonus()).thenReturn(0L);
		Mockito.when(ptUpdated.getMalus()).thenReturn(1L);
		
		service.updatePoint(ptUpdated, 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testUpdatePointWithBadMalus() throws GamificationServiceException{
		StudentEntity student = Mockito.mock(StudentEntity.class);
		PointEntity points = Mockito.mock(PointEntity.class);
		PointModel ptUpdated = Mockito.mock(PointModel.class);
		Mockito.when(repository.findByStudent_Id(2L)).thenReturn(points);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(student);
		Mockito.when(mapper.map(ptUpdated, PointEntity.class)).thenReturn(points);
		Mockito.when(points.getBonus()).thenReturn(1L);
		Mockito.when(points.getMalus()).thenReturn(1L);
		Mockito.when(ptUpdated.getBonus()).thenReturn(1L);
		Mockito.when(ptUpdated.getMalus()).thenReturn(0L);
		
		service.updatePoint(ptUpdated, 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testUpdatePointWithBadMalusBadBonus() throws GamificationServiceException{
		StudentEntity student = Mockito.mock(StudentEntity.class);
		PointEntity points = Mockito.mock(PointEntity.class);
		PointModel ptUpdated = Mockito.mock(PointModel.class);
		Mockito.when(repository.findByStudent_Id(2L)).thenReturn(points);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(student);
		Mockito.when(mapper.map(ptUpdated, PointEntity.class)).thenReturn(points);
		Mockito.when(points.getBonus()).thenReturn(1L);
		Mockito.when(points.getMalus()).thenReturn(1L);
		Mockito.when(ptUpdated.getBonus()).thenReturn(0L);
		Mockito.when(ptUpdated.getMalus()).thenReturn(0L);
		
		service.updatePoint(ptUpdated, 2L);
	}
}