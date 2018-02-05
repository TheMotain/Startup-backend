/**
 * 
 */
package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.Price;
import fr.iagl.gamification.model.PriceModel;
import fr.iagl.gamification.repository.PriceRepository;
import fr.iagl.gamification.services.PriceService;

/**
 * Implementation du service {@link PriceService}
 * 
 * @author dalencourt
 *
 */
@Service
public class PriceServiceImpl implements PriceService {

	/**
	 * Répository price
	 */
	@Autowired
	private PriceRepository priceRepository;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<PriceModel> listAllAvatar() {
		List<PriceModel> output = new ArrayList<>();
		Iterable<Price> in = priceRepository.findAll();
		if(in != null) {
			in.forEach(x -> output.add(mapper.map(x, PriceModel.class)));
		}
		return output;
	}

}
