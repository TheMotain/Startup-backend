package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
	
}
