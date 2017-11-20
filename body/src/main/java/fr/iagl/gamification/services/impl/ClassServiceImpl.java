package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.exceptions.ClassroomExistsException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.services.ClassService;

/**
 * Implémentation du service de la classe
 * 
 * @author Hélène Meyer
 *
 */
@Service
public class ClassServiceImpl implements ClassService {

	/**
	 * repository
	 */
	@Autowired
	private ClassRepository repository;
	
	/**
	 * mapper
	 */
	@Autowired
	private Mapper mapper;
	
	/* (non-Javadoc)
	 * @see fr.iagl.gamification.services.ClassService#createClass(fr.iagl.gamification.model.ClassModel)
	 */
	@Override
	public ClassModel createClass(@QueryParam("classe") ClassModel classe) throws ClassroomExistsException {
		if (! repository.existsByName(classe.getClassName())) {
			ClassEntity entity = repository.save(this.mapper.map(classe, ClassEntity.class));
			return mapper.map(entity, ClassModel.class);
		}
		throw new ClassroomExistsException();
	}

	@Override
	public List<ClassModel> getAllClassroom() {
		List<ClassModel> output = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, ClassModel.class)));
		return output;
	}

}
