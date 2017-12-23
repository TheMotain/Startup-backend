package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.UserEntity;

/** Repository pour les appels dans la table user
 * 
 * @author Nadia
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{

	public List<UserEntity> findByRole_Role(String role);
	
	public UserEntity findByEmail(String email);
}
