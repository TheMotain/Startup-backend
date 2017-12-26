package fr.iagl.gamification.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.TeacherModel;

/**
 * Interface du service des professeur
 *
 * @author Hélène MEYER
 *
 */
public interface TeacherService {

	/**
	 * Créer un professeur
	 * 
	 * @param teacher le modèle du professeur à ajouter
	 * @return le professeur créé
	 * @throws GamificationServiceException si le mail du professeur existe déjà en base de données
	 */
	public TeacherModel createTeacher(TeacherModel teacher) throws GamificationServiceException;

	/**
	 * Récupère tous les professeurs
	 * 
	 * @return la liste de tous les professeurs
	 */
	public List<TeacherModel> getAllTeacher();

	public boolean teacherExists(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	public TeacherModel getTeacherByMail(String email);
}
