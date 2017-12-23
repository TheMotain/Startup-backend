package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.TeacherModel;

public interface TeacherService {

	public TeacherModel createTeacher(TeacherModel teacher) throws GamificationServiceException;

	public List<TeacherModel> getAllTeacher();
}
