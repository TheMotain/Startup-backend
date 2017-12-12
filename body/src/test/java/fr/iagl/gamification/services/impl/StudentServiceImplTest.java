package fr.iagl.gamification.services.impl;

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

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.AvatarService;

public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl service;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private ClassRepository classRepository;
	
	@Mock
	private AvatarService avatarService;
	
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
	public void testSaveStudentCallSaveRepositoryMethod() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		Mockito.doReturn(classe).when(model).getClassroom();
		Mockito.doReturn(2L).when(classe).getId();
		Mockito.doReturn(classEntity).when(classRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(studentEntity).when(mapper).map(model, StudentEntity.class);
		Mockito.when(studentRepository.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);
		Mockito.when(mapper.map(studentEntity, StudentModel.class)).thenReturn(model);
		
		service.saveStudent(model);
		Mockito.verify(studentRepository, Mockito.times(1)).save((StudentEntity)Mockito.any());
	}
	
	@Test
	public void testSaveStudentCallCreateDefaultAvatarIfNotExists() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		Mockito.when(model.getAvatar()).thenReturn(null);
		Mockito.doReturn(classe).when(model).getClassroom();
		Mockito.doReturn(2L).when(classe).getId();
		Mockito.doReturn(classEntity).when(classRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(studentEntity).when(mapper).map(model, StudentEntity.class);
		Mockito.when(studentRepository.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);
		Mockito.when(mapper.map(studentEntity, StudentModel.class)).thenReturn(model);
		
		service.saveStudent(model);
		Mockito.verify(avatarService, Mockito.times(1)).createDefaultAvatar((StudentEntity)Mockito.any());
	}
	
	@Test
	public void testSaveStudentCallCreateDefaultAvatarExists() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		AvatarModel avatarEntity = Mockito.mock(AvatarModel.class);
		Mockito.when(model.getAvatar()).thenReturn(avatarEntity);
		Mockito.doReturn(classe).when(model).getClassroom();
		Mockito.doReturn(2L).when(classe).getId();
		Mockito.doReturn(classEntity).when(classRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(studentEntity).when(mapper).map(model, StudentEntity.class);
		Mockito.when(studentRepository.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);
		Mockito.when(mapper.map(studentEntity, StudentModel.class)).thenReturn(model);
		
		service.saveStudent(model);
		Mockito.verify(avatarService, Mockito.times(0)).createDefaultAvatar((StudentEntity)Mockito.any());
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveStudentBadClassReturnException() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		service.saveStudent(model);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveStudentBadIdClassReturnException() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn(classe).when(model).getClassroom();
		service.saveStudent(model);
	}
	
	@Test
	public void testDeleteStudentFromClass() throws GamificationServiceException{
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		StudentModel studentModel = Mockito.mock(StudentModel.class);
		
		Mockito.when(mapper.map(Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(studentModel);
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(studentEntity);
		
		StudentModel model = service.deleteStudentFromClass(1L);
		Mockito.verify(studentEntity).setClassroom(null);
		Mockito.verify(studentRepository, Mockito.times(1)).save(studentEntity);
		
		Assert.assertEquals(studentModel, model);

	}
	
	@Test(expected=GamificationServiceException.class)
	public void testDeleteStudentFromClassWithInexistingStudentThrowException() throws GamificationServiceException{
		service.deleteStudentFromClass(1L);
	}
	
	@Test
	public void testSaveStudentReturnSaveStudentByRepository() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		StudentModel modelUpdated = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		Mockito.doReturn(classe).when(model).getClassroom();
		Mockito.doReturn(2L).when(classe).getId();
		Mockito.doReturn(studentEntity).when(studentRepository).save(studentEntity);
		Mockito.doReturn(classEntity).when(classRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(studentEntity).when(mapper).map(model, StudentEntity.class);
		
		Mockito.doReturn(modelUpdated).when(mapper).map(studentEntity, StudentModel.class);
		Assert.assertEquals(modelUpdated, service.saveStudent(model));
	}
	
	@Test
	public void testSaveStudentUseStudentModelInParamToPersistIt() throws GamificationServiceException {
		StudentModel model = Mockito.mock(StudentModel.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		StudentEntity studentEntity = Mockito.mock(StudentEntity.class);
		Mockito.doReturn(classe).when(model).getClassroom();
		Mockito.doReturn(2L).when(classe).getId();
		Mockito.doReturn(studentEntity).when(studentRepository).save(studentEntity);
		Mockito.doReturn(classEntity).when(classRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(studentEntity).when(mapper).map(model, StudentEntity.class);
		Mockito.when(mapper.map(studentEntity, StudentModel.class)).thenReturn(model);
		
		ArgumentCaptor<StudentEntity> captor = ArgumentCaptor.forClass(StudentEntity.class);
		
		service.saveStudent(model);
		Mockito.verify(studentRepository, Mockito.times(1)).save(captor.capture());
		Assert.assertEquals(studentEntity, captor.getValue());
	}
	
	@Test
	public void testAddClassToStudent() throws GamificationServiceException {
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
	

	@Test(expected=GamificationServiceException.class)
	public void testAddClassToStudentWithBadIdStudentThrowException() throws GamificationServiceException {
		service.addClassToStudent(1L, 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testAddClassToStudentWithBadIdClassThrowException() throws GamificationServiceException {
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(entity);
		service.addClassToStudent(1L, 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testAddClassToStudentWithClassThrowException() throws GamificationServiceException {
		StudentEntity entity = Mockito.mock(StudentEntity.class);
		ClassEntity classroom = Mockito.mock(ClassEntity.class); 
		
		Mockito.when(studentRepository.findOne(Mockito.any())).thenReturn(entity);
		Mockito.when(entity.getClassroom()).thenReturn(classroom);
		service.addClassToStudent(1L, 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testGetStudentWithIdNullThrowException() throws GamificationServiceException {
		service.getStudentById(null);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testGetStudentWithStudentNullThrowException() throws GamificationServiceException {
		service.getStudentById(2L);
	}
	
	@Test
	public void testGetStudentOK() throws GamificationServiceException {
		StudentEntity student = Mockito.mock(StudentEntity.class);
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(studentRepository.findOne(2L)).thenReturn(student);
		Mockito.when(mapper.map(student, StudentModel.class)).thenReturn(model);
		StudentModel ret = service.getStudentById(2L);
		
		assertEquals(ret, model);
	}
	
}
