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
import fr.iagl.gamification.entity.PriceEntity;
import fr.iagl.gamification.entity.pk.InventairePK;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.repository.InventaireRepository;
import fr.iagl.gamification.repository.PriceRepository;
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
	 * Repository inventaire
	 */
	@Autowired
	private InventaireRepository inventaireRepository;
	
	/**
	 * Repository Student
	 */
	@Autowired
	private StudentRepository studentRepository;	
	
	/**
	 * Repository Pricing
	 */
	@Autowired
	private PriceRepository priceRepository;
	
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

	@Override
	public InventaireEntity createDefaultAvatar(Long id) {
		InventaireEntity inventaireEntity = new InventaireEntity();
		inventaireEntity.getId().setStudentId(id);
		inventaireEntity.getId().setAvatarRef(priceRepository.findDefaultAvatar());
		return inventaireRepository.save(inventaireEntity);
	}
	
	@Override
	public InventaireEntity findAvatarForStudent(String avatar, Long idStudent) {
		PriceEntity entity = new PriceEntity();
		entity.setAvatar(avatar);
		return inventaireRepository.findOne(new InventairePK(idStudent, entity));
	}
}
