package fr.iagl.gamification.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.StudentForm;


/**
 * Controller pour la gestion des élèves
 * @author ALEX
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = MappingConstant.STUDENT_PATH_ROOT)
public class StudentController {

	/**
	 * Service de gestion des élèves
	 */
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private Mapper mapper;
	
	/**
	 * Récupère la liste de tous les élèves
	 * @return Response contenant la liste des élèves
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentModel>> getAllStudent(){
		List<StudentModel> result = studentService.getAllStudent();
		return new ResponseEntity<List<StudentModel>>(result, HttpStatus.OK);
	}
	
	/**
	 * Permet de créer un nouvel élève
	 * @param studentForm Contient les informations de l'élève à créer
	 * @param bindingResult Contient les résultats de la validation du formulaire
	 * @return L'élève qui a été créé
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@Valid @RequestBody StudentForm studentForm, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			
			return new ResponseEntity<List<String>>(RequestTools.transformBindingErrors(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		StudentModel studentCreated = studentService.createStudent(mapper.map(studentForm, StudentModel.class));
		return new ResponseEntity<StudentModel>(studentCreated, HttpStatus.OK);
	}
}
