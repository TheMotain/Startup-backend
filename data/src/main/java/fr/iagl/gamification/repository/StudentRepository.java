package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.StudentEntity;

/**
 * Repository pour les appels dans la classe pupil
 * 
 * @author ALEX
 *
 */
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
	
}
