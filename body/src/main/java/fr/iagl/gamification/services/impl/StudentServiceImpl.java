package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
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
	public List<StudentModel> getStudentsWithoutClass() {
		List<StudentModel> output = new ArrayList<>();
		studentRepository.findByClassroomIsNull().iterator().forEachRemaining(x -> output.add(mapper.map(x, StudentModel.class)));
		return output;
	}

	@Override
	public StudentModel saveStudent(StudentModel model) {
		StudentEntity entity = mapper.map(model, StudentEntity.class);
		return entityToModel(entity);
	}

	@Override
	public StudentModel addClassToStudent(long idStudent, long idClass) throws StudentNotFoundException, ClassroomNotFoundException {
		StudentEntity entity = studentRepository.findOne(idStudent);
		
		if (entity == null) {
			throw new StudentNotFoundException();
		}
		
		ClassEntity classEntity = classRepository.findOne(idClass);
		
		if (classEntity == null) {
			throw new ClassroomNotFoundException();
		}
		
		entity.setClassroom(classEntity);
		return entityToModel(entity);
	}
	
	private StudentModel entityToModel(StudentEntity entity) {
		return mapper.map(studentRepository.save(entity), StudentModel.class);
	}


}
