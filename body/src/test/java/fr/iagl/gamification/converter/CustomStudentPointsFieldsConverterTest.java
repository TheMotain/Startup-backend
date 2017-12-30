package fr.iagl.gamification.converter;

import java.math.BigDecimal;

import org.dozer.MappingException;
import org.junit.Assert;
import org.junit.Test;

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.model.PointModel;

public class CustomStudentPointsFieldsConverterTest {
	
	@Test
	public void testInputIsNotAndSourceTypeIsPointModelThenReturnNullPointEntity() {
		Assert.assertNull(new CustomStudentPointsFieldsConverter().convert(null,null,null,PointModel.class));
	}
	
	@Test
	public void testInputIsNullAndSourceTypeIsPointEntityThenReturnDefaultPointEntity() {
		PointModel entity = (PointModel) new CustomStudentPointsFieldsConverter().convert(null,null,null,PointEntity.class);
		Assert.assertNotNull(entity);
		Assert.assertEquals(0, entity.getBonus());
		Assert.assertEquals(0, entity.getMalus());
		Assert.assertEquals(new BigDecimal(0), entity.getArgent());
		Assert.assertNull(entity.getStudent());
	}
	
	@Test
	public void testSourceisNotNullAndDestinationIsNotNullAndTypeSourceIsPointModelThenDontCreateNewDestination() {
		PointEntity destination = new PointEntity();
		Assert.assertEquals(destination, new CustomStudentPointsFieldsConverter().convert(destination, new PointModel(), null, PointModel.class));
	}
	
	@Test
	public void testSourceisNotNullAndDestinationIsNotNullAndTypeSourceIsPointEntityThenDontCreateNewDestination() {
		PointModel destination = new PointModel();
		Assert.assertEquals(destination, new CustomStudentPointsFieldsConverter().convert(destination, new PointEntity(), null, PointEntity.class));
	}
	
	@Test(expected=MappingException.class)
	public void testSourceisNotNullAndTypeSourceIsPointEntityAndPointModelThenDontCreateNewDestination() {
		PointModel destination = new PointModel();
		Assert.assertEquals(destination, new CustomStudentPointsFieldsConverter().convert(destination, new PointModel(), null, null));
	}
	
	@Test
	public void testMappingModelToEntityMapAttributes() {
		PointModel model = new PointModel();
		model.setBonus(1);
		model.setMalus(2);
		model.setArgent(new BigDecimal(2));
		PointEntity entity = (PointEntity) new CustomStudentPointsFieldsConverter().convert(null, model, null, model.getClass());
		Assert.assertEquals(model.getBonus(), entity.getBonus());
		Assert.assertEquals(model.getMalus(), entity.getMalus());
		Assert.assertEquals(model.getArgent(), entity.getArgent());
	}
	
	@Test
	public void testMappingEntityToModelMapAttributes() {
		PointEntity entity = new PointEntity();
		entity.setBonus(1);
		entity.setMalus(2);
		entity.setArgent(new BigDecimal(2));
		PointModel model = (PointModel) new CustomStudentPointsFieldsConverter().convert(null, entity, null, entity.getClass());
		Assert.assertEquals(model.getBonus(), entity.getBonus());
		Assert.assertEquals(model.getMalus(), entity.getMalus());
		Assert.assertEquals(model.getArgent(), entity.getArgent());
	}
}
