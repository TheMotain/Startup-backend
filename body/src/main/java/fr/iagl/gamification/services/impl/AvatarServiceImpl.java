package fr.iagl.gamification.services.impl;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AvatarEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.repository.AvatarRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService{

	@Autowired
	private AvatarRepository avatarRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public AvatarModel findAvatar(Long idStudent) throws GamificationServiceException {
		StudentEntity entity = null;
		if(null == idStudent || null == (entity = studentRepository.findOne(idStudent))) {
			throw new GamificationServiceException(Arrays.asList("ID étudiant non connu"));
		}
		AvatarEntity result = avatarRepository.findOne(idStudent);
		if(result == null) {
			result = new AvatarEntity();
			result.setStudent(entity);
			result = avatarRepository.save(result);
		}
		return mapper.map(result, AvatarModel.class);
	}

}
