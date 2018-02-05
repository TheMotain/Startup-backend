package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.entity.PriceEntity;
import fr.iagl.gamification.model.PriceModel;
import fr.iagl.gamification.repository.PriceRepository;

public class PriceServiceImplTest {

	@InjectMocks
	private PriceServiceImpl priceService;
	
	@Mock
	private PriceRepository priceRepository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init() {
		priceService = new PriceServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCallRepository() {
		priceService.listAllAvatar();
		Mockito.verify(priceRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testReturnMappedListFromRepositoryResult() {
		List<PriceEntity> input = new ArrayList<>();
		PriceEntity mock = Mockito.mock(PriceEntity.class);
		input.add(mock);
		input.add(mock);
		input.add(mock);
		Mockito.when(priceRepository.findAll()).thenReturn(input);
		List<PriceModel> output = priceService.listAllAvatar();
		Mockito.verify(mapper, Mockito.times(3)).map(mock, PriceModel.class);
		Assert.assertEquals(3, output.size());
	}
}
