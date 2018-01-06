package fr.iagl.gamification.model;

/**
 * Classe représentant un professeur
 *
 * @author Hélène MEYER
 *
 */
public class TeacherModel extends UserModel{

	public TeacherModel() {}
	
	public TeacherModel(Long id, String uuid, String name, String email, String password){
		this.id= id;
		this.uuid = uuid;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public TeacherModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	
}
