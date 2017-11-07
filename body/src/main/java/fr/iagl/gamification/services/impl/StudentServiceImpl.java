package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.StudentService;

/**
 * Implémentation du service {@link StudentService}
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
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<StudentModel> getAllStudent() {
		List<StudentModel> output = new ArrayList<>();
		studentRepository.findAll().iterator().forEachRemaining(x->{output.add(mapper.map(x, StudentModel.class));});
		return output;
	}

	@Override
	public StudentModel createStudent(StudentModel model) {
		// TODO
		return null;
	}

}
