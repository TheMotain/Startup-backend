package fr.iagl.gamification.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.ServiceConstants;
import fr.iagl.gamification.entity.RoleUserEntity;
import fr.iagl.gamification.entity.UserEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.repository.RoleUserRepository;
import fr.iagl.gamification.repository.UserRepository;
import fr.iagl.gamification.services.TeacherService;
import fr.iagl.gamification.utils.CryptPasswordService;

/**
 * Service pour traiter les utilisateur ayant un rôle de professeur
 *
 * @author Hélène MEYER
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService{

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(TeacherServiceImpl.class);
	
	
	/**
	 * repository de l'utilisateur
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * repository des roles de l'utilisateur
	 */
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	/**
	 * service qui crypte les mots de passe
	 */
	@Autowired
	private CryptPasswordService cryptPassword;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public TeacherModel createTeacher(TeacherModel teacher) throws GamificationServiceException {
		UserEntity user = userRepository.findByEmailAndRole_Role(teacher.getEmail(), ServiceConstants.CODE_TEACHER);
		if (user != null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_EMAIL_ALREADY_EXISTS));
		}
		RoleUserEntity role = roleUserRepository.findByRole(ServiceConstants.CODE_TEACHER);
		
		UserEntity entity = mapper.map(teacher, UserEntity.class);
		entity.setRole(role);
		
		try {
			entity.setPassword(cryptPassword.encryptPassword(teacher));
			entity = userRepository.save(entity);
			return mapper.map(entity, TeacherModel.class);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.warn("cryptage impossible");
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_CRYPTAGE_PASSWORD));
		}
		
	}

	@Override
	public List<TeacherModel> getAllTeacher() {
		List<TeacherModel> output = new ArrayList<>();
		userRepository.findByRole_Role(ServiceConstants.CODE_TEACHER).iterator().forEachRemaining(x -> output.add(mapper.map(x, TeacherModel.class)));
		return output;
	}

}
