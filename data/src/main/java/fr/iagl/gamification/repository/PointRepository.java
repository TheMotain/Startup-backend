package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.PointEntity;

/**
 * Repository de la table Point
 * 
 * @author Hélène MEYER
 *
 */
public interface PointRepository extends CrudRepository<PointEntity, Long>{

	public PointEntity findByStudent_Id(Long id);
}
