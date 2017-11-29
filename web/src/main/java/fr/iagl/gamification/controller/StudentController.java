package fr.iagl.gamification.controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import fr.iagl.gamification.exceptions.GamificationServiceException;
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
	@RequestMapping(value = MappingConstant.STUDENT_PATH_ROOT, method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentForm.class, responseContainer = "list", message = "Liste des élève")
	public ResponseEntity<List<StudentForm>> getAllStudent() {
		LOG.info("Récupération de la liste des élèves");
		List<StudentModel> result = studentService.getAllStudent();
		List<StudentForm> students = new ArrayList<>();
		Optional.ofNullable(result)
					.orElseGet(Collections::emptyList)
					.iterator()
					.forEachRemaining(e -> students.add(mapper.map(e, StudentForm.class)));
		return new ResponseEntity<>(students, HttpStatus.OK);
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
	@RequestMapping(value = MappingConstant.STUDENT_PATH_ROOT, method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_CREATED, response = StudentForm.class, message = "Elève créé"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs à la validation du formulaire") })
	public ResponseEntity createStudent(@Valid @RequestBody StudentForm studentForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			LOG.info("Call createOrUpdateStudent from service");
			StudentModel studentCreated;
			try {
				studentCreated = studentService.saveStudent(mapper.map(studentForm, StudentModel.class));
				LOG.info("Return saveStudent from service");
				
				if (studentCreated != null) {
					return new ResponseEntity<StudentForm>(mapper.map(studentCreated, StudentForm.class), HttpStatus.CREATED);
				}
			} catch (GamificationServiceException e) {
				LOG.warn("Erreur lors de l'appel au service studentService (saveStudent)");
				errors = e.getErrors();
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Ajouter une classe 
	 * 
	 * @param linkForm le formulaire contenant l'identifiant de l'élève et celui de la classe
	 * @param bindingResult
	 * @return l'objet modifié
	 */
	@RequestMapping(value=MappingConstant.POST_ADD_CLASS_TO_STUDENT, method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentForm.class, message = "Classe ajoutée à l'élève"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs") })
	public ResponseEntity<StudentForm> addClassToStudent(@Valid @RequestBody LinkStudentClassForm linkForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {

			StudentModel studentModified;
			try {
				studentModified = studentService.addClassToStudent(linkForm.getIdStudent(), linkForm.getIdClass());
				if (studentModified != null) {
					return new ResponseEntity<>(mapper.map(studentModified, StudentForm.class), HttpStatus.OK);
				}
			} catch (GamificationServiceException gse) {
				errors = gse.getErrors();
				LOG.error(errors);
			}
		}
		
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Supprime un élève d'une classe
	 * @param idStudent Id de l'élève à supprimer
	 * @param bindingResult binding mvc
	 * @return L'étudiant qui a été modifié
	 */
	@RequestMapping(value=MappingConstant.POST_DELETE_STUDENT_CLASS, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK,response = StudentForm.class, message = "élève supprimé de la classe"),
				@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list" , message = "Liste des erreurs")})
	public ResponseEntity<StudentForm> deleteStudentFromClass(@Valid @RequestBody Long idStudent,BindingResult bindingResult){
		
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		if(bindingResult.hasErrors()){
			errors = RequestTools.transformBindingErrors(bindingResult);
			}else{
				StudentModel studentUpdate ;
				try {
					studentUpdate=studentService.deleteStudentFromClass(idStudent);
					if (studentUpdate != null) {
						return new ResponseEntity<>(mapper.map(studentUpdate, StudentForm.class), HttpStatus.OK);
					}
				} catch (GamificationServiceException e) {
					errors = e.getErrors();
					LOG.error(errors);
				}
		}
		
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	
	}
}
