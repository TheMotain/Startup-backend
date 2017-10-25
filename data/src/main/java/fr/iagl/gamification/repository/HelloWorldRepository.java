package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.iagl.gamification.entity.HelloWorldEntity;

@Repository
public interface HelloWorldRepository extends CrudRepository<HelloWorldEntity, String> {

}
