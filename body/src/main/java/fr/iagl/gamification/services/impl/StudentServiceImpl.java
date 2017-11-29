package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.StudentService;

/**
 * Implémentation du service {@link StudentService}
 * 
 * @author ALEX
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	/**
	 * Repository pour la manipulation des élèves en base
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Repository pour la manipulation des classes en base
	 */
	@Autowired
	private ClassRepository classRepository;

	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;

	@Override
	public List<StudentModel> getAllStudent() {
		List<StudentModel> output = new ArrayList<>();
		studentRepository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, StudentModel.class)));
		return output;
	}

	@Override
	public StudentModel saveStudent(StudentModel model) throws GamificationServiceException {
		ClassEntity classe = model.getClassroom() != null &&  model.getClassroom().getId() != null? classRepository.findOne(model.getClassroom().getId()) : null;
		if (classe == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM));
		}
		StudentEntity entity = mapper.map(model, StudentEntity.class);
		entity.setClassroom(classe);
		return entityToModel(entity);
	}

	@Override
	public StudentModel addClassToStudent(long idStudent, long idClass) throws GamificationServiceException {
		StudentEntity entity = studentRepository.findOne(idStudent);
		if (entity == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT));
		}
		if (entity.getClassroom() != null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_CLASS_ALREADY_EXISTS));
		}
		ClassEntity classEntity = classRepository.findOne(idClass);
		if (classEntity == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM));
		}
		
		entity.setClassroom(classEntity);
		return entityToModel(entity);
	}
	
	/**
	 * Map l'entité sous le format modèle
	 * 
	 * @param entity entité de l'étudiant
	 * @return le model de l'étudiant
	 */
	private StudentModel entityToModel(StudentEntity entity) {
		return mapper.map(studentRepository.save(entity), StudentModel.class);
	}

	@Override
	public StudentModel deleteStudentFromClass(long idStudent) throws GamificationServiceException{
		StudentEntity studentEntity = studentRepository.findOne(idStudent);
		
		if(studentEntity == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT));
		}
		studentEntity.setClassroom(null);
		
		return mapper.map(studentRepository.save(studentEntity),StudentModel.class);
	}

}
