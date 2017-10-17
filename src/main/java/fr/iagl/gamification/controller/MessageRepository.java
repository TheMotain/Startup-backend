package fr.iagl.gamification.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository  extends CrudRepository<MessageEntity, Long>{
		List<MessageEntity> findBySender(String sender);
}
