package fr.iagl.gamification.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.StudentForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller pour la gestion des élèves
 * 
 * @author ALEX
 *
 */
@RequestMapping(value = MappingConstant.STUDENT_PATH_ROOT)
public class StudentController extends AbstractController{

	/**
	 * Service de gestion des élèves
	 */
	@Autowired
	private StudentService studentService;

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
		List<StudentModel> result = studentService.getAllStudent();
		return new ResponseEntity<List<StudentModel>>(result, HttpStatus.OK);
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

		StudentModel studentCreated = studentService.createStudent(mapper.map(studentForm, StudentModel.class));
		return new ResponseEntity<StudentModel>(studentCreated, HttpStatus.CREATED);
	}
}
