package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.ClassEntity;

/**
 * Repository pour les appels dans la table classroom
 * 
 * @author Hélène Meyer
 *
 */
public interface ClassRepository extends CrudRepository<ClassEntity, String>{

	/**
	 * Retourne true si le nom de la classe est déjà enregistré en base de donnée, sinon retourne false
	 * 
	 * @param classname le nom de la classe
	 * @return true si le nom de la classe est déjà enregistré en base de donnée, sinon retourne false
	 */
	boolean existsByName(String classname);
}
