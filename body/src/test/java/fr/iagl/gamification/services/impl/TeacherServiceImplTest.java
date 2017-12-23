package fr.iagl.gamification.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import fr.iagl.gamification.constants.ServiceConstants;
import fr.iagl.gamification.entity.UserEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.model.UserModel;
import fr.iagl.gamification.repository.RoleUserRepository;
import fr.iagl.gamification.repository.UserRepository;
import fr.iagl.gamification.utils.CryptPasswordService;

public class TeacherServiceImplTest{

	@InjectMocks
	private TeacherServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private RoleUserRepository roleRepository;
	
	@Mock
	private CryptPasswordService cryptPassword;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testTeacherNotExistAndCreated() throws GamificationServiceException {
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		UserEntity entity = Mockito.mock(UserEntity.class);
		Mockito.doReturn("mail@gmail.com").when(teacher).getEmail();
		Mockito.doReturn(entity).when(mapper).map(teacher, UserEntity.class);
		
		service.createTeacher(teacher);
		Mockito.verify(repository).save(entity);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testTeacherNotExistAndNotCreatedBecausePasswordNotCrypted() throws GamificationServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		UserEntity entity = Mockito.mock(UserEntity.class);
		Mockito.doReturn("mail@gmail.com").when(teacher).getEmail();
		Mockito.doReturn(entity).when(mapper).map(teacher, UserEntity.class);
		
		Mockito.doThrow(NoSuchAlgorithmException.class).when(cryptPassword).encryptPassword(Mockito.any(TeacherModel.class));
		service.createTeacher(teacher);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testTeacherNotExistAndNotCreatedBecausePasswordNotCryptedOtherException() throws GamificationServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		UserEntity entity = Mockito.mock(UserEntity.class);
		Mockito.doReturn("mail@gmail.com").when(teacher).getEmail();
		Mockito.doReturn(entity).when(mapper).map(teacher, UserEntity.class);
		
		Mockito.doThrow(UnsupportedEncodingException.class).when(cryptPassword).encryptPassword(Mockito.any(TeacherModel.class));
		service.createTeacher(teacher);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testTeacherExistAndNotCreated() throws GamificationServiceException {
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		UserEntity user = Mockito.mock(UserEntity.class);
		Mockito.doReturn("mail@gmail.com").when(teacher).getEmail();
		Mockito.doReturn(user).when(repository).findByEmailAndRole_Role(Mockito.anyString(), Mockito.anyString());
		service.createTeacher(teacher);
	}

	@Test
	public void testGetAllTeacherCallFindAllFromRepository(){
		Mockito.when(repository.findByRole_Role(ServiceConstants.CODE_TEACHER)).thenReturn(new ArrayList<UserEntity>());
		service.getAllTeacher();
		Mockito.verify(repository, Mockito.times(1)).findByRole_Role(ServiceConstants.CODE_TEACHER);
	}
	
	@Test
	public void testGetAllTeacherReturnListOfClass(){
		TeacherModel stm1 = Mockito.mock(TeacherModel.class);
		TeacherModel stm2 = Mockito.mock(TeacherModel.class);
		TeacherModel stm3 = Mockito.mock(TeacherModel.class);
		UserEntity ste1 = Mockito.mock(UserEntity.class);
		UserEntity ste2 = Mockito.mock(UserEntity.class);
		UserEntity ste3 = Mockito.mock(UserEntity.class);
		Mockito.when(mapper.map(ste1, TeacherModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, TeacherModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, TeacherModel.class)).thenReturn(stm3);
		Mockito.when(repository.findByRole_Role(ServiceConstants.CODE_TEACHER)).thenReturn(Arrays.asList(new UserEntity[]{ste1,ste2,ste3}));
		List<TeacherModel> result = service.getAllTeacher();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
}
