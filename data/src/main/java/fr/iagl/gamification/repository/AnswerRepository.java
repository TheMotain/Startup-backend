package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.AnswerEntity;

/**
 * Repository traitant les actions des choix du QCM dans la base de données
 *
 * @author Hélène MEYER
 *
 */
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long>{

}
