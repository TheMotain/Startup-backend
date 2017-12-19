package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.StudentEntity;

/**
 * Repository pour les appels dans la classe pupil
 * 
 * @author ALEX
 *
 */
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

	/**
	 * Récupère un utilisateur par son uuid
	 * @param uuid Identifiant unique de connexion
	 * @return Etudiant correspondant ou null
	 */
	StudentEntity findByUuid(String uuid);
	
}
