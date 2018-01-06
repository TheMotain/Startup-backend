package fr.iagl.gamification.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.model.StudentModel;

/**
 * Converter customisé pour le mapping de l'attribut
 * {@link StudentModel#getPoints()} {@link StudentEntity#getPoints()}
 * 
 * @author dalencourt
 *
 */
public class CustomStudentPointsFieldsConverter implements CustomConverter {

	/**
	 * Surcharge de la méthode {@link CustomConverter#convert(Object, Object, Class, Class)}<br/>
	 * Ne mappe pas l'attribut Student contenu dans l'attribut point<br/>
	 * Si l'attribut point est null :
	 * <ul>
	 * <li>Model -> Entity : le PointEntity n'est pas mappé</li>
	 * <li>Entity -> Model : le PointModel est créé avec la valeur par défaut 0 dans chaque attribut</li>
	 * </ul>
	 */
	@Override
	public Object convert(Object destination, Object source, Class<?> classDest, Class<?> classSource) {
		if (source == null && PointModel.class.equals(classSource)) {
			return null;
		}
		if (PointModel.class.equals(classSource)) {
			PointEntity dest = null;
			if (destination == null) {
				dest = new PointEntity();
			} else {
				dest = (PointEntity) destination;
			}
			dest.setBonus(((PointModel) source).getBonus());
			dest.setMalus(((PointModel) source).getMalus());
			dest.setLevel(((PointModel) source).getLevel());
			dest.setPointsToNextLevel(((PointModel) source).getPointsToNextLevel());
			return dest;
		} else if (PointEntity.class.equals(classSource)) {
			PointModel dest = null;
			if (destination == null) {
				dest = new PointModel();
			} else {
				dest = (PointModel) destination;
			}
			if (source != null) {
				dest.setBonus(((PointEntity) source).getBonus());
				dest.setMalus(((PointEntity) source).getMalus());
				dest.setLevel(((PointEntity) source).getLevel());
				dest.setPointsToNextLevel(((PointEntity) source).getPointsToNextLevel());
			} else {
				dest.setBonus(0);
				dest.setMalus(0);
				dest.setLevel(1);
				dest.setPointsToNextLevel(100L);
			}
			return dest;
		} else {
			throw new MappingException("Converter CustomStudentPointsFieldsConverter "
					+ "used incorrectly. Arguments passed in were:" + destination + " and " + source);
		}
	}

}
