/**
 * 
 */
package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.entity.PriceEntity;
import fr.iagl.gamification.entity.pk.InventairePK;

/**
 * Répository pour la manipulation de l'inventaire
 * 
 * @author dalencourt
 *
 */
@Repository
public interface InventaireRepository extends CrudRepository<InventaireEntity, InventairePK> {
	
	/**
	 * Récupère la liste des avatar acheté pour l'élève en paramètre
	 * @param studentId identifiant de l'élève
	 * @return liste des avatars
	 */
	List<InventaireEntity> findByIdStudentId(Long studentId);
}
