package fr.iagl.gamification.services.impl;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AvatarEntity;
import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.AvatarService;
import fr.iagl.gamification.services.InventaireService;

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
	 * Service traitant les avatar qui ont été achetés
	 */
	@Autowired
	private InventaireService inventaireService;
	
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
		InventaireEntity inventaireEntity = null;
		if(null == idStudent || null == (studentEntity = studentRepository.findOne(idStudent))) {
			throw new GamificationServiceException(Arrays.asList(ERROR_ID_ETUDIANT_NON_CONNU));
		}
		if(null == avatar || null == (inventaireEntity = inventaireService.findAvatarForStudent(avatar,idStudent))) {
			throw new GamificationServiceException(Arrays.asList("Avatar non disponible"));
		}
		AvatarEntity avatarEntity = avatarRepository.findOne(idStudent);
		if(null == avatarEntity) {
			avatarEntity = new AvatarEntity();
			avatarEntity.setStudent(studentEntity);
		}
		avatarEntity.setAvatar(inventaireEntity.getId().getAvatarRef().getAvatar());
		avatarEntity = avatarRepository.save(avatarEntity);
		return mapper.map(avatarEntity, AvatarModel.class);
	}

	@Override
	public AvatarModel createDefaultAvatar(StudentEntity student) {
		AvatarEntity result = new AvatarEntity();
		result.setStudent(student);
		inventaireService.createDefaultAvatar(student.getId());
		result.setAvatar("avatar1");
		result = avatarRepository.save(result);
		return mapper.map(result, AvatarModel.class);
	}
}
