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

	/**
	 * Retourne vrai si le professeur existe
	 * 
	 * @param email le mail de l'utilisateur
	 * @param password le mot de passe non encrypté de l'utilisateur
	 * @return vrai si le professeur existe sinon faux
	 * @throws NoSuchAlgorithmException s'il y a un problème de cryptage
	 * @throws UnsupportedEncodingException d'il y a un problème de cryptage
	 */
	public boolean teacherExists(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	/**
	 * Récupère le professeur grâce à son mail et retourne null s'il n'existe pas
	 * 
	 * @param email du professeur
	 * @return le professeur
	 */
	public TeacherModel getTeacherByMail(String email);
}
