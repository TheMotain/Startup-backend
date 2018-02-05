package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.iagl.gamification.entity.PriceEntity;

/**
 * Répository de manipulation BDD
 * @author dalencourt
 *
 */
@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, String> {
	
}
