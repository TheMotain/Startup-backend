package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.repository.ResultQcmRepository;
import fr.iagl.gamification.services.ResultQcmService;

@Service
public class ResultQcmServiceImpl implements ResultQcmService{

	@Autowired
	private ResultQcmRepository repository;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<ResultQcmModel> getAllQcmResultsByIdQcm(Long id) {
		List<ResultQcmModel> output = new ArrayList<>();
		repository.findByAnswer_Question_Qcm_Id(id).iterator().forEachRemaining(x -> output.add(mapper.map(x, ResultQcmModel.class)));
		return output;
	}

}
