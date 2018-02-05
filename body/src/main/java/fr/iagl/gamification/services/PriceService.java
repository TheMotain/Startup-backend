package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.model.PriceModel;

/**
 * Service pour la manipulation des avatar et leur prix
 * @author dalencourt
 *
 */
public interface PriceService {
	
	List<PriceModel> listAllAvatar();
}
