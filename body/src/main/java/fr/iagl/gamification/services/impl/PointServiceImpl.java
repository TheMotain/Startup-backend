package fr.iagl.gamification.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.repository.PointRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.PointService;

/**
 * Service de gestion des points des élèves
 * 
 * @author Hélène MEYER
 *
 */
@Service
public class PointServiceImpl implements PointService{

	private static final Long CONSTANTE_POINTS_NEXT_LEVEL = 25L;

	private static final Long CONSTANTE_100 = 100L;
	
	private static final BigDecimal CONSTANTE_ARGENT_NEXT_LEVEL = new BigDecimal(15);

	/**
	 * Fait les interactions avec la base de données
	 */
	@Autowired
	private PointRepository pointRepository;
	
	/**
	 * Repository pour la manipulation des élèves en base
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public PointModel updatePoint(PointModel pointToUpdate, long idStudent) throws GamificationServiceException{
		StudentEntity student = studentRepository.findOne(idStudent);
		if (student == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT));
		}
		PointEntity points = pointRepository.findByStudent_Id(idStudent);
	
		if (points == null) {
			points = mapper.map(pointToUpdate, PointEntity.class);
			points.setPointsToNextLevel(CONSTANTE_100);
			points.setStudent(student);
		} else {
			points.setBonus(points.getBonus() + pointToUpdate.getBonus());
			points.setMalus(points.getMalus() + pointToUpdate.getMalus());
		}
		updateLevel(points);
		PointEntity saved = pointRepository.save(points);
		return mapper.map(saved, PointModel.class);
	}

	@Override
	public List<PointModel> getPoints() {
		List<PointModel> output = new ArrayList<>();
		pointRepository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, PointModel.class)));
		return output;
	}

	@Override
	public PointModel getPoint(long studentID) throws GamificationServiceException {
		PointEntity entity = pointRepository.findByStudent_Id(studentID);
		if(null == entity) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT));
		}
		return mapper.map(entity, PointModel.class);
	}

	@Override
	public void updateLevel(PointEntity point) {
		Long pointToNextLevel = point.getPointsToNextLevel();
		int level = point.getLevel();
		BigDecimal argentActu = point.getArgent();
		
		while(point.getBonus() >= pointToNextLevel){
			if(argentActu == null){
				level++;
				point.setArgent(CONSTANTE_ARGENT_NEXT_LEVEL);
				pointToNextLevel += CONSTANTE_100 + CONSTANTE_POINTS_NEXT_LEVEL;
			}else{
				level++;
				point.setArgent(argentActu.add(CONSTANTE_ARGENT_NEXT_LEVEL));
				pointToNextLevel += CONSTANTE_100 + CONSTANTE_POINTS_NEXT_LEVEL;
			}
		}
		
		point.setLevel(level);
		point.setPointsToNextLevel(pointToNextLevel);
	}

}
