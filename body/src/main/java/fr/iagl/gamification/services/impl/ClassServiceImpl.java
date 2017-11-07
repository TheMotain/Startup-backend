package fr.iagl.gamification.services.impl;

import javax.ws.rs.QueryParam;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.exceptions.ClassExistsException;
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
	public ClassModel createClass(@QueryParam("classe") ClassModel classe) throws ClassExistsException {
		if (! repository.existsByName(classe.getClassName())) {
			ClassEntity entity = repository.save(this.mapper.map(classe, ClassEntity.class));
			return mapper.map(entity, ClassModel.class);
		}
		throw new ClassExistsException();
	}

}
