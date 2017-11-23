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

	/**
	 * Recherche les points par l'identifiant de l'élève
	 * 
	 * @param id identifiant de l'élève
	 * @return l'objet représentant les points de l'élève
	 */
	public PointEntity findByStudent_Id(Long id);
}
