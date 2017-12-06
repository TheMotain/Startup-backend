package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.QuestionEntity;

/**
 * Repository traitant les actions des questions en base de données
 *
 * @author Hélène MEYER
 *
 */
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long>{

}
