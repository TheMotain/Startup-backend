package fr.iagl.gamification.model;

import java.util.List;

import fr.iagl.gamification.entity.ClassEntity;

/**
 * Classe représentant un professeur
 *
 * @author Hélène MEYER
 *
 */
public class TeacherModel extends UserModel{

	public TeacherModel() {}
	
	public TeacherModel(Long id, String uuid, String name, String email, String password, List<ClassModel> classrooms){
		this.id= id;
		this.uuid = uuid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.classrooms = classrooms;
	}
	
	public TeacherModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	
}
