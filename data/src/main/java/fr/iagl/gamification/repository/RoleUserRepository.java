package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.RoleUserEntity;

/**
 * Traitement avec la table ROLE_USER
 *
 * @author Hélène MEYER
 *
 */
public interface RoleUserRepository  extends CrudRepository<RoleUserEntity, Long>{

	/**
	 * Recupère le role ayant le code role 'role'
	 * 
	 * @param role le code role du role
	 * @return le role
	 */
	public RoleUserEntity findByRole(String role);
}
