package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.ResultQcmEntity;

public interface ResultQcmRepository extends CrudRepository<ResultQcmEntity, Long>{

	public List<ResultQcmEntity> findByAnswer_Question_Qcm_Id(Long id);
}
