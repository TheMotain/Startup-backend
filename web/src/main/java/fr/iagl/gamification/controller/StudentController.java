package fr.iagl.gamification.controller;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.LinkStudentClassForm;
import fr.iagl.gamification.validator.StudentForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller pour la gestion des élèves
 * 
 * @author ALEX
 *
 */
@RestController
@RequestMapping(value = MappingConstant.STUDENT_PATH_ROOT)
public class StudentController implements AbstractController {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(StudentController.class);
	
	/**
	 * Service de gestion des élèves
	 */
	@Autowired
	private StudentService studentService;

	/**
	 * Mapper Form <-> Model
	 */
	@Autowired
	private Mapper mapper;

	/**
	 * Récupère la liste de tous les élèves
	 * 
	 * @return Response contenant la liste des élèves
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentModel.class, responseContainer = "list", message = "Liste des élève")
	public ResponseEntity<List<StudentModel>> getAllStudent() {
		LOG.info("Récupération de la liste des élèves");
		List<StudentModel> result = studentService.getAllStudent();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Récupère la liste de tous les élèves n'ayant pas de classe
	 * 
	 * @return Response contenant la liste des élèves sans classe
	 */
	@RequestMapping(value = MappingConstant.GET_STUDENTS_WITHOUT_CLASS , method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentModel.class, responseContainer = "list", message = "Liste des élève n'ayant pas de classe")
	public ResponseEntity<List<StudentModel>> getAllStudentWithoutClass() {
		LOG.info("Récupération de la liste des élèves sans classe");
		List<StudentModel> result = studentService.getStudentsWithoutClass();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * Permet de créer un nouvel élève
	 * 
	 * @param studentForm
	 *            Contient les informations de l'élève à créer
	 * @param bindingResult
	 *            Contient les résultats de la validation du formulaire
	 * @return L'élève qui a été créé
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_CREATED, response = StudentModel.class, message = "Elève créé"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs à la validation du formulaire") })
	public ResponseEntity createStudent(@Valid @RequestBody StudentForm studentForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<List<String>>(RequestTools.transformBindingErrors(bindingResult),
					HttpStatus.BAD_REQUEST);
		}
		LOG.info("Call createOrUpdateStudent from service");
		StudentModel studentCreated = studentService.saveStudent(mapper.map(studentForm, StudentModel.class));
		LOG.info("Return createOrUpdateStudent from service");
		return new ResponseEntity<StudentModel>(studentCreated, HttpStatus.CREATED);
	}
	
	/**
	 * Ajouter une classe 
	 * 
	 * @param linkForm le formulaire contenant l'identifiant de l'élève et celui de la classe
	 * @param bindingResult
	 * @return l'objet modifié
	 */
	@RequestMapping(value=MappingConstant.POST_ADD_CLASS, method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentModel.class, message = "Classe ajoutée à l'élève"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs") })
	public ResponseEntity addClassToStudent(@Valid @RequestBody LinkStudentClassForm linkForm, BindingResult bindingResult) {
		List<String> errors;
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {

			StudentModel studentModified;
			try {
				studentModified = studentService.addClassToStudent(linkForm.getIdStudent(), linkForm.getIdClass());
				return new ResponseEntity<StudentModel>(studentModified, HttpStatus.OK);
			} catch (StudentNotFoundException e) {
				errors = Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT);
				LOG.warn(CodeError.ERROR_NOT_EXISTS_STUDENT);
			} catch (ClassroomNotFoundException e) {
				errors = Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM);
				LOG.warn(CodeError.ERROR_NOT_EXISTS_CLASSROOM);
			}
		}
		
		return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
	}
}
