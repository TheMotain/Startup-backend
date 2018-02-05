package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.iagl.gamification.entity.Price;

/**
 * Répository de manipulation BDD
 * @author dalencourt
 *
 */
@Repository
public interface PriceRepository extends CrudRepository<Price, String> {
	
}
