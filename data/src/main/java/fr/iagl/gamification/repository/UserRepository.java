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

	/**
	 * Donne tous les utilisateurs ayant le rôle de professeur
	 * 
	 * @param role le code role
	 * @return la liste des utilisateurs ayant un code role 'TEACHER'
	 */
	public List<UserEntity> findByRole_Role(String role);
	
	/**
	 * Renvoie l'utilisateur 
	 * 
	 * @param email email du professeur
	 * @param role role du professeur
	 * @return l'utilisateur ayant le rôle professeur et le mail 
	 */
	public UserEntity findByEmailAndRole_Role(String email, String role);
}
