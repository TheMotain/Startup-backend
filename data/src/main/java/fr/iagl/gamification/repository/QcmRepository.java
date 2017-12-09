package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.QcmEntity;

/**
 * Repository traitant les actions du QCM dans la base de données
 *
 * @author Hélène MEYER
 *
 */
public interface QcmRepository extends CrudRepository<QcmEntity, Long>{
	
	public List<QcmEntity> findByClass(Long idClass);

}
