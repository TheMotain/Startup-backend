package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.AvatarEntity;

/**
 * Répository de manipulation base de données pour l'avatar
 * 
 * @author dalencourt
 *
 */
public interface AvatarRepository extends CrudRepository<AvatarEntity, Long>{

}
