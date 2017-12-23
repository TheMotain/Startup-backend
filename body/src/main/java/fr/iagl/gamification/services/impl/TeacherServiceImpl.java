package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import fr.iagl.gamification.utils.CryptPassword;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public TeacherModel createTeacher(TeacherModel teacher) throws GamificationServiceException {
		UserEntity user = userRepository.findByEmail(teacher.getEmail());
		if (user != null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_EMAIL_ALREADY_EXISTS));
		}
		RoleUserEntity role = roleUserRepository.findByRole(ServiceConstants.CODE_TEACHER);
		
		UserEntity entity = mapper.map(teacher, UserEntity.class);
		entity.setRole(role);
		entity.setPassword(CryptPassword.encryptPassword(teacher));
		entity = userRepository.save(entity);
		return mapper.map(entity, TeacherModel.class);
	}

	@Override
	public List<TeacherModel> getAllTeacher() {
		List<TeacherModel> output = new ArrayList<>();
		userRepository.findByRole_Role(ServiceConstants.CODE_TEACHER).iterator().forEachRemaining(x -> output.add(mapper.map(x, TeacherModel.class)));
		return output;
	}

}
