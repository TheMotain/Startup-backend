package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.model.ResultQcmModel;

public interface ResultQcmService {

	public List<ResultQcmModel> getAllQcmResultsByIdQcm(Long id);
	
}
