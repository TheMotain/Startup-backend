package fr.iagl.gamification.repository;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.RoleUserEntity;

public interface RoleUserRepository  extends CrudRepository<RoleUserEntity, Long>{

	public RoleUserEntity findByRole(String role);
}
