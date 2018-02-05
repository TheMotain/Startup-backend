package fr.iagl.gamification.repository;

import org.springframework.data.jpa.repository.Query;
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
	
	/**
	 * Récupère l'avatar par de défaut
	 * <br>
	 * Cet avatar est identifié par l'id default
	 * @return L'avatar par défaut.
	 */
	@Query("SELECT a FROM PriceEntity a WHERE a.id = 'default'")
	PriceEntity findDefaultAvatar();
	
}
