package fr.iagl.gamification.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.data.entity.MessageEntity;

public interface MessageRepository  extends CrudRepository<MessageEntity, Long>{
		List<MessageEntity> findBySender(String sender);
		
}
