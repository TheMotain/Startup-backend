package fr.iagl.gamification.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.data.entity.MessageEntity;
	
/**
 * Repository de la table message
 * 
 * @author Hélène Meyer
 *
 */
public interface MessageRepository  extends CrudRepository<MessageEntity, Long>{
		
	/**
	 * Récupération de la liste des messages en fonction d'un envoyeur
	 * 
	 * @param sender le nom de celui qui a envoyé tous les messages
	 * @return la liste de tous les messages envoyé par sender
	 */
	List<MessageEntity> findBySender(String sender);
	
}
