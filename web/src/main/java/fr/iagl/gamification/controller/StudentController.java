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

import fr.iagl.gamification.exceptions.ClassroomAlreadyExistedException;
import fr.iagl.gamification.exceptions.ClassroomNotFoundException;
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.object.StudentObject;
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
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentModel.class, responseContainer = "list", message = "Liste des élève")
	public ResponseEntity<List<StudentObject>> getAllStudent() {
		LOG.info("Récupération de la liste des élèves");
		List<StudentModel> result = studentService.getAllStudent();
		List<StudentObject> students = new ArrayList<>();
		Optional.ofNullable(result)
					.orElseGet(Collections::emptyList)
					.iterator()
					.forEachRemaining(e -> students.add(mapper.map(e, StudentObject.class)));
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
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_CREATED, response = StudentModel.class, message = "Elève créé"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs à la validation du formulaire") })
	public ResponseEntity createStudent(@Valid @RequestBody StudentForm studentForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			LOG.info("Call createOrUpdateStudent from service");
			StudentModel studentCreated = studentService.saveStudent(mapper.map(studentForm, StudentModel.class));
			LOG.info("Return createOrUpdateStudent from service");
			
			if (studentCreated != null) {
				return new ResponseEntity<StudentObject>(mapper.map(studentCreated, StudentObject.class), HttpStatus.CREATED);
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
	@RequestMapping(value=MappingConstant.POST_ADD_CLASS, method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = HttpsURLConnection.HTTP_OK, response = StudentModel.class, message = "Classe ajoutée à l'élève"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs") })
	public ResponseEntity<StudentObject> addClassToStudent(@Valid @RequestBody LinkStudentClassForm linkForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {

			StudentModel studentModified;
			try {
				studentModified = studentService.addClassToStudent(linkForm.getIdStudent(), linkForm.getIdClass());
				if (studentModified != null) {
					return new ResponseEntity<>(mapper.map(studentModified, StudentObject.class), HttpStatus.OK);
				}
			} catch (StudentNotFoundException e) {
				errors = Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT);
				LOG.warn(CodeError.ERROR_NOT_EXISTS_STUDENT);
			} catch (ClassroomNotFoundException e) {
				errors = Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM);
				LOG.warn(CodeError.ERROR_NOT_EXISTS_CLASSROOM);
			} catch (ClassroomAlreadyExistedException e) {
				errors = Arrays.asList(CodeError.ERROR_CLASS_ALREADY_EXISTS);
				LOG.warn(CodeError.ERROR_CLASS_ALREADY_EXISTS);
			}
		}
		
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value=MappingConstant.POST_DELETE_STUDENT_CLASS, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK,response = StudentModel.class, message = "élève supprimé de la classe"),
				@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list" , message = "Liste des erreurs")})
	public ResponseEntity<StudentObject> deleteStudentFromClass(@Valid @RequestBody Long idStudent,BindingResult bindingResult){
		
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		if(bindingResult.hasErrors()){
			errors = RequestTools.transformBindingErrors(bindingResult);
			}else{
				StudentModel studentUpdate ;
				try {
					studentUpdate=studentService.deleteStudentFromClass(idStudent);
					if (studentUpdate != null) {
						return new ResponseEntity<>(mapper.map(studentUpdate, StudentObject.class), HttpStatus.OK);
					}
				} catch (StudentNotFoundException e) {
					errors = Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT);
				}
		}
		
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	
	}
}
