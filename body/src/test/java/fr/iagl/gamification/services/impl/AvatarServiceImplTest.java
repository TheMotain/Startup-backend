package fr.iagl.gamification.services.impl;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.entity.AvatarEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.AvatarService;

public class AvatarServiceImplTest {

	@InjectMocks
	private AvatarService service;
	
	@Mock
	private AvatarRepository avatarRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@Before
	public void init() {
		service = new AvatarServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testFindAvatarWithNullParamThrowException() throws GamificationServiceException {
		service.findAvatar(null);
	}
	
	@Test
	public void testFindAvatarCallRepository() throws GamificationServiceException {
		service.findAvatar(0L);
		Mockito.verify(avatarRepository, Mockito.times(1)).findOne(Mockito.anyLong());
	}
	
	@Test
	public void testFindAvatarReturnAvatarModelMappedFromResultRepository() throws GamificationServiceException {
		AvatarEntity entity = Mockito.mock(AvatarEntity.class);
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarRepository.findOne(Mockito.anyLong())).thenReturn(entity);
		Mockito.when(mapper.map(entity, AvatarModel.class)).thenReturn(model);
		Assert.assertEquals(model, service.findAvatar(Mockito.anyLong()));
		Mockito.verify(mapper, Mockito.times(1)).map(Mockito.any(AvatarEntity.class), (Class<?>) Mockito.eq(AvatarModel.class));
	}
	
	@Test
	public void testFindAvatarNotExistsCreateDefaultAvatar() throws GamificationServiceException {
		Mockito.when(avatarRepository.findOne(0L)).thenReturn(null);
		service.findAvatar(0L);
		Mockito.verify(avatarRepository, Mockito.times(1)).save((AvatarEntity)Mockito.any());
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testGetAvatarThrowErrorIfStudentNotExist() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(null);
		service.findAvatar(0L);
	}
}
