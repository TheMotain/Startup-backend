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
	 * Créer un élève
	 * @param model élève à créer
	 * @return élève créé
	 */
	public StudentModel createStudent(StudentModel model);
	
	
	/**
	 * Supprimer un élève d'une classe
	 * @param idStudent identifiant de l'élève à supprimer
	 * @param idClass identifiant de la classe
	 * @return élève
	 * @throws StudentNotFoundException 
	 * @throws ClassroomNotFoundException 
	 */
	public StudentModel deleteStudentFromClass(long idStudent, long idClass) throws StudentNotFoundException, ClassroomNotFoundException;
}
