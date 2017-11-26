package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
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
	public ClassModel createClass(@QueryParam("classe") ClassModel classe) throws GamificationServiceException {
		if (! repository.existsByName(classe.getClassName())) {
			ClassEntity entity = repository.save(this.mapper.map(classe, ClassEntity.class));
			return mapper.map(entity, ClassModel.class);
		}
		throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_EXISTS_CLASS));
	}

	@Override
	public List<ClassModel> getAllClassroom() {
		List<ClassModel> output = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, ClassModel.class)));
		return output;
	}

}
