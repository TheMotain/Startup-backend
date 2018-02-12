package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.entity.PriceEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.entity.pk.InventairePK;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.repository.InventaireRepository;
import fr.iagl.gamification.repository.PriceRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.InventaireService;

public class InventaireServiceImplTest {

	@InjectMocks
	private InventaireService inventaireService;
	
	@Mock
	private InventaireRepository inventaireRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private PriceRepository priceRepository;
	
	@Before
	public void init() {
		inventaireService = new InventaireServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testGetAllBougthAvatarCheckIfIdNotNull() throws GamificationServiceException {
		inventaireService.getAllBougthAvatar(null);
	}
	
	@Test(expected = GamificationServiceException.class)
	public void testGetAllBougthAvatarCheckIfIdExist() throws GamificationServiceException {
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(null);
		inventaireService.getAllBougthAvatar(12L);
	}
	
	@Test
	public void testGetAllBougthAvatarCallInventaireRepository() throws GamificationServiceException {
		StudentEntity student = Mockito.mock(StudentEntity.class);
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(student);
		inventaireService.getAllBougthAvatar(10L);
		Mockito.verify(inventaireRepository, Mockito.times(1)).findByIdStudentId(Mockito.anyLong());
	}
	
	@Test
	public void testGetAllBougthAvatarReturnMappingOfRepositoryResult() throws GamificationServiceException {
		StudentEntity student = Mockito.mock(StudentEntity.class);
		Mockito.when(studentRepository.findOne(Mockito.anyLong())).thenReturn(student);
		List<InventaireEntity> in = new ArrayList<>();
		InventaireEntity entity = Mockito.mock(InventaireEntity.class);
		InventairePK pk = Mockito.mock(InventairePK.class);
		PriceEntity avatarRef = Mockito.mock(PriceEntity.class);
		in.add(entity);
		in.add(entity);
		in.add(entity);
		Mockito.when(inventaireRepository.findByIdStudentId(Mockito.anyLong())).thenReturn(in);
		Mockito.when(entity.getId()).thenReturn(pk);
		Mockito.when(pk.getAvatarRef()).thenReturn(avatarRef);
		List<String> output = inventaireService.getAllBougthAvatar(10L);
		Assert.assertEquals(3, output.size());
		Mockito.verify(entity, Mockito.times(3)).getId();
		Mockito.verify(pk, Mockito.times(3)).getAvatarRef();
		Mockito.verify(avatarRef, Mockito.times(3)).getAvatar();
	}
	
	@Test
	public void testCreateDefaultAvatar() {
		inventaireService.createDefaultAvatar(0L);
		Mockito.verify(priceRepository, Mockito.times(1)).findDefaultAvatar();
		Mockito.verify(inventaireRepository, Mockito.times(1)).save(Mockito.any(InventaireEntity.class));
	}
	
	@Test
	public void testFindAvatarForStudent() {
		inventaireService.findAvatarForStudent("avatar", 0L);
		Mockito.verify(inventaireRepository, Mockito.times(1)).findOne(Mockito.any());
	}
}
