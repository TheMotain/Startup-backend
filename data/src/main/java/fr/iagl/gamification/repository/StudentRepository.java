package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.iagl.gamification.entity.StudentEntity;

/**
 * Repository pour la manipulation des étudiants
 * @author ALEX
 *
 */
@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long>{

}
