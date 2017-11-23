package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.repository.PointRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.PointService;

@Service
public class PointServiceImpl implements PointService{

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
	public PointModel updatePoint(PointModel pointToUpdate, long idStudent) throws StudentNotFoundException {
		StudentEntity student = studentRepository.findOne(idStudent);
		if (student == null) {
			throw new StudentNotFoundException();
		}
		PointEntity points = pointRepository.findByStudent_Id(idStudent);
	
		if (points == null) {
			points = mapper.map(pointToUpdate, PointEntity.class);
			points.setStudent(student);
		} else {
			points.setBonus(points.getBonus() + pointToUpdate.getBonus());
			points.setMalus(points.getMalus() + pointToUpdate.getMalus());
		}
		
		PointEntity saved = pointRepository.save(points);
		return mapper.map(saved, PointModel.class);
	}

	@Override
	public List<PointModel> getPoints() {
		List<PointModel> output = new ArrayList<>();
		pointRepository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, PointModel.class)));
		return output;
	}

}
