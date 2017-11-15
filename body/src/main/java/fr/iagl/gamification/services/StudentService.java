package fr.iagl.gamification.services;

import java.util.List;

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
	 * Récupère tous les élèves sans classe
	 * @return Liste d'élève
	 */
	public List<StudentModel> getStudentsWithoutClass();
	
	/**
	 * Créer ou Modifier un élève
	 * @param model élève à créer ou à modifier
	 * @return élève créé ou modifié
	 */
	public StudentModel saveStudent(StudentModel model);

	/**
	 * Ajouter un élève dans une classe
	 * 
	 * @param idStudent  identifiant de l'élève
	 * @param idClass identifiant de la classe
	 * @return élève 
	 * 
	 * @throws StudentNotFoundException 
	 * @throws ClassroomNotFoundException 
	 */
	public StudentModel addClassToStudent(long idStudent, long idClass) throws StudentNotFoundException, ClassroomNotFoundException;
	
}
