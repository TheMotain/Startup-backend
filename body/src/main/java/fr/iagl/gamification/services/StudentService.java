package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.ClassroomAlreadyExistedException;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
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
	 */
	public StudentModel saveStudent(StudentModel model);

	
	
	/**
	 * Supprimer un élève d'une classe
	 * @param idStudent identifiant de l'élève à supprimer
	 * @return élève
	 * @throws StudentNotFoundException 
	 */
	public StudentModel deleteStudentFromClass(long idStudent) throws StudentNotFoundException;

	/**
	 * Ajouter un élève dans une classe
	 * 
	 * @param idStudent  identifiant de l'élève
	 * @param idClass identifiant de la classe
	 * @return élève 
	 * 
	 * @throws StudentNotFoundException 
	 * @throws ClassroomNotFoundException 
	 * @throws ClassroomAlreadyExistedException 
	 */
	public StudentModel addClassToStudent(long idStudent, long idClass) throws StudentNotFoundException, ClassroomNotFoundException, ClassroomAlreadyExistedException;
	
}
