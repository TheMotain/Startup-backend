package fr.iagl.gamification.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.model.PointModel;

public class CustomStudentPointsFieldsConverter implements CustomConverter {

	@Override
	public Object convert(Object destination, Object source, Class<?> classDest, Class<?> classSource) {
		if(source == null) {
			return null;
		}
		if(source instanceof PointModel) {
			PointEntity dest = null;
			if(destination == null) {
				dest = new PointEntity();
			} else {
				dest = (PointEntity) destination;
			}
			dest.setBonus(((PointModel)source).getBonus());
			dest.setMalus(((PointModel)source).getMalus());
			return dest;
		}else if(source instanceof PointEntity) {
			PointModel dest = null;
			if(destination == null) {
				dest = new PointModel();
			} else {
				dest = (PointModel) destination;
			}
			dest.setBonus(((PointEntity)source).getBonus());
			dest.setMalus(((PointEntity)source).getMalus());
			return dest;
		}else {
			throw new MappingException("Converter CustomStudentPointsFieldsConverter "
					+ "used incorrectly. Arguments passed in were:" + destination + " and " + source);
		}
	}

}
