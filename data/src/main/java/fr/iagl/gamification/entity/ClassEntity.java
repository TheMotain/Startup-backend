package fr.iagl.gamification.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entité représentant une classe
 * 
 * @author Hélène Meyer
 *
 */
@Entity
@Table(name = "classroom")
public class ClassEntity implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -3650567266024965280L;


	/**
	 * id de la classe
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	
	/**
	 * Nom de la classe
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Récupération de la liste des élèves pour la classe
	 */
	@OneToMany(mappedBy = "classroom")
	private List<StudentEntity> students;
	
	
	/**
	 * Récupération des professeurs de cette classe
	 */
	@ManyToMany(mappedBy = "classrooms")
	private List<UserEntity> teachers  = new ArrayList<>();
	
	/**
	 * Constructeur vide
	 */
	public ClassEntity() {
		
	}
	
	/**
	 * Constructeur avec nom de la classe en paramètre
	 * @param name Nom de la classe à créer
	 */
	public ClassEntity(String name) {
		this.name = name;
	}

	/**
	 * Getter du nom de la classe
	 * 
	 * @return le nom de la classe
	 */
	public String getClassName() {
		return name;
	}

	/**
	 * Setter du nom de la classe
	 * 
	 * @param className le nouveau nom de la classe
	 */
	public void setClassName(String className) {
		this.name = className;
	}

	/**
	 * Getter de l'id
	 * 
	 * @return l'id de la classe
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'id
	 * 
	 * @param id de la classe
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link ClassEntity#teachers}
	 * @return teachers
	 */
	public List<UserEntity> getTeachers() {
		return teachers;
	}

	/**
	 * Setter de l'attribut {@link ClassEntity#teachers}
	 * @param teachers l'attribut {@link ClassEntity#teachers} à setter
	 */
	public void setTeachers(List<UserEntity> teachers) {
		this.teachers = teachers;
	}

	/**
	 * 
	 * @param user
	 */
	public void addTeacher(UserEntity user) {
		this.teachers.add(user);
		user.getClassrooms().add(this);
	}
	
	
}