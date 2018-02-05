package fr.iagl.gamification.services.impl;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.entity.AvatarEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.repository.PriceRepository;
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
	private PriceRepository priceRepository;
	
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

	@Ignore
	@Test
	public void testFindAvatarCallRepository() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		service.findAvatar(0L);
		Mockito.verify(studentRepository, Mockito.times(1)).findOne(Mockito.anyLong());
		Mockito.verify(avatarRepository, Mockito.times(1)).findOne(Mockito.anyLong());
	}
	
	@Test
	public void testFindAvatarReturnAvatarModelMappedFromResultRepository() throws GamificationServiceException {
		AvatarEntity entity = Mockito.mock(AvatarEntity.class);
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarRepository.findOne(Mockito.anyLong())).thenReturn(entity);
		Mockito.when(mapper.map(entity, AvatarModel.class)).thenReturn(model);
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		Assert.assertEquals(model, service.findAvatar(Mockito.anyLong()));
		Mockito.verify(mapper, Mockito.times(1)).map(Mockito.any(AvatarEntity.class), (Class<?>) Mockito.eq(AvatarModel.class));
	}

	@Ignore
	@Test
	public void testFindAvatarNotExistsCreateDefaultAvatar() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		Mockito.when(avatarRepository.findOne(0L)).thenReturn(null);
		service.findAvatar(0L);
		Mockito.verify(avatarRepository, Mockito.times(1)).save((AvatarEntity)Mockito.any());
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testFindAvatarThrowErrorIfStudentNotExist() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(null);
		service.findAvatar(0L);
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testUpadeAvatarWithStudentNullParamThrowException() throws GamificationServiceException {
		service.updateAvatar(null, "azeear");
	}

	@Ignore
	@Test
	public void testUpdateAvatarCallRepository() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		service.updateAvatar(0L, "aze");
		Mockito.verify(studentRepository, Mockito.times(1)).findOne(Mockito.anyLong());
		Mockito.verify(avatarRepository, Mockito.times(1)).findOne(Mockito.anyLong());
		Mockito.verify(avatarRepository, Mockito.times(1)).save((AvatarEntity)Mockito.any());
	}

	@Test(expected = GamificationServiceException.class)
	public void testUpdateAvatarThrowErrorIfStudentNotExist() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(null);
		service.updateAvatar(0L, "azea");
	}

	@Ignore
	@Test
	public void testUpdateAvatarReturnAvatarModelMappedFromResultRepository() throws GamificationServiceException {
		AvatarEntity entity = Mockito.mock(AvatarEntity.class);
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarRepository.save((AvatarEntity)Mockito.anyObject())).thenReturn(entity);
		Mockito.when(mapper.map(entity, AvatarModel.class)).thenReturn(model);
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		Assert.assertEquals(model, service.updateAvatar(0L,Mockito.anyString()));
		Mockito.verify(mapper, Mockito.times(1)).map(Mockito.any(AvatarEntity.class), Mockito.eq(AvatarModel.class));
	}

	@Ignore
	@Test
	public void testUpdateAvatarReturnAvatarModelUpdatedFromResultRepository() throws GamificationServiceException {
		AvatarEntity entity = Mockito.mock(AvatarEntity.class);
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarRepository.save((AvatarEntity)Mockito.anyObject())).thenReturn(entity);
		Mockito.when(mapper.map(entity, AvatarModel.class)).thenReturn(model);
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(StudentEntity.class));
		Mockito.when(avatarRepository.findOne(Mockito.anyLong())).thenReturn(Mockito.mock(AvatarEntity.class));
		Assert.assertEquals(model, service.updateAvatar(0L,Mockito.anyString()));
		Mockito.verify(mapper, Mockito.times(1)).map(Mockito.any(AvatarEntity.class), Mockito.eq(AvatarModel.class));
	}
	
	@Ignore
	@Test
	public void testCreateDefaultAvatarCallSave() {
		service.createDefaultAvatar(Mockito.any(StudentEntity.class));
		Mockito.verify(avatarRepository, Mockito.times(1)).save(Mockito.any(AvatarEntity.class));
	}
	
	@Ignore
	@Test
	public void testCreateDefaultAvatarMapSaveResult() {
		AvatarEntity entity = Mockito.mock(AvatarEntity.class);
		Mockito.when(avatarRepository.save(Mockito.any(AvatarEntity.class))).thenReturn(entity);
		service.createDefaultAvatar(Mockito.any(StudentEntity.class));
		Mockito.verify(mapper, Mockito.times(1)).map(entity, AvatarModel.class);
	}
	
}
