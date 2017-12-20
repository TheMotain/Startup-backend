package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.UserEntity;

/** Repository pour les appels dans la table user
 * 
 * @author Nadia
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{

}
