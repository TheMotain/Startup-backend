package fr.iagl.gamification.controller;

import java.util.ArrayList;
import java.util.List;

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
import fr.iagl.gamification.model.PriceModel;
import fr.iagl.gamification.services.AvatarService;
import fr.iagl.gamification.services.InventaireService;
import fr.iagl.gamification.services.PriceService;

public class AvatarControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private AvatarController controller;
	
	@Mock
	private AvatarService avatarService;
	
	@Mock
	private PriceService priceService;
	
	@Mock
	private InventaireService inventaireService;
	
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
		ResponseEntity result = controller.getAvatar(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assert.assertEquals(gse.getErrors(), result.getBody());
	}
	
	@Test
	public void testUpdateAvatarCallServiceWithMethodParam() throws GamificationServiceException {
		controller.updateAvatar(10L, "null");
		Mockito.verify(avatarService, Mockito.times(1)).updateAvatar(10L, "null");
	}
	
	@Test
	public void testUpdateAvatarReturnResultFromServiceAndOkStatus() throws GamificationServiceException {
		AvatarModel model = Mockito.mock(AvatarModel.class);
		Mockito.when(avatarService.updateAvatar(Mockito.anyLong(), Mockito.anyString())).thenReturn(model);
		ResponseEntity<AvatarModel> result = controller.updateAvatar(0L,"aze");
		Assert.assertEquals(model, result.getBody());
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode().OK);
	}
	
	@Test
	public void testUpdateAvatarIdNullReturnBadRequestWithErrorsOnExcpetion() throws GamificationServiceException {
		GamificationServiceException gse = new GamificationServiceException(new ArrayList<>());
		Mockito.when(avatarService.updateAvatar(Mockito.eq(null), Mockito.anyString())).thenThrow(gse);
		ResponseEntity result = controller.updateAvatar(null, "ezr");
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assert.assertEquals(gse.getErrors(), result.getBody());
	}
	
	@Test
	public void testUpdateAvatarWithAvatarParamNullReturnBadRequest() {
		ResponseEntity result = controller.updateAvatar(null, null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());		
	}
	
	@Test
	public void testUpdateAvatarWithAvatarParamEmptyReturnBadRequest() {
		ResponseEntity result = controller.updateAvatar(null, "");
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());		
	}

	@Test
	public void testUpdateAvatarWithAvatarParamEmptyAfterTrimReturnBadRequest() {
		ResponseEntity result = controller.updateAvatar(null, "    ");
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());		
	}
	
	@Test
	public void testPriceListCallRepository() {
		controller.priceList();
		Mockito.verify(priceService,Mockito.times(1)).listAllAvatar();
	}
	
	@Test
	public void testPriceListReturnResultOfService() {
		List<PriceModel> in = new ArrayList<>();
		PriceModel mock = Mockito.mock(PriceModel.class);
		in.add(mock);
		in.add(mock);
		Mockito.when(priceService.listAllAvatar()).thenReturn(in);
		ResponseEntity<List<PriceModel>> out = controller.priceList();
		Assert.assertEquals(out.getStatusCode(), HttpStatus.OK);
		Assert.assertEquals(out.getBody(), in);
	}
	
	@Test
	public void testGetBougthAvatarReturnResultFromService() throws GamificationServiceException {
		List<String> in = new ArrayList<>();
		Mockito.when(inventaireService.getAllBougthAvatar(Mockito.anyLong())).thenReturn(in);
		ResponseEntity response = controller.getBougthAvatar(10L);
		Assert.assertEquals(in, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(inventaireService, Mockito.times(1)).getAllBougthAvatar(Mockito.any());
	}
	
	@Test
	public void testGetBougthAvatarReturnBadRequestWhenServiceThrowException() throws GamificationServiceException {
		Mockito.when(inventaireService.getAllBougthAvatar(Mockito.anyLong())).thenThrow(new GamificationServiceException(null));
		ResponseEntity response = controller.getBougthAvatar(10L);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
