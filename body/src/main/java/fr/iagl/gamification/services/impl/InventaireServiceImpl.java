/**
 * 
 */
package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.repository.InventaireRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.InventaireService;

/**
 * Implémentation du service {@link InventaireService}
 * 
 * @author dalencourt
 *
 */
@Service
public class InventaireServiceImpl implements InventaireService {

	/**
	 * Service inventaire
	 */
	@Autowired
	private InventaireRepository inventaireRepository;
	
	@Autowired
	private StudentRepository studentRepository;	
	
	@Override
	public List<String> getAllBougthAvatar(Long id) throws GamificationServiceException {
		if(id == null || studentRepository.findOne(id) == null) {
			throw new GamificationServiceException(Arrays.asList("Etudiant non trouvé"));
		}
		List<String> output = new ArrayList<>();
		List<InventaireEntity> in = inventaireRepository.findByIdStudentId(id);
		if(in != null) {
			in.forEach(x -> output.add(x.getId().getAvatarRef().getAvatar()));
		}
		return output;
	}

}
