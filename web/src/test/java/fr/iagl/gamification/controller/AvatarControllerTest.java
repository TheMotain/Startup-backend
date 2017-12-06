package fr.iagl.gamification.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.services.AvatarService;

public class AvatarControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private AvatarController controller;
	
	@Mock
	private AvatarService avatarService;
	
	@Before
	public void init() {
		controller = new AvatarController();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAvatarCallServiceWithMethodParam() throws GamificationServiceException {
		controller.getAvatar(10L);
		Mockito.verify(avatarService, Mockito.times(1)).findAvatar(10L);
	}
	
	@Test
	public void testGetAvatarReturnResultFromServiceAndOkStatus() throws GamificationServiceException {
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarService.findAvatar(Mockito.anyLong())).thenReturn(model);
		ResponseEntity<AvatarModel> result = controller.getAvatar(0L);
		Assert.assertEquals(model, result.getBody());
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode().OK);
	}
	
	@Test
	public void testGetAvatarIdNullReturnBadRequestWithErrorsOnExcpetion() throws GamificationServiceException {
		GamificationServiceException gse = new GamificationServiceException(Mockito.anyList());
		Mockito.when(avatarService.findAvatar(null)).thenThrow(gse);
		ResponseEntity result = controller.getAvatar(0L);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assert.assertEquals(gse.getErrors(), result.getBody());
	}
}
