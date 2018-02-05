package fr.iagl.gamification.services.impl;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AvatarEntity;
import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.entity.pk.InventairePK;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.repository.PriceRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.AvatarService;

/**
 * Service permettant la manipulation d'un Avatar
 * @author dalencourt
 *
 */
@Service
public class AvatarServiceImpl implements AvatarService{

	/**
	 * Message d'erreur
	 */
	private static final String ERROR_ID_ETUDIANT_NON_CONNU = "ID étudiant non connu";

	/**
	 * Repository pour la manipulation des avatars
	 */
	@Autowired
	private AvatarRepository avatarRepository;
	
	/**
	 * Repository pour la récupération d'un étudiant
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Répository pour récupérer la liste des avatars existant
	 */
	@Autowired
	private PriceRepository priceRepository;
	
	/**
	 * Mapper
	 */
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public AvatarModel findAvatar(Long idStudent) throws GamificationServiceException {
		StudentEntity entity = null;
		if(null == idStudent || null == (entity = studentRepository.findOne(idStudent))) {
			throw new GamificationServiceException(Arrays.asList(ERROR_ID_ETUDIANT_NON_CONNU));
		}
		AvatarEntity result = avatarRepository.findOne(idStudent);
		if(result == null) {
			return(createDefaultAvatar(entity));
		}
		return mapper.map(result, AvatarModel.class);
	}

	@Override
	public AvatarModel updateAvatar(Long idStudent, String avatar) throws GamificationServiceException {
		StudentEntity studentEntity = null;
		if(null == idStudent || null == (studentEntity = studentRepository.findOne(idStudent))) {
			throw new GamificationServiceException(Arrays.asList(ERROR_ID_ETUDIANT_NON_CONNU));
		}
		AvatarEntity avatarEntity = avatarRepository.findOne(idStudent);
		if(null == avatarEntity) {
			avatarEntity = new AvatarEntity();
			avatarEntity.setStudent(studentEntity);
		}
		avatarEntity.setAvatar(avatar);
		avatarEntity = avatarRepository.save(avatarEntity);
		return mapper.map(avatarEntity, AvatarModel.class);
	}

	/* (non-Javadoc)
	 * @see fr.iagl.gamification.services.AvatarService#createDefaultAvatar()
	 */
	@Override
	public AvatarModel createDefaultAvatar(StudentEntity student) {
		AvatarEntity result = new AvatarEntity();
		result.setStudent(student);
		result.setInventaire(new InventaireEntity(new InventairePK(student.getId(), priceRepository.findDefaultAvatar())));
		result = avatarRepository.save(result);
		return mapper.map(result, AvatarModel.class);
	}
}
