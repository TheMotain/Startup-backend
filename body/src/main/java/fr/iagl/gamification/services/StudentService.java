package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.StudentModel;

/**
 * Interface de gestion des élèves
 * @author ALEX
 *
 */
public interface StudentService {

	/**
	 * Récupère tous les élèves
	 * @return Liste d'élève
	 */
	public List<StudentModel> getAllStudent();
	
	/**
	 * Créer ou Modifier un élève
	 * @param model élève à créer ou à modifier
	 * @return élève créé ou modifié
	 * @throws GamificationServiceException lorsque la classe n'existe pas
	 */
	public StudentModel saveStudent(StudentModel model) throws GamificationServiceException;

	
	
	/**
	 * Supprimer un élève d'une classe
	 * @param idStudent identifiant de l'élève à supprimer
	 * @return élève
	 * @throws GamificationServiceException  lorsque l'élève n'existe pas
	 */
	public StudentModel deleteStudentFromClass(long idStudent) throws GamificationServiceException;

	/**
	 * Ajouter un élève dans une classe
	 * 
	 * @param idStudent  identifiant de l'élève
	 * @param idClass identifiant de la classe
	 * @return élève 
	 * 
	 * @throws GamificationServiceException lorsque l'élève / la classe n'existe pas
	 */
	public StudentModel addClassToStudent(long idStudent, long idClass) throws GamificationServiceException;
	
}
